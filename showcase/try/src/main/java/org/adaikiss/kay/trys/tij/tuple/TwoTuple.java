/**
 * 下午03:26:48
 */
package org.adaikiss.kay.trys.tij.tuple;

/**
 * hlw
 * 
 */
public class TwoTuple<A, B> {
	public final A a;
	public final B b;

	public TwoTuple(A a, B b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return a + ", " + b;
	}
}
