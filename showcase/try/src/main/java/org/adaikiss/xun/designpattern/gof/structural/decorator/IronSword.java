/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.decorator;

import java.util.Arrays;
import java.util.List;

/**
 * @author hlw
 *
 */
public class IronSword extends Sword{

	@Override
	public List<String> getMaterials() {
		return Arrays.asList(new String[]{"Iron"});
	}
	
}
