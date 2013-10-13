/**
 * 下午03:56:15
 */
package org.adaikiss.kay.trys.tij.tuple;

/**
 * hlw
 * 
 */
public class TupleTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TupleUtils.TwoTuple<String, Integer> tp = TupleUtils
				.newTuple("aaa", 12);
		System.out.println(tp);
		TupleUtils.FiveTuple<String, String, Integer, Double, Integer> ft = TupleUtils
				.newTuple("aaa", "bbb", 12, 12D, 11);
		System.out.println(ft);
	}

}
