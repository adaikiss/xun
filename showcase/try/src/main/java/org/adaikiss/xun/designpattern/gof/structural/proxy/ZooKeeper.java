/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hlw
 * 
 */
public class ZooKeeper implements java.lang.reflect.InvocationHandler {

	private Zoo zoo;

	public ZooKeeper(Zoo zoo) {
		this.zoo = zoo;
	}

	public Zoo getZoo() {
		return (Zoo) java.lang.reflect.Proxy.newProxyInstance(zoo.getClass()
				.getClassLoader(), zoo.getClass().getInterfaces(),
				new ZooKeeper(zoo));
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result;
		try {
			System.out.println(args[0] + " entering " + zoo.getName() + ", need a ticket!");
			result = method.invoke(zoo, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: "
					+ e.getMessage());
		} finally {
			System.out.println(args[0] + " leaving " + zoo.getName() + ", see you!");
		}
		return result;
	}

}
