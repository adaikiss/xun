/**
 * 
 */
package org.adaikiss.xun;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author hlw
 * 
 */
public class CheckAvailability implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String bookTitle = (String) execution.getVariable("bookTitle");
		boolean bookAvailable = false;
		if (bookTitle.equalsIgnoreCase("BPMN 2.0 with Activiti")) {
			bookAvailable = true;
		}
		execution.setVariable("bookAvailable", bookAvailable);
		execution.setVariable("result", 9);
	}

}
