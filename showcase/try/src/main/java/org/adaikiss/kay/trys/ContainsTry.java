/**
 * 
 */
package org.adaikiss.kay.trys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hlw
 *
 */
public class ContainsTry {
	static List<Foo> list = new ArrayList<Foo>();
	static class Foo{
		private int id;
		public Foo(int id){
			this.id = id;
		}
		public int getId(){
			return id;
		}
		public void setId(int id){
			this.id = id;
		}
		@Override
		public boolean equals(Object o){
			if(o == this){
				return true;
			}
			if(o == null){
				return false;
			}
			if(!o.getClass().equals(Foo.class)){
				return false;
			}
			Foo obj = (Foo)o;
			return obj.getId() == id;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		list.add(new Foo(1));
		for(int i = 0; i < 10; i ++){
			Foo f = new Foo(i);
			if(list.contains(f)){
				System.out.println("duplicate!");
				continue;
			}
			list.add(f);
		}
		System.out.println(list.size());
	}

}
