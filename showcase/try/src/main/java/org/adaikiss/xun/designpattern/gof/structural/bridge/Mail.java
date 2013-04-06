/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.bridge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hlw
 * 
 */
public class Mail {
	private static final DateFormat formater = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	private Mail original;
	private String title;
	private String content;
	private Date date;

	public Mail(String title, String content, Date date) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public Mail(String title, String content, Date date, Mail original) {
		super();
		this.original = original;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public Mail getOriginal() {
		return original;
	}

	public void setOriginal(Mail original) {
		this.original = original;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("==========================\n");
		sb.append("Title:").append(title).append("\n");
		sb.append("Date:").append(formater.format(date)).append("\n");
		sb.append("Content:").append(content).append("\n");
		if (original != null) {
			sb.append("Original:\n");
			sb.append(original.toString());
		}
		return sb.toString();
	}
}
