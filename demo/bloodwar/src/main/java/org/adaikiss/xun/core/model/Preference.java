/**
 * 
 */
package org.adaikiss.xun.core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author hlw
 * 
 */
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_EMPTY)
public class Preference{
	/**
	 * 网站名称
	 */
	private String name = "Xun CMS";
	/**
	 * 网站url
	 */
	private String url = "localhost";
	/**
	 * 页面url后缀
	 */
	private String pageSuffix = ".html";
	/**
	 * 栏目URL后缀
	 */
	private String channelSuffix = "/";
	/**
	 * 各种首页url名称
	 */
	private String index = "index";

	/**
	 * 模板主题
	 */
	private String theme = "default";

	/**
	 * 网站开始运营时间
	 */
	private Date startup;

	/**
	 * 是否已经初始化
	 */
	private boolean initialized = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageSuffix() {
		return pageSuffix;
	}

	public void setPageSuffix(String pageSuffix) {
		this.pageSuffix = pageSuffix;
	}

	public String getChannelSuffix() {
		return channelSuffix;
	}

	public void setChannelSuffix(String channelSuffix) {
		this.channelSuffix = channelSuffix;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Date getStartup() {
		return startup;
	}

	public void setStartup(Date startup) {
		this.startup = startup;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
