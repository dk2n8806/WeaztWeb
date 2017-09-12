package com.common.util.image;

import java.awt.Image;
import java.awt.image.BufferedImage;


/** <h1>Image Converter</h1>
 *  
 *  An interface that defines methods to convert an file to a file and via vesa.
 * @author dk
 */
public interface ImageConverter {

	/** To BufferedImage
	 * Converts a given Image into a BufferredImage
	 * @param image The Image to be converted
	 * @return the converted BufferedImage
	 * ****************************************************/
	BufferedImage 
	toBufferedImage(Image image);
	
	
	
}
