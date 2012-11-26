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
public class AjaxFilter {
	private String property;
	private Object value;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
