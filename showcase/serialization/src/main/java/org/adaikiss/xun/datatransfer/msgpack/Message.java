/**
 * 
 */
package org.adaikiss.xun.datatransfer.msgpack;

import org.msgpack.annotation.Optional;

/**
 * @author hlw
 *
 */
@org.msgpack.annotation.Message
public class Message {
	private long time;
	@Optional
	private String content;
	private MessageType type;
	private User user;

	public Message() {
		super();
	}

	public Message(long time, String content, MessageType type, User user) {
		super();
		this.time = time;
		this.content = content;
		this.type = type;
		this.user = user;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
