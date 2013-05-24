/**
 * 
 */
package org.adaikiss.xun.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author hlw
 * 
 */
public class XunPortlet extends GenericPortlet {

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		renderResponse(request, response, "/jsp/view.jsp");
	}

	@Override
	protected void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		renderResponse(request, response, "/jsp/edit.jsp");
	}

	@Override
	protected void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		renderResponse(request, response, "/jsp/help.jsp");
	}

	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		super.processAction(request, response);
	}

	private void renderResponse(RenderRequest request, RenderResponse response,
			String path) throws PortletException, IOException {
		response.setContentType("text/html");
		PortletRequestDispatcher prd = getPortletContext()
				.getRequestDispatcher(path);
		prd.include(request, response);
	}
}
