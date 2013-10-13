/**
 * 
 */
package org.adaikiss.xun.trys;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.adaikiss.xun.trys.imageio.ImageIOUtil;

/**
 * @author hlw
 *
 */
public class AnimatedGifEnocderIllustration {

	public static void main(String[] args) throws Exception{
		  BufferedImage img1 = ImageIOUtil.resizeSuit(ImageIO.read(new File("e:/2.jpg")), 100, 0);
		  BufferedImage img2 = ImageIOUtil.resizeSuit(ImageIO.read(new File("e:/1.jpg")), 100, 0);
		  AnimatedGifEncoder e = new AnimatedGifEncoder();
		  e.start("e:/a.gif");
		  e.setDelay(0);
		  e.setRepeat(-1);
		  e.setQuality(1);
		  for(int i = 0;i< 60; i++){
			  e.addFrame(img1);
		  }
		  e.addFrame(img2);
		  e.finish();
	  }

}
