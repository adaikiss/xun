/**
 * 
 */
package org.adaikiss.xun.apt;

/**
 * @author hlw
 *
 */
@Xun
public class XunTest {
	@Xun
	private String name;

	@Xun
	protected static int number;

	@Xun
	public String getName(){
		return name;
	}
}
