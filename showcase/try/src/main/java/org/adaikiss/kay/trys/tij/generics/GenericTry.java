/**
 * 上午09:03:09
 */
package org.adaikiss.kay.trys.tij.generics;

import java.util.List;

/**
 * hlw
 *
 */
public class GenericTry<T> {
	private Class<T> kind;
	public List<? extends T> a;
	public List<? super T> b;
	public static <K> void f1(GenericTry<K> p){
	}
	public static void f2(GenericTry<?> p){	
	}
	public void aa(T aa){
		System.out.println(kind.isInstance(aa));
	}
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		GenericTry<B> tr = new GenericTry<B>();
		tr.aa(new C());
		//error! tr.a.add(new A());
		//error! tr.a.add(new B());
		//error! tr.a.add(new C());
		//error! tr.b.add(new A());
		tr.b.add(new B());
		tr.b.add(new C());

		@SuppressWarnings("rawtypes")
		GenericTry t = new GenericTry<String>();
		f1(t);//warning
		f2(t);
		
	}

	static class A{
	}
	static class B extends A{
		
	}
	static class C extends B{
		
	}
	//self bound
	class SelfBounded<K extends SelfBounded<K>> {
	}
}
