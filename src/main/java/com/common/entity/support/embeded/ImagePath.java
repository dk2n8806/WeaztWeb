package com.common.entity.support.embeded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Embeddable
public class ImagePath implements Serializable{


	private static final Logger logger = LogManager.getLogger();
	
	private static final long serialVersionUID = 1L;
	private String path;
	private boolean isActive;
	
	public static ImagePath create(String path) {
		try {
			if(path == null) {
				throw new IllegalArgumentException("Image path is required");
			}
			ImagePath imagePath = new ImagePath();
			imagePath.path = path;
			imagePath.isActive = true;
			
			return imagePath;
		} catch(IllegalArgumentException e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}


	@Lob
	@Column(name="IMAGE_PATH", nullable=false, updatable=false)
	public String getPath() {return path;}
	public void setPath(String path) {	this.path = path;}

	@Column(name="IS_ACTIVE", nullable=false)
	public boolean isActive() {return isActive;}
	public void setActive(boolean isActive) {	this.isActive = isActive;}
	
	
	
}
