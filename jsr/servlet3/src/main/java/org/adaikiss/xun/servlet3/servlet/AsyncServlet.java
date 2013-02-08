/**
 * 
 */
package org.adaikiss.xun.servlet3.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hlw
 * 
 */
@WebServlet(name = "AsyncServlet", urlPatterns = { "/async.svl" }, asyncSupported = true)
@SuppressWarnings("serial")
public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		final PrintWriter writer = res.getWriter();
		writer.println("Servlet begin <br>");

		final AsyncContext asyncContext = req.startAsync();
		asyncContext.start(new Thread(){

			@Override
			public void run() {
				try {
					writer.println("get writer from parameter<br>");
					writer.flush();
					PrintWriter out = asyncContext.getResponse().getWriter();
					out.println("get writer from asyncContext<br>");
					out.flush();
					for(int i = 0;i < 10;i++){
						writer.println("param " + i + "<br>");
						writer.flush();
						out.println("asyncContext " + i + "<br>");
						out.flush();
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				writer.println("outer thread completed!<br>");
				asyncContext.complete();
				writer.println("will not print?no!<br>");
			}
		});
		writer.println("Servlet end <br>");
		writer.flush();
	}
}
