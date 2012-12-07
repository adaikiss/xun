package org.adaikiss.xun;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 * 
 * copied from SpringSide3
 *
 */
public class Start {
	public static final int PORT = 80;
	public static final String CONTEXT = "/";
	public static final String BASE_URL = "http://localhost:80/";

	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(PORT, CONTEXT);
		server.start();

		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
	}

	/**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		webContext.setDefaultsDescriptor("src/main/webapp/WEB-INF/webdefault.xml");
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}

	/**
	 * 创建用于Functional Test的Jetty Server:
	 * 1.以src/main/webapp为Web应用目录.
	 * 2.以test/resources/web.xml指向applicationContext-test.xml创建测试环境.
	 */
	public static Server buildTestServer(int port, String contextPath) {
		Server server = buildNormalServer(port, contextPath);
		((WebAppContext) server.getHandler()).setDescriptor("src/test/resources/web.xml");
		return server;
	}

}

