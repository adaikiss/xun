/**
 * 下午03:34:43
 */
package org.adaikiss.kay.trys.tij.tuple;

/**
 * hlw
 * 
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
	public final D d;

	public FourTuple(A a, B b, C c, D d) {
		super(a, b, c);
		this.d = d;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + d;
	}
}
