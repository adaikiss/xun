/**
 * 
 */
package org.adaikiss.xun.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hlw
 *
 */
@WebServlet(urlPatterns = {"/hero.svl"})
@SuppressWarnings("serial")
public class HeroServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter writer = res.getWriter();
		writer.write("<h3>Hello world!</h3>");
		writer.flush();
	}
	
}
