package com.core.amazon.bucket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.admin.resource.WeaztAppResourceKeys;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.common.util.file.FileUtil;

/******************************************************************************
 * 
 * @author dk
 *
 */
@Service
public class S3MultipartUpload implements IS3ProfileUploadService, IS3ProductUploadService {
	
	
	private String getPublishPath(String bucketName, String keyName) {
		return new StringBuilder()
						.append(WeaztAppResourceKeys.S3BucketResource.END_POINT)
						.append("/").append(bucketName)
						.append("/").append(keyName)
						.toString();
	}
	
	private String generate(String folder) {
		return folder + UUID.randomUUID().toString();
	}
	

	
	private String upload(String bucketName, String keyName, File file) {
		AmazonS3 s3Client = new AmazonS3Client(AmazonUtil.getAwsCredentials());       
		String imageName = this.formatImage(keyName);
		
        // Create a list of UploadPartResponse objects. You get one of these
        // for each part upload.
        List<PartETag> partETags = new ArrayList<PartETag>();

        // Step 1: Initialize.
        InitiateMultipartUploadRequest initRequest = new 
             InitiateMultipartUploadRequest(bucketName, imageName);
        InitiateMultipartUploadResult initResponse = 
        	                   s3Client.initiateMultipartUpload(initRequest);

        long contentLength = file.length();
        long partSize = 5242880; // Set part size to 5 MB (5*1024*1024).

        try {
            // Step 2: Upload parts.
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                // Last part can be less than 5 MB. Adjust part size.
            	partSize = Math.min(partSize, (contentLength - filePosition));
            	
                // Create request to upload a part.
                UploadPartRequest uploadRequest = new UploadPartRequest()
                    .withBucketName(bucketName).withKey(imageName)
                    .withUploadId(initResponse.getUploadId()).withPartNumber(i)
                    .withFileOffset(filePosition)
                    .withFile(file)
                    .withPartSize(partSize);

                // Upload part and add response to our list.
                partETags.add(
                		s3Client.uploadPart(uploadRequest).getPartETag());

                filePosition += partSize;
            }

            // Step 3: Complete.
            CompleteMultipartUploadRequest compRequest = new 
                         CompleteMultipartUploadRequest(
                                    bucketName, 
                                    imageName, 
                                    initResponse.getUploadId(), 
                                    partETags);

            s3Client.completeMultipartUpload(compRequest);
            s3Client.setObjectAcl(bucketName, imageName, CannedAccessControlList.PublicRead);
        } catch (Exception e) {
            s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(
                    bucketName, imageName, initResponse.getUploadId()));
            return null;
        }
        return getPublishPath(bucketName, imageName);
	}
	
	
	
	private String formatImage(String image) {
		if(!image.endsWith(".jpg")) {
			image += ".png";
		}
		return image;
	}
	
	

	
	
/** 
 * {@link IS3ProductUploadService#uploadProduct(File)} 
 * */
	@Override
	public String uploadProduct(File file) {
		if(file.length() > 0){
			return upload(WeaztAppResourceKeys.S3BucketResource.BUCKET
					, generate(WeaztAppResourceKeys.S3BucketResource.PRODUCT_BUCKET), file);
		}
		return null;
	}
	

/** 
 * {@link IS3ProductUploadService#uploadProduct(MultipartFile)} 
 * */
	@Override
	public String uploadProduct(MultipartFile file) {
		if(!file.isEmpty()){
			try {
				return uploadProduct(new FileUtil().convert(file));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	
	
/*******************************************************************************
 * Upload profile 
 * 
 * {@link IS3ProfileUploadService#uploadProfile(File)}
 */
	@Override
	public String uploadProfile(File profileFile) {
		if(profileFile.length() > 0) {
			return this.upload(WeaztAppResourceKeys.S3BucketResource.BUCKET
					, generate(WeaztAppResourceKeys.S3BucketResource.PROFILE_BUCKET)
							, profileFile);
		}
		return null;
	}
	
	
/********************************************
 * Upload profile 
 * 
 * {@link IS3ProfileUploadService#uploadProfile(MultipartFile)}
 */
	@Override
	public String uploadProfile(MultipartFile file) {
		if(!file.isEmpty()){
			try {
				return uploadProfile( new FileUtil().convert(file));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
