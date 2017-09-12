package com.core.amazon.bucket;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.SetObjectAclRequest;

@Service
public class AmazonBucketManagerImpl 
implements IAmazonS3Bucket, IAmazonS3Object {

	public static void main(String args[]) {
		IAmazonS3Object amazonBucket = new AmazonBucketManagerImpl();
		String bucketName = "example";
		String objectKey = "otherone111";
		String path = amazonBucket.insertPrivateObject(bucketName, objectKey, new File("/home/dk/Pictures/isabella/pink.jpg"));
		amazonBucket.setObjectPublic(bucketName, objectKey);
		System.out.println(path);
		System.out.println(amazonBucket.isObjectKeyAlreadyExist(bucketName, objectKey));
	}
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String BUCKET_SUFFIX = "-mingofy-buket";
	private static final String OBJECT_SUFFIX = "-mingofy-object";
	private static final String AMAZON_CONSTANT = "https://s3.amazonaws.com";
	
	
	
	private AmazonS3 s3Client;
	public AmazonBucketManagerImpl() {
		s3Client = new AmazonS3Client(AmazonUtil.getAwsCredentials());
	}

	
	
	
	private String getBucketUrl(String bucketName) {
		return new StringBuilder().append(AMAZON_CONSTANT)
									.append("/")
									.append(bucketName)
									.toString();
	}
	
	
	private String getObjectUrl(String bucketName, String objectKey) {
		return new StringBuilder().append(AMAZON_CONSTANT)
									.append("/")
									.append(bucketName)
									.append("/")
									.append(objectKey)
									.toString();
	}
	
	
	
	private String getBucketConf(String bucketName) {
		return new StringBuilder().append(bucketName)
									.append(BUCKET_SUFFIX)
									.toString();
	}
	
	private String getObjectConf(String objectKey) {
		return new StringBuilder().append(objectKey)
									.append(OBJECT_SUFFIX)
									.toString();
	}
	
	
	
	@Override
	public String createBucket(String bucketName) {
		bucketName = this.getBucketConf(bucketName);
		try {
			if(!s3Client.doesBucketExist(bucketName)) {
				s3Client.createBucket(bucketName);
				return this.getBucketUrl(bucketName);
			}
		}catch(AmazonServiceException e) {
			LOGGER.info("Caught an AmazonServiceException, which means your request"
					+ " made it to Amazon S3, but was rejected with an error response");
		} catch(AmazonClientException e) {
			LOGGER.info("Caught an AmazonClientException, which means the client encountered"
					+ " an internal error while trying to communicate with S3");
		}
		return null;
	}
	

	@Override
	public void deleteBucket(String bucketName) {
		bucketName = this.getBucketConf(bucketName);
		try {
				s3Client.deleteBucket(bucketName);
		}catch(AmazonServiceException e) {
			LOGGER.info("Caught an AmazonServiceException, which means your request"
					+ " made it to Amazon S3, but was rejected with an error response");
		} catch(AmazonClientException e) {
			LOGGER.info("Caught an AmazonClientException, which means the client encountered"
					+ " an internal error while trying to communicate with S3");
		}
	}
	

	@Override
	public List<Bucket> getBuckets() {
		try {
			return s3Client.listBuckets();
		} catch(AmazonClientException e) {
			return new ArrayList<Bucket>(); 
		}
	}
	
	
	
	@Override
	public String insertPrivateObject(
			String bucketName, String objectKey, File file) {
		try {
			bucketName = this.getBucketConf(bucketName);
			objectKey = this.getObjectConf(objectKey);

			System.out.println(bucketName);
			// Create a bucket if the bucket hasn't created
			if(!s3Client.doesBucketExist(bucketName)) {
				s3Client.createBucket(bucketName);
			}
			
			// Determine if the key already exist in the bucket
			// If the object key doesnt not exist in the bucket,
			// insert the object into the bucket and return the
			// path to the object. 
			// If the object key already exists in the bucket,
			// return null.
			if(!this.isObjectKeyAlreadyExist(bucketName, objectKey)) {
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, file);
				s3Client.putObject(putObjectRequest);
				return this.getObjectUrl(bucketName, objectKey);
			} else {
				return null;
			}
		} catch(AmazonS3Exception e) {
			LOGGER.info("Catch amazon s3 exception");
		} catch(AmazonClientException e) {
		
			LOGGER.info("Unable to upload a file to bucket");
		}
		return null;
	}

	@Override
	public void deleteObject(String bucketName, String objectKey) {
		try {
			bucketName = this.getBucketConf(bucketName);
			objectKey = this.getObjectConf(objectKey);
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, objectKey);
			s3Client.deleteObject(deleteObjectRequest);
		} catch(AmazonClientException e) {
			LOGGER.info("Unable to delete object");
		}
	}




	@Override
	public boolean isObjectKeyAlreadyExist(String bucketName, String objectKey) {
		
		try {
			bucketName = this.getBucketConf(bucketName);
			objectKey = this.getObjectConf(objectKey);
			S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, objectKey));
			if(object == null) {
				return false;
			}
			return true;
		}catch(AmazonS3Exception e) {
			return false;
		}
	}




	@Override
	public void setObjectPublic(String bucketName, String objectKey) {
		try {
			bucketName = this.getBucketConf(bucketName);
			objectKey = this.getObjectConf(objectKey);
			System.out.println(bucketName);
			//if(this.isObjectKeyAlreadyExist(bucketName, objectKey)) {
				s3Client.setObjectAcl(new SetObjectAclRequest(bucketName, objectKey, CannedAccessControlList.PublicRead));
			//} else {
				//throw new IllegalArgumentException();
			//}
		} catch(AmazonClientException e) {
			throw new IllegalArgumentException();
		}
	}
}
