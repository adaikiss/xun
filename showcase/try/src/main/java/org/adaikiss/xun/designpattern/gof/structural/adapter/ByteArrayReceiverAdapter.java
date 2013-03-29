/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.adapter;

/**
 * @author hlw
 * 
 */
public class ByteArrayReceiverAdapter {
	private ByteArrayReceiver receiver;

	public ByteArrayReceiverAdapter(ByteArrayReceiver receiver) {
		this.receiver = receiver;
	}

	public void receive(Object data) {
		if (data == null) {
			receiver.receive(null);
			return;
		}
		receiver.receive(data.toString().getBytes());
	}
}
