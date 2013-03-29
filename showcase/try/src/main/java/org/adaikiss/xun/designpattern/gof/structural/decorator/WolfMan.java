/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.decorator;

/**
 * @author hlw
 *
 */
public class WolfMan implements Struckable{
	@Override
	public void strucked(Sword sword){
		if(sword.getMaterials().contains("Silver")){
			System.out.println("Oh, the sword contains silver, I'm hurt!");
		}else{
			System.out.println("Ah, the sword contains no silver, it can't hurt me!");
		}
	}
}
