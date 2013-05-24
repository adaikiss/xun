/**
 * 
 */
package org.adaikiss.xun.core.util;

import org.adaikiss.xun.core.entity.Log;
import org.adaikiss.xun.core.repository.LogRepository;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.test.spring.SpringContextHolder;

/**
 * @author hlw
 *
 */
public class LogUtil {
	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
	private static LogRepository logRepository = null;
	/**
	 * 记录操作日志
	 * @param user 操作者
	 * @param action 操作类型
	 * @param target 操作对象
	 */
	public static void log(Object user, String action, String target){
		try {
			if(null == logRepository){
				logRepository = SpringContextHolder.getBean(LogRepository.class);
			}
			Log log = new Log(String.valueOf(user), action, target);
			logRepository.save(log);
		} catch (Exception e) {
			logger.error(new StringBuilder().append("记录操作日志失败![").append(user).append(" ").append(action).append(" ").append(target).append("]").toString(), e);
		}
	}

	/**
	 * 记录当前登录用户的操作日志
	 * @param action 操作类型
	 * @param target 操作对象
	 */
	public static void log(String action, String target){
		log(SecurityUtils.getSubject().getPrincipal(), action, target);
	}
}
