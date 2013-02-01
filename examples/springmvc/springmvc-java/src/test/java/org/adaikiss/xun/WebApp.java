/**
 * 
 */
package org.adaikiss.xun;

import org.adaikiss.xun.mvc.config.AppConfig;
import org.adaikiss.xun.mvc.config.WebMvcConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author hlw
 * 
 */
public class WebApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		final AnnotationConfigWebApplicationContext dispatcherContext  = new AnnotationConfigWebApplicationContext();
		dispatcherContext .register(WebMvcConfig.class);
        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(dispatcherContext ));

		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		context.setResourceBase("src/main/webapp");
		context.addEventListener(new ContextLoaderListener(rootContext));
		context.addServlet(servletHolder, "/*");
		int webPort = 80;

		final Server server = new Server(Integer.valueOf(webPort));

		server.setStopAtShutdown(true);

		server.setHandler(context);

		server.start();
		//server.join();
		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
	}

}
