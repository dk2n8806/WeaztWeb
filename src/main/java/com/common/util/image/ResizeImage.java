package com.common.util.image;

import java.awt.image.BufferedImage;

public interface ResizeImage {

	/** Resize
	 * Resizes an image with given scale width and scale height
	 * @param image The Original image
	 * @param scaleWidth Absolute width in pixels
	 * @param scaleHeight Absolute height in pixels
	 * ***********************************************************/
	void 
	resize(BufferedImage image, int scaleWidth, int scaleHeight);
}
