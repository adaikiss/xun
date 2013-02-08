package org.adaikiss.xun.struts2.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;


/**
 * 将resultCode转换成lastpackage/actionname-resultCode, 
 * @author hlw
 *
 */
@SuppressWarnings("serial")
public class WrapResultInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(WrapResultInterceptor.class);

	private String basePackage = "org.adaikiss.xun.action";

	private String excludes = "";

	private Set<String> excludesSet;

	private static final String SPLIT = "-";

	@Override
	public void destroy() {
		excludesSet.clear();
	}

	@Override
	public void init() {
		excludesSet = new HashSet<String>();
		for (String exclude : excludes.split(",")) {
			excludesSet.add(exclude.trim());
		}
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		invocation.addPreResultListener(new PreResultListener() {
			public void beforeResult(ActionInvocation invocation, String resultCode) {
				if (isExclude(resultCode)) {
					return;
				}
				Class<?> actionClass = invocation.getAction().getClass();
				try {
					String resultPrefix = getResultPrefix(actionClass, basePackage);
					if (resultPrefix != null) {
						logger.debug("对返回视图名称{}添加前缀{}", resultCode, resultPrefix);
						invocation.setResultCode(resultPrefix + resultCode);
					}
				} catch (Exception e) {
					logger.error("封装返回视图名称" + resultCode + "时出现异常！", e);
				}
				return;
			}
		});
		return invocation.invoke();
	}

	private String getResultPrefix(Class<?> actionClass, String basePackage) {
		String packageName = actionClass.getPackage().getName();
		String packagePrefix = "";
		int trip1 = packageName.indexOf(basePackage);
		if (trip1 == -1 || packageName.equals(basePackage)) {
			return null;
		}
		packagePrefix = packageName.substring(trip1 + basePackage.length() + 1);
		return new StringBuilder().append(packagePrefix.replace(".", "/")).append("/")
				.append(formatActionName(actionClass.getSimpleName())).append(SPLIT).toString();
	}

	private static String formatActionName(String actionName) {
		StringBuilder sb = new StringBuilder();
		char[] cs = actionName.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == 'A') {
				if (cs.length > i + 5 && cs[i + 1] == 'c' && cs[i + 2] == 't' && cs[i + 3] == 'i' && cs[i + 4] == 'o'
						&& cs[i + 5] == 'n')
					break;
			}
			// 大写
			if (cs[i] > 64 && cs[i] < 91) {
				if (i != 0) {
					sb.append(SPLIT);
				}
				sb.append((char) (cs[i] + 32));
				continue;
			}
			sb.append(cs[i]);
		}
		return sb.toString();
	}

	private boolean isExclude(String resultCode) {
		if (resultCode == null || excludesSet.contains(resultCode)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(formatActionName("HelloWorldAction"));
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getExcludes() {
		return excludes;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}
}
