/**
 * 下午03:31:11
 */
package org.adaikiss.kay.trys.tij.tuple;

/**
 * hlw
 * 
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	public final C c;

	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		this.c = c;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + c;
	}
}
