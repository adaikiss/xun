/**
 * 
 */
package org.adaikiss.xun.performance.chararray;

/**
 * @author HuLingwei
 *
 */
public interface Executable<T> {
	String getDescription();
	T execute();
}
