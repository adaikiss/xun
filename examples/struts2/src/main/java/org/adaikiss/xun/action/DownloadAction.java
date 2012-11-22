package org.adaikiss.xun.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@Result(name = "download", type = "stream", params = { "contentType",
		"application/octet-stream", "inputName", "inputStream",
		"contentDisposition", "attachment;filename=\"${filename}\"",
		"bufferSize", "1024" })
@SuppressWarnings("serial")
public class DownloadAction extends ActionSupport {
	private InputStream inputStream;
	private String content = "";
	private String filename = "default.txt";

	@Override
	public String execute() {
		inputStream = new ByteArrayInputStream(content.getBytes(Charset
				.forName("UTF-8")));
		return "download";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}