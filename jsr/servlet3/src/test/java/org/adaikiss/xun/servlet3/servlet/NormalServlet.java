/**
 * 
 */
package org.adaikiss.xun.servlet3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hlw
 *
 */
@WebServlet(name = "NormalServlet", urlPatterns = { "/normal.svl" }, asyncSupported = false)
@SuppressWarnings("serial")
public class NormalServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(req.getParameter("a"));
		System.out.println(req.getParameter("b"));
	}
	
}
