package com.core.amazon.bucket;

import java.io.File;

public interface IAmazonS3Object {


	/** {@link AmazonBucketManagerImpl#insertPrivateObject(String, String, File)} */
	String insertPrivateObject(String bucketName, String key, File file);
	
	/** {@link AmazonBucketManagerImpl#isObjectKeyAlreadyExist(String, String)} */
	boolean isObjectKeyAlreadyExist(String bucketName, String objectKey);
	
	/** {@link AmazonBucketManagerImpl#setObjectPublic(String, String)} */
	void setObjectPublic(String bucketName, String key)
				throws IllegalArgumentException;
	
	/** {@link AmazonBucketManagerImpl#deleteObject(String, String)} */
	void deleteObject(String bucketName, String objectKey);
	

}
