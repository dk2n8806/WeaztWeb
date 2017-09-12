package com.common.entity.account;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.support.embeded.ImagePath;

@Entity
@Table(name="AVATAR")
public class Avatar extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private ImagePath imagePath;

	public static Avatar create(ImagePath imagePath) {
		try {
			if(imagePath == null) {
				throw new IllegalArgumentException("Image path is required");
			}
			Avatar accountAvatar = new Avatar();
			accountAvatar.imagePath = imagePath;
			return accountAvatar;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Avatar createEntityInstance(String imagePath) {
		return create(ImagePath.create(imagePath));
	}
	
	
	

	@Embedded
	public ImagePath getImagePath() {return imagePath;}
	public void setImagePath(ImagePath imagePath) {	this.imagePath = imagePath;}
	
}
