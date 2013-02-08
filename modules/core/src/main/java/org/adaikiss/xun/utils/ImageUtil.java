package org.adaikiss.xun.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author hlw
 *
 */
public class ImageUtil {

	private static final Set<String> IMAGE_TYPES;

	private static WildcardHelper matcher;

	static {
		String[] contentTypes = new String[] { "image/*" };
		IMAGE_TYPES = new HashSet<String>();
		for (String contentType : contentTypes) {
			IMAGE_TYPES.add(contentType);
		}
	}

	/**
	 * 实现图像的等比缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		int type = BufferedImage.TYPE_3BYTE_BGR;// source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx < sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		target.flush();
		return target;
	}

	/**
	 * 放大/缩小图片
	 * 
	 * @param originalImage
	 * @param zoomLevel
	 *            缩放级别，100表示原始大小
	 * @param minWidth
	 *            最小宽度(避免缩得太小)
	 * @param minHeight
	 *            最小高度
	 * @return
	 */
	public static BufferedImage zoomImage(BufferedImage originalImage, int zoomLevel, int minWidth, int minHeight) {
		double zoom = zoomLevel / 100.000;
		int imageWidth = originalImage.getWidth();
		int imageHeight = originalImage.getHeight();
		int newImageWidth = (int) (imageWidth * zoom);
		int newImageHeight = (int) (imageHeight * zoom);
		int backImageWidth = Math.max(newImageWidth, minWidth);
		int backImageHeight = Math.max(newImageHeight, minHeight);
		BufferedImage resizedImage = new BufferedImage(backImageWidth, backImageHeight, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newImageWidth, newImageHeight, null);
		g.dispose();
		return resizedImage;
	}

	/**
	 * 调整图片大小
	 * 
	 * @param originalImage
	 * @param newImageWidth
	 * @param newImageHeight
	 * @return
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int newImageWidth, int newImageHeight) {
		BufferedImage resizedImage = new BufferedImage(newImageWidth, newImageHeight, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newImageWidth, newImageHeight, null);
		g.dispose();
		return resizedImage;
	}

	/**
	 * 判断是否属于图片，see also {@link IMAGE_TYPES}
	 * 
	 * @param contentType
	 * @return
	 */
	public static boolean isImage(String contentType) {
		if (null == contentType) {
			return false;
		}
		contentType = contentType.toLowerCase();
		Map<String, String> map = new HashMap<String, String>();
		if (null == matcher) {
			matcher = new WildcardHelper();
		}
		for (String regex : IMAGE_TYPES) {
			if (matcher.match(map, contentType, matcher.compilePattern(regex))) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isImage("image/pjpg"));
	}

}