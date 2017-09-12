package com.core.amazon.bucket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.admin.resource.WeaztAppResourceKeys;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class AmazonUtil {

	private static final Logger log = LogManager.getLogger();
	
	private static AWSCredentials awsCredentials;
	
	private AmazonUtil() {}
	
	static {
		init();
	}
	
	
	private static void init() {
		try {
			awsCredentials = new BasicAWSCredentials(
									WeaztAppResourceKeys.AmazonResource.AMAZON_ACCESS_KEY
									, WeaztAppResourceKeys.AmazonResource.AMAZON_SECRET_KEY);
			
		} catch(AmazonClientException e) {
			log.error("Unable initialize amazon credentials");
		}
	}
 	
	public static AWSCredentials getAwsCredentials() {
		return awsCredentials;
	}
}
