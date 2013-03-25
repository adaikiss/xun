/**
 * 
 */
package org.adaikiss.xun.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

/**
 * @author hlw
 *
 */
public class FilterChainDefinitionsLoader {
	private static final Logger logger = LoggerFactory.getLogger(FilterChainDefinitionsLoader.class);
	@Autowired
	private ResourceLoader resourceLoader;

	private String location;

	public String loadDefinitions(){
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		logger.debug("===============>开始加载资源权限信息<===============");
		long start = System.currentTimeMillis();
		try {
			is = resourceLoader.getResource(location).getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			char[] buff = new char[1024];
			int n;
			while((n = in.read(buff)) != -1){
				sb.append(new String(buff, 0, n));
			}
			logger.debug("===============>加载资源权限信息成功,耗时{}毫秒<===============", System.currentTimeMillis() - start);
			logger.debug("\n=====================================\n" + sb.toString() + "\n=====================================");
		} catch (IOException e) {
			logger.error("加载资源权限信息时出现异常!", e);
		}
		return sb.toString();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
