/**
 * 
 */
package org.adaikiss.xun.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * xml test data
 * 
 * @author hlw
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TestData {

	/**
	 * 这个属性不能和 {@link #value}/{@link #basePath}混用，选择其一
	 * 
	 * @return
	 */
	String[] paths() default {};

	/**
	 * 这个属性不能和 {@link #paths}/{@link #basePath}混用，选择其一
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 对于oracle数据库需要指定，其他数据库可以忽略
	 * 
	 * @return
	 */
	String schema() default "";

	/**
	 * 测试数据的基本路径，使用basePath/测试类.getSimpleName()-data.xml来寻找测试数据,这个属性不能和
	 * {@link #paths}/{@link #value}混用，选择其一
	 * 
	 * @return
	 */
	String basePath() default "";
}
