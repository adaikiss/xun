package org.adaikiss.xun;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.security.FilterChainDefinitionsLoader;
import org.apache.shiro.config.Ini;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springside.modules.utils.spring.SpringContextHolder;

/**
 * @author hlw
 * 
 */
public class ReloadResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReloadResourceServlet() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FilterChainDefinitionsLoader loader = SpringContextHolder
				.getBean(FilterChainDefinitionsLoader.class);
		// 重新加载资源权限信息
		refreshFilterChainDefinitions(loader.loadDefinitions());
	}

	/**
	 * refresh the shiro's filter chain definitions
	 * 
	 * @param definitions
	 */
	private void refreshFilterChainDefinitions(String definitions) {
		AbstractShiroFilter shiroFilter = SpringContextHolder
				.getBean("shiroFilter");
		DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager) ((PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver()).getFilterChainManager();
		filterChainManager
				.setFilterChains(new LinkedHashMap<String, NamedFilterList>());
		synchronized(filterChainManager.getFilterChains()){			
			Map<String, String> chains = getFilterChainDefinitionMap(definitions);
			// from {@link
			// org.apache.shiro.spring.web.ShiroFilterFactoryBean#createFilterChainManager()}
			// build up the chains:
			if (!CollectionUtils.isEmpty(chains)) {
				for (Map.Entry<String, String> entry : chains.entrySet()) {
					String url = entry.getKey();
					String chainDefinition = entry.getValue();
					filterChainManager.createChain(url, chainDefinition);
				}
			}
		}
	}

	/**
	 * modefied from
	 * {@link org.apache.shiro.spring.web.ShiroFilterFactoryBean#setFilterChainDefinitions(String)}
	 * 
	 * @param definitions
	 * @return
	 */
	private Map<String, String> getFilterChainDefinitionMap(String definitions) {
		Ini ini = new Ini();
		ini.load(definitions);
		// did they explicitly state a 'urls' section? Not necessary, but just
		// in case:
		Ini.Section section = ini
				.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			// no urls section. Since this _is_ a urls chain definition
			// property, just assume the
			// default section contains only the definitions:
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		return section;
	}
}
