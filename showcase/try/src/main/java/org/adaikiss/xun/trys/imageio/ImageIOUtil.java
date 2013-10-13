/**
 * 
 */
package org.adaikiss.xun.trys.imageio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

/**
 * @author hlw
 * 
 */
public class ImageIOUtil {

	public static BufferedImage resizeSuit(BufferedImage img, int nw, int nh) {
		int w = img.getWidth();
		int h = img.getHeight();
		if (nw <= 0) {
			nw = nh * w / h;
		}
		if (nh <= 0) {
			nh = nw * h / w;
		}
		return resize(img, nw, nh);
	}

	public static BufferedImage resize(BufferedImage img, int nw, int nh) {
		// return (BufferedImage)img.getScaledInstance(nw, nh,
		// Image.SCALE_SMOOTH);
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(nw, nh, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, nw, nh, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	public static BufferedImage convertRGBAToIndexed(BufferedImage src) {
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(),
				BufferedImage.TYPE_BYTE_INDEXED);
		Graphics g = dest.getGraphics();
		g.setColor(new Color(231, 20, 189));

		// fill with a hideous color and make it transparent
		g.fillRect(0, 0, dest.getWidth(), dest.getHeight());
		dest = makeTransparent(dest, 0, 0);

		dest.createGraphics().drawImage(src, 0, 0, null);
		return dest;
	}

	public static BufferedImage makeTransparent(BufferedImage image, int x,
			int y) {
		ColorModel cm = image.getColorModel();
		if (!(cm instanceof IndexColorModel))
			return image; // sorry...
		IndexColorModel icm = (IndexColorModel) cm;
		WritableRaster raster = image.getRaster();
		int pixel = raster.getSample(x, y, 0); // pixel is offset in ICM's
												// palette
		int size = icm.getMapSize();
		byte[] reds = new byte[size];
		byte[] greens = new byte[size];
		byte[] blues = new byte[size];
		icm.getReds(reds);
		icm.getGreens(greens);
		icm.getBlues(blues);
		IndexColorModel icm2 = new IndexColorModel(8, size, reds, greens,
				blues, pixel);
		return new BufferedImage(icm2, raster, image.isAlphaPremultiplied(),
				null);
	}
}
