package com.common.util.image;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class AbstractImageManager implements ImageConverter, ResizeImage {

	/** To BufferedImage
	 * Converts a given Image into a BufferredImage
	 * @param image The Image to be converted
	 * @return the converted BufferedImage
	 * ****************************************************/
	@Override
	public BufferedImage toBufferedImage(Image image) {
		if(image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		
		// Determine if the image has transparent pixels
		boolean hasAlpha = hasAlpha(image);
		BufferedImage bImg = null;
		GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try {
			
			// Determine the type of transparency of the Image
			int transparency = (hasAlpha) ? 	Transparency.BITMASK 
																			: Transparency.OPAQUE;
			
			GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
			GraphicsConfiguration gConf = gDev.getDefaultConfiguration();
			
			bImg = gConf.createCompatibleImage(
													image.getWidth(null), image.getHeight(null), transparency);
		} 
		catch(HeadlessException e) {} // No screen
		
		if(bImg == null) {
			// Determine the type of the Image
			int type = (hasAlpha) ? BufferedImage.TYPE_INT_ARGB 
														: BufferedImage.TYPE_INT_RGB;
			bImg = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
	
		// Copy Image to buffered image
		Graphics2D graphic = bImg.createGraphics();
		// Paint the image onto the buffered image
		graphic.drawImage(image, 0, 0, null);
		graphic.dispose();
		return bImg;
	}

	
	
	/** Has Alpha
	 * Determine if the image has transparent pixels
	 * @param image The Image
	 * @return true if image has alpha, false otherwise
	 * *********************************************************************/
	public boolean hasAlpha(Image image) {
		// If buffered Image, the color model is readily available
		if(image instanceof BufferedImage) {
			return ((BufferedImage) image).getColorModel().hasAlpha();
		}
		
		// Use a pixel grabber to retrieve the image's color model
		// Grapping a single pixel is usually sufficient
		PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			// Request and wait for pixels deliver
			pixelGrabber.grabPixels();
		} catch(InterruptedException e) {
			
		}
		return pixelGrabber.getColorModel().hasAlpha();
		
	}



	/** Resize
	 * Resizes an image with given scale width and scale height
	 * @param image The Original image
	 * @param scaleWidth Absolute width in pixels
	 * @param scaleHeight Absolute height in pixels
	 * ***********************************************************/
	@Override
	public void resize(BufferedImage image, int scaleWidth, int scaleHeight) {
		Graphics2D g2D = image.createGraphics();
		g2D.drawImage(image, 0, 0, scaleWidth, scaleHeight, null);
		g2D.dispose();
	}
	
}
