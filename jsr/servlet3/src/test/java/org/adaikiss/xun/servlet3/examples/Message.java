/**
 * 
 */
package org.adaikiss.xun.servlet3.examples;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author hlw
 * 
 */
public class Message {
	private String name;
	private Date time;
	private String content;

	public Message(String name, String content) {
		this.name = name;
		this.content = content;
		this.time = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonFormat(pattern = "HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(name).append(' ')
				.append(new SimpleDateFormat("HH:mm:ss").format(time)).append("] ").append(content)
				.toString();
	}
}
