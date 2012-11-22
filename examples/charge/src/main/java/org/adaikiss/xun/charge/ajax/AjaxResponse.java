/**
 * 
 */
package org.adaikiss.xun.charge.ajax;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author hlw
 * 
 */
@XmlRootElement
@JsonInclude(Include.NON_EMPTY)
public class AjaxResponse {
	private boolean success;
	private String msg;
	private Object data;

	public AjaxResponse() {
	}

	public AjaxResponse(boolean success) {
		this.success = success;
	}

	public AjaxResponse(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public AjaxResponse(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}

	public AjaxResponse(boolean success, String msg, Object data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
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
}
