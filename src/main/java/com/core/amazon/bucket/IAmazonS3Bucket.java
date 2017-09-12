package com.core.amazon.bucket;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;

public interface IAmazonS3Bucket {

	/** {@link AmazonBucketManagerImpl#createBucket(String)} */
	String createBucket(String bucketName);
	
	
	/** {@link AmazonBucketManagerImpl#deleteBucket(String)} */
	void deleteBucket(String bucketName);
	
	
	/** {@link AmazonBucketManagerImpl#getBuckets()} */
	List<Bucket> getBuckets();
	
}
