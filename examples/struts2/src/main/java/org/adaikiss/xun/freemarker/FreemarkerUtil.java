package org.adaikiss.xun.freemarker;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.DeepUnwrap;

/**
 * @author hlw
 * 
 */
public class FreemarkerUtil {
	/**
	 * 将模板文件中的参数对应值转换为String类型
	 * 
	 * @param key
	 *            参数名称
	 * @param map
	 *            参数名称和值
	 * @return
	 * @throws TemplateModelException
	 */
	public static String parseString(String key, Map<String, TemplateModel> map) throws TemplateModelException {
		TemplateModel templateModel = map.get(key);
		if (templateModel == null)
			return null;
		if (templateModel instanceof TemplateScalarModel)
			return ((TemplateScalarModel) templateModel).getAsString();
		if (templateModel instanceof TemplateNumberModel)
			return ((TemplateNumberModel) templateModel).getAsNumber().toString();
		throw new TemplateModelException("The \"" + key + "\" parameter must be a string.");
	}

	/**
	 * 将模板文件中的参数对应值转换为Integer类型
	 * 
	 * @param key
	 *            参数名称
	 * @param map
	 *            参数名称和值
	 * @return
	 * @throws TemplateModelException
	 */
	public static Integer parseInteger(String key, Map<String, TemplateModel> map) throws TemplateModelException {
		TemplateModel templateModel = (TemplateModel) map.get(key);
		if (templateModel == null)
			return null;
		if (templateModel instanceof TemplateScalarModel) {
			String str = ((TemplateScalarModel) templateModel).getAsString();
			if (StringUtils.isEmpty(str))
				return null;
			return Integer.valueOf(str);
		}
		if (templateModel instanceof TemplateNumberModel)
			return Integer.valueOf(((TemplateNumberModel) templateModel).getAsNumber().intValue());
		throw new TemplateModelException("The \"" + key + "\" parameter must be a integer.");
	}

	public static Integer parseInteger(String key, Map<String, TemplateModel> map, Integer defaultValue) {
		Integer value;
		try {
			value = parseInteger(key, map);
			if (null != value) {
				return value;
			}
		} catch (TemplateModelException e) {
		}
		return defaultValue;
	}

	/**
	 * 将模板文件中的参数对应值转换为Integer类型
	 * 
	 * @param key
	 *            参数名称
	 * @param map
	 *            参数名称和值
	 * @return
	 * @throws TemplateModelException
	 */
	public static Long parseLong(String key, Map<String, TemplateModel> params) throws TemplateModelException {
		TemplateModel templateModel = (TemplateModel) params.get(key);
		if (templateModel == null)
			return null;
		if (templateModel instanceof TemplateScalarModel) {
			String str = ((TemplateScalarModel) templateModel).getAsString();
			if (StringUtils.isEmpty(str))
				return null;
			return Long.valueOf(str);
		}
		if (templateModel instanceof TemplateNumberModel)
			return Long.valueOf(((TemplateNumberModel) templateModel).getAsNumber().intValue());
		throw new TemplateModelException("The \"" + key + "\" parameter must be a Long.");
	}

	public static BigDecimal parseBigDecimal(String key, Map<String, TemplateModel> map) throws TemplateModelException {
		TemplateModel templateModel = (TemplateModel) map.get(key);
		if (templateModel == null)
			return null;
		if (templateModel instanceof TemplateScalarModel) {
			String str = ((TemplateScalarModel) templateModel).getAsString();
			if (StringUtils.isEmpty(str))
				return null;
			return new BigDecimal(str);
		}
		if (templateModel instanceof TemplateNumberModel)
			return BigDecimal.valueOf(((TemplateNumberModel) templateModel).getAsNumber().doubleValue());
		throw new TemplateModelException("The \"" + key + "\" parameter must be a bigdecimal.");
	}

	/**
	 * 将模板文件中的参数对应值转换为Boolean类型
	 * 
	 * @param key
	 *            参数名称
	 * @param map
	 *            参数名称和值
	 * @return
	 * @throws TemplateModelException
	 */
	public static Boolean parseBoolean(String key, Map<String, TemplateModel> map) throws TemplateModelException {
		TemplateModel templateModel = (TemplateModel) map.get(key);
		if (templateModel == null)
			return null;
		if (templateModel instanceof TemplateScalarModel) {
			String str = ((TemplateScalarModel) templateModel).getAsString();
			if (StringUtils.isEmpty(str))
				return null;
			return Boolean.valueOf(str);
		}
		if (templateModel instanceof TemplateBooleanModel)
			return Boolean.valueOf(((TemplateBooleanModel) templateModel).getAsBoolean());
		throw new TemplateModelException("The \"" + key + "\" parameter must be a boolean.");
	}

	/**
	 * 将模板文件中的参数对应值转换为java.util.Date类型
	 * 
	 * @param key
	 *            参数名称
	 * @param map
	 *            参数名称和值
	 * @return
	 * @throws TemplateModelException
	 */
	public static Date parseDate(String key, Map<String, TemplateModel> map) throws TemplateModelException {
		TemplateModel templateModel = (TemplateModel) map.get(key);
		if (templateModel == null)
			return null;
		if (templateModel instanceof TemplateScalarModel) {
			String str = ((TemplateScalarModel) templateModel).getAsString();
			if (StringUtils.isEmpty(str))
				return null;
			try {
				String[] dataFormats = { "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
						"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss" };
				return DateUtils.parseDate(str, dataFormats);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		if (templateModel instanceof TemplateDateModel)
			return ((TemplateDateModel) templateModel).getAsDate();
		throw new TemplateModelException("The \"" + key + "\" parameter must be a date.");
	}

	/**
	 * 将模板文件中的参数对应值转换为Object类型
	 * 
	 * @param key
	 *            参数名称
	 * @param map
	 *            参数名称和值
	 * @return
	 * @throws TemplateModelException
	 */
	public static Object parseObject(String key, Map<String, TemplateModel> map) throws TemplateModelException {
		TemplateModel templateModel = (TemplateModel) map.get(key);
		if (templateModel == null)
			return null;
		try {
			return DeepUnwrap.unwrap(templateModel);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		throw new TemplateModelException("The \"" + key + "\" parameter must be a object.");
	}

}
