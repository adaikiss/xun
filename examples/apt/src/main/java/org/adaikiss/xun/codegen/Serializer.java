/**
 * 
 */
package org.adaikiss.xun.codegen;

import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hlw
 * 
 */
public abstract class Serializer {

	protected static final String TPL_PATH = "META-INF/templates/";

	protected String tplStr;

	public Serializer init(){
		//cache the template file content to a field.
		this.tplStr = getTemplate();
		return this;
	}

	/**
	 * serialize
	 */
	public abstract void serialize(Model model, Writer writer);

	abstract String getTemplateFileName();

	String getTemplate() {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(TPL_PATH + getTemplateFileName());
			byte[] buff = new byte[is.available()];
			is.read(buff);
			is.close();
			return new String(buff, Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * get replacement for reg</br> ie:<code>${name}</code> will invoke
	 * <code>serializeName()</code> method of caller.
	 * 
	 * @param reg
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	protected String getReplacement(String reg) throws InvocationTargetException, IllegalAccessException,
			NoSuchMethodException {
		String methodName = "serialize" + reg.substring(2, 3).toUpperCase() + reg.substring(3, reg.length() - 1);
		return (String) this.getClass().getDeclaredMethod(methodName).invoke(this);
	}

	/**
	 * replace the placeholder of the template file string
	 * 
	 * @param tpl
	 * @param pattern
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	protected StringBuffer replace(String tpl, Pattern pattern) throws InvocationTargetException,
			IllegalAccessException, NoSuchMethodException {
		Matcher matcher = pattern.matcher(tpl);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, getReplacement(matcher.group(0)));
		}
		matcher.appendTail(sb);
		return sb;
	}

	private static class TestSerializer extends Serializer {

		@SuppressWarnings("unused")
		String serializeAaa() {
			return "_aaa_";
		}

		@SuppressWarnings("unused")
		String serializeBbb() {
			return "_bbb_";
		}

		@SuppressWarnings("unused")
		String serializeCcc() {
			return "_ccc_";
		}

		@Override
		public void serialize(Model model, Writer writer) {

		}

		@Override
		String getTemplateFileName() {
			return "test.tpl";
		}

	}

	public static void main(String[] args) throws Exception {
		TestSerializer s = new TestSerializer();
		StringBuffer sb = s.replace(s.getTemplate(), Pattern.compile("\\$\\{[a-zA-Z]+\\}", Pattern.MULTILINE));
		System.out.println(sb);
	}
}
