package org.adaikiss.xun.cms;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class App {
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

	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		webContext.setDefaultsDescriptor("src/main/webapp/WEB-INF/webdefault.xml");
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}

}
