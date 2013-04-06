/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.decorator;

import java.util.List;

/**
 * @author hlw
 *
 */
public abstract class Sword {
	public void struck(Struckable target){
		target.strucked(this);
	}
	public abstract List<String> getMaterials();
}
