/**
 * 上午11:33:34
 */
package org.adaikiss.kay.trys.tij;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * hlw
 * 
 */
public class ProxyTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NoSuchMethodException,
			Throwable {
		ProxyTry tr = new ProxyTry();
		CustomProxy cp = tr.new CustomProxy();
		DynamicProxyHandler handler = tr.new DynamicProxyHandler(cp);
		Object result = handler.invoke(cp,
				Interface.class.getDeclaredMethod("consume", String.class),
				new Object[] { "hello!" });
		System.out.println(result);
		Interface proxy = (Interface) Proxy.newProxyInstance(
				Interface.class.getClassLoader(),
				new Class[] { Interface.class }, handler);
		result = proxy.consume("hi!");
		System.out.println(result);
	}

	class DynamicProxyHandler implements InvocationHandler {
		private Object proxie;
		public DynamicProxyHandler(Object proxie){
			this.proxie = proxie;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("before call...");
			try {
				Object result = method.invoke(proxie, args);
				System.out.println("after return...");
				return result;
			} catch (Exception e) {
				System.out.println("after throwing...");
				throw e;
			}
		}
	}

	class CustomProxy implements Interface {
		@Override
		public String consume(String arg) {
			return ">>>" + arg;
		}
	}

	interface Interface {
		public String consume(String arg);
	}

}
