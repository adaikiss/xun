package org.adaikiss.xun.shiro;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.util.RegExPatternMatcher;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springside.modules.utils.spring.SpringContextHolder;


/**
 * @author hlw
 *
 */
public class SetPathMatcherHandler implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 对PathMatchingFilterChainResolver使用RegExPatternMatcher
		PathMatchingFilterChainResolver filterChainResolver = ((PathMatchingFilterChainResolver) ((AbstractShiroFilter) SpringContextHolder
				.getBean("shiroFilter")).getFilterChainResolver());
		filterChainResolver.setPathMatcher(new RegExPatternMatcher());
		// 对PathMatchingFilter子类使用RegExPatternMatcher
		for (Map.Entry<String, Filter> entry : filterChainResolver.getFilterChainManager().getFilters().entrySet()) {
			Filter filter = entry.getValue();
			if (PathMatchingFilter.class.isAssignableFrom(filter.getClass())) {
				try {
					Field field = PathMatchingFilter.class.getDeclaredField("pathMatcher");
					field.setAccessible(true);
					field.set(filter, new RegExPatternMatcher());
					field.setAccessible(false);
				} catch (SecurityException e) {
					// 反射异常不会出现
				} catch (IllegalArgumentException e) {
					// 反射异常不会出现
				} catch (NoSuchFieldException e) {
					// 反射异常不会出现
				} catch (IllegalAccessException e) {
					// 反射异常不会出现
				}
			}
		}
	}
}