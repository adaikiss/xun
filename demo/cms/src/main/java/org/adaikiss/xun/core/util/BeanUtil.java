/**
 * 
 */
package org.adaikiss.xun.core.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author hlw
 * 
 */
public class BeanUtil {
	/**
	 * 复制属性
	 * @param source
	 * @param target
	 * @param includes 需要复制的属性
	 */
	public static void copyProperties(final Object source, final Object target,
			final String... includes) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		final BeanWrapper trg = new BeanWrapperImpl(target);
		for (final String propertyName : includes) {
			trg.setPropertyValue(propertyName,
					src.getPropertyValue(propertyName));
		}
	}
}
