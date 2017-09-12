package com.core.amazon.bucket;


import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/******************************************************************************
 * 
 * @author dk
 *
 */
public interface IS3ProfileUploadService {


	
	/** {@link S3MultipartUpload#uploadProfile(String, String)} */
	//String uploadProfile(String keyName, String filePath);
	
	
	/** {@link S3MultipartUpload#uploadProfile(String)} */
	//String uploadProfile(String filePath);
	
	
	/** {@link S3MultipartUpload#uploadProfile(File)} */
	String uploadProfile(File profileFile);
	
	/** {@link S3MultipartUpload#uploadProfile(MultipartFile)} */
	String uploadProfile(MultipartFile file);
}
