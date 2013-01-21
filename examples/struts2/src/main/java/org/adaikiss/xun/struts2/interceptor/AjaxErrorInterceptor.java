package org.adaikiss.xun.struts2.interceptor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.adaikiss.xun.web.AjaxResponse;
import org.adaikiss.xun.web.Ajaxable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * 对于Ajax请求，validation error直接输出！
 * @author hlw
 *
 */
@SuppressWarnings("serial")
public class AjaxErrorInterceptor extends AbstractInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(AjaxErrorInterceptor.class);

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();

		// 只对实现了ValidationAware和Ajaxable接口的类处理
		if (action instanceof ValidationAware && action instanceof Ajaxable) {
			ValidationAware validationAwareAction = (ValidationAware) action;
			Ajaxable ajaxable = (Ajaxable) action;
			boolean isAjax = ajaxable.isAjax();
			if (isAjax && validationAwareAction.hasErrors()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Errors on action " + validationAwareAction
							+ ", is an ajax request!");
				}
				AjaxResponse res = new AjaxResponse();
				res.setMsg(getMessageFromValidationAware(validationAwareAction));
				res.writeJson(ajaxable.getResponse());
				return null;
			}
		}
		return invocation.invoke();
	}

	private String getMessageFromValidationAware(ValidationAware validationAware) {
		StringBuilder sb = new StringBuilder();
		Collection<String> actionErrors = validationAware.getActionErrors();
		Map<String, List<String>> fieldErrors = validationAware
				.getFieldErrors();
		for (String actionError : actionErrors) {
			if (logger.isDebugEnabled()) {
				logger.debug("actionError : " + actionError);
			}
			sb.append(actionError);
		}
		for (Map.Entry<String, List<String>> entry : fieldErrors.entrySet()) {
			if (logger.isDebugEnabled()) {
				logger.debug("field : " + entry.getKey() + ", errors : {");
			}
			for (String fieldError : entry.getValue()) {
				logger.debug(fieldError);
				sb.append(fieldError);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("}\n");
			}
		}
		return sb.toString();
	}
}
