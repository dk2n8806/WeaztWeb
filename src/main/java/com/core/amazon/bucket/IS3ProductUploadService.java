package com.core.amazon.bucket;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/***********************************************************************
 * 
 * @author dk
 *
 */
public interface IS3ProductUploadService {
	
	/** {@link S3MultipartUpload#uploadProduct(File)} */
	String uploadProduct(File file);

	/** {@link S3MultipartUpload#uploadProduct(MultipartFile)} */
	String uploadProduct(MultipartFile file);
}
