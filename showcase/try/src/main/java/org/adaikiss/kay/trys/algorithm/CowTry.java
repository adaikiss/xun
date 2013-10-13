/**
 * 2011-2-14
 */
package org.adaikiss.kay.trys.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * hlw
 *
 */
public class CowTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CowTry t = new CowTry();
		Farm farm = t.new Farm();
		t.new Cow(farm);
		System.out.println(farm.countCows());
		farm.passYear(10);
		System.out.println(farm.countCows());
	}

	class Farm{
		private Set<Cow> cows = new HashSet<Cow>();
		private Set<Cow> newCows = new HashSet<Cow>();
		private Set<Cow> deadCows = new HashSet<Cow>();
		public void remove(Cow cow){
			this.deadCows.remove(cow);
		}

		public void add(Cow cow){
			this.newCows.add(cow);
		}

		private void passYear(){
			for(Cow cow:this.cows){
				cow.grow();
			}
			cows.removeAll(deadCows);
			deadCows.clear();
			cows.addAll(newCows);
			newCows.clear();
		}

		public void passYear(int num){
			for(int i = 0;i<num;i++){
				this.passYear();
			}
		}

		public int countCows(){
			return this.cows.size() + this.newCows.size();
		}
	}

	class Cow{
		private int life = 11;
		private int adult = 3;
		private int age = 0;
		private Farm farm;
		public Cow(Farm farm){
			this.farm = farm;
			this.farm.add(this);
		}

		public void grow(){
			this.age++;
			this.bear();
			if(this.age>=this.life){
				this.dead();
			}
		}

		public void bear(){
			if(this.age >= this.adult){
				new Cow(this.farm);
			}
		}

		public void dead(){
			this.farm.remove(this);
		}
	}
}
