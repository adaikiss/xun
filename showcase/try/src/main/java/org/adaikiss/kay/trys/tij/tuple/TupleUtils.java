/**
 * 下午03:46:25
 */
package org.adaikiss.kay.trys.tij.tuple;

/**
 * hlw
 * 
 */
public class TupleUtils {
	public static <A, B> TwoTuple<A, B> newTuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}

	public static <A, B, C> ThreeTuple<A, B, C> newTuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}

	public static <A, B, C, D> FourTuple<A, B, C, D> newTuple(A a, B b, C c, D d) {
		return new FourTuple<A, B, C, D>(a, b, c, d);
	}

	public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> newTuple(A a, B b,
			C c, D d, E e) {
		return new FiveTuple<A, B, C, D, E>(a, b, c, d, e);
	}

	static class TwoTuple<A, B> {
		public final A a;
		public final B b;

		private TwoTuple(A a, B b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return a + ", " + b;
		}
	}

	static class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
		public final C c;

		private ThreeTuple(A a, B b, C c) {
			super(a, b);
			this.c = c;
		}

		@Override
		public String toString() {
			return super.toString() + ", " + c;
		}
	}

	static class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
		public final D d;

		private FourTuple(A a, B b, C c, D d) {
			super(a, b, c);
			this.d = d;
		}

		@Override
		public String toString() {
			return super.toString() + ", " + d;
		}
	}

	static class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
		public final E e;

		private FiveTuple(A a, B b, C c, D d, E e) {
			super(a, b, c, d);
			this.e = e;
		}

		@Override
		public String toString() {
			return super.toString() + ", " + e;
		}
	}
}
