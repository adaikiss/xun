package org.adaikiss.xun.servlet3;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.NetworkTrafficSelectChannelConnector;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 
 * copied from SpringSide3
 * 
 */
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
		Server server = new Server();
		ServerConnector connector = new NetworkTrafficSelectChannelConnector(server);
		connector.setPort(port);
		server.addConnector(connector);

		ContextHandlerCollection contexts = new ContextHandlerCollection();
		server.setHandler(contexts);

		WebAppContext webContext = new WebAppContext("src/main/webapp",
				contextPath);
		webContext
				.setDefaultsDescriptor("src/main/webapp/WEB-INF/webdefault.xml");
		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webContext.setConfigurations(new Configuration[]{new AnnotationConfiguration()});
		//webContext.setParentLoaderPriority(true);
		//webContext.addServlet(AsyncServlet.class, "/async.svl");
		contexts.addHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}

}
