package org.adaikiss.xun.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
public class AvatarHelper {
	private static final Logger logger = LoggerFactory.getLogger(AvatarHelper.class);
	public static final String AVATAR_BIG_SIZE = "avatar.big.size";
	public static final String AVATAR_SMALL_SIZE = "avatar.small.size";
	public static final String AVATAR_EDITOR_WIDTH = "avatar.editor.width";
	public static final String AVATAR_EDITOR_HEIGHT = "avatar.editor.height";

	public static final String AVATAR_PATH = "avatar.path";
	public static final String AVATAR_PREFIX = "avatar_";
	public static final String AVATAR_EXTENSION = ".jpg";

	public static String getAvatarPath(Long userId, int size) {
		return new StringBuilder().append(SystemManager.getProperty(AVATAR_PATH)).append("/").append(AVATAR_PREFIX)
				.append(userId).append("_").append(size).append("x").append(size).append(AVATAR_EXTENSION).toString();
	}

	public static String getImagePath(){
		//TODO:
		return null;
	}

	/**
	 * 切割头像
	 * 
	 * @param filename
	 *            原始图像
	 * @param userId
	 *            用户ID
	 * @param zoom
	 *            缩放级别
	 * @param size
	 *            编辑时的大小
	 * @param left
	 *            编辑时的offset.left
	 * @param top
	 *            编辑时的offset.top
	 * @throws IOException
	 */
	public static String[] cutAvatar(String filename, Long userId, int zoom, double size, double left, double top)
			throws IOException {
		SystemManager systemManager = SystemManager.getInstance();
		int bigSize = Integer.parseInt(systemManager.get(AVATAR_BIG_SIZE, "120"));
		int smallSize = Integer.parseInt(systemManager.get(AVATAR_SMALL_SIZE, "60"));
		String fsPath = getImagePath();
		if (StringUtils.isBlank(fsPath)) {
			logger.error("FS存储路径为空{}", fsPath);
			throw new RuntimeException("存储路径为空");
		}
		File parent = new File(fsPath + SystemManager.getProperty(AVATAR_PATH));
		parent.mkdirs();
		String smallName = getAvatarPath(userId, smallSize);
		String smallPath = fsPath + smallName;
		String bigName = getAvatarPath(userId, bigSize);
		String bigPath = fsPath + bigName;
		logger.debug("small avatar path:{}", smallPath);
		logger.debug("big avatar path:{}", bigPath);
		BufferedImage temp = ImageIO.read(new File(filename));
		// if (zoom != 100) {
		int editorWidth = Integer.parseInt(systemManager.get(AVATAR_EDITOR_WIDTH));
		int editorHeight = Integer.parseInt(systemManager.get(AVATAR_EDITOR_HEIGHT));
		temp = ImageUtil.zoomImage(temp, zoom, editorWidth, editorHeight);
		// }
		BufferedImage cutted = temp.getSubimage((int) left, (int) top, (int) size, (int) size);
		BufferedImage big = ImageUtil.resizeImage(cutted, bigSize, bigSize);
		BufferedImage small = ImageUtil.resizeImage(cutted, smallSize, smallSize);
		ImageIO.write(big, "jpg", new File(bigPath));
		ImageIO.write(small, "jpg", new File(smallPath));
		return new String[] { bigName, smallName };
	}

	public static void main(String[] args) {
	}
}
