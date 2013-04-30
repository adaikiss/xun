package org.adaikiss.xun.web;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

/**
 * @author hlw
 * 
 */
public class AjaxResponse {
	private boolean success;
	private String msg;
	private Object data;
	private Map<String, Object> json;

	public AjaxResponse() {
		this.json = new HashMap<String, Object>();
	}

	public AjaxResponse(boolean success) {
		this();
		setSuccess(success);
	}

	public AjaxResponse(boolean success, String msg) {
		this(success);
		setMsg(msg);
	}

	public AjaxResponse(boolean success, String msg, Object data) {
		this(success, msg);
		setData(data);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void write(Writer writer) throws IOException, JSONException {
		JSONUtil.serialize(writer, this);
	}

	/**
	 * 输出JSON到response,使用UTF-8编码, 非iframe
	 * 
	 * @param response
	 */
	public void writeJson(HttpServletResponse response) {
		writeJson(response, false);
	}

	/**
	 * 输出JSON到response,使用UTF-8编码
	 * 
	 * @param response
	 */
	public void writeJson(HttpServletResponse response, boolean isIframe) {
		writeJson(response, "UTF-8", isIframe);
	}

	/**
	 * 输出JSON到response
	 * 
	 * @param response
	 * @param encoding
	 * @param isIframe
	 *            对于iframe方式伪ajax,
	 *            避免firefox/chrome添加"&lt;pre&gt;"标签，需要将contentType设置为text/html
	 */
	public void writeJson(HttpServletResponse response, String encoding,
			boolean isIframe) {
		if (isIframe) {
			response.setContentType("text/html");
		} else {
			response.setContentType("application/json");
		}
		response.setCharacterEncoding(encoding);
		json.put("success", success);
		if(msg != null){
			json.put("msg", msg);
		}
		if(data != null){
			json.put("data", data);
		}
		try {
			write(response.getWriter());
		} catch (IOException e) {
			throw new RuntimeException("输出JSON时出现异常！", e);
		} catch (JSONException e) {
			throw new RuntimeException("转换JSON时出现异常！", e);
		}
	}
}
