/**
 * 
 */
package org.adaikiss.xun.servlet3.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hlw
 *
 */
@WebServlet(name = "ChatServlet", urlPatterns = { "/chat" }, asyncSupported = true)
@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {

	private Map<AsyncContext, Integer> asyncContexts = new ConcurrentHashMap<AsyncContext, Integer>();

	private MessageRepository<Message> repo = new MessageRepository<Message>();

	private ThreadLocal<AsyncContext> ctx = new ThreadLocal<AsyncContext>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("index"));
		response.setCharacterEncoding("utf-8");
		// Content-Type header
		response.setContentType("application/json");
		final AsyncContext ac = request.startAsync();
		ctx.set(ac);
		ac.setTimeout(5 * 60 * 1000);
		ac.addListener(new AsyncListener() {
			public void onComplete(AsyncEvent event) throws IOException {
				quit();
			}

			public void onTimeout(AsyncEvent event) throws IOException {
				quit();
			}

			public void onError(AsyncEvent event) throws IOException {
				quit();
			}

			public void onStartAsync(AsyncEvent event) throws IOException {

			}
		});
		asyncContexts.put(ac, index);
	}

	private void quit(){
		AsyncContext ac = ctx.get();
		asyncContexts.remove(ac);
		ctx.remove();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if ("close".equals(request.getParameter("type"))) {
			AsyncContext ac = ctx.get();
			if (ac != null) {
				ac.complete();
			}
			return;
		}
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		repo.add(new Message(name, content));
		notifyMsg();
	}

	private void notifyMsg(){
		for(Map.Entry<AsyncContext, Integer> entry : asyncContexts.entrySet()){
			AsyncContext asyncContext = entry.getKey();
			Integer index = entry.getValue();
			asyncContexts.remove(asyncContext);
			List<Message> msgs = repo.get(index);
			write(asyncContext.getResponse(), msgs);
			asyncContext.complete();
		}
	}

	private void write(ServletResponse response, List<Message> msgs){
		try {
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(writer, msgs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
