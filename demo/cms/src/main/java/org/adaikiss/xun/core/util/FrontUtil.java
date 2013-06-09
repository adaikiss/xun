package org.adaikiss.xun.core.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontUtil {
	public static String pageNotFound(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model){
		return "404.html";
	}

	public static String getDefaultArticleTpl(){
		return "article";
	}

	public static String getDefaultChannelTpl(){
		return "channel";
	}
}
