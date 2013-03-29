/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.adapter;

import org.adaikiss.xun.designpattern.gof.structural.proxy.Proxy;

/**
 * <b>Adapter</b>
 * 
 * <pre>
 * Definition
 *   Convert the existing interfaces to a new interface to achieve compatibility and reusability of the unrelated classes in one application. Also known as Wrapper pattern.
 * Where to use & benefits
 *   Try to match an interface(WindowAdapter, etc.)
 *   Make unrelated classes work together.
 *   Multiple compatibility.
 *   Increase transparency of classes.
 *   Make a pluggable kit.
 *   Delegate objects.
 *   Highly class reusable.
 *   Achieve the goal by inheritance or by composition
 *   Related patterns include
 *     {@link Proxy}, which provides the same interface as its subject, whereas an adapter provides a different interface to the object it adapts.
 *     {@link Decorator}, which focuses on adding new functions to an object, whereas an adapter coordinates two different objects.
 *     {@link Bridge}, which tries to separate an interface from its implementation and make an object vary independently, whereas an adapter tries to change and cooperate the interface of an object.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Adapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ByteArrayReceiver byteArrayReceiver = new ByteArrayReceiver();
		byte[] bytes = new byte[]{Byte.parseByte("1"), Byte.parseByte("2")};
		byteArrayReceiver.receive(bytes);
		ByteArrayReceiverAdapter adapter = new ByteArrayReceiverAdapter(byteArrayReceiver);
		adapter.receive("12");
	}

}
