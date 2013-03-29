/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hlw
 * 
 */
public class SilverPlatedSword extends Sword {
	private Sword sword;

	@Override
	public List<String> getMaterials() {
		List<String> materials = new ArrayList<String>();
		materials.addAll(sword.getMaterials());
		materials.add("Silver");
		return materials;
	}

	public SilverPlatedSword(Sword sword) {
		this.sword = sword;
	}
}
