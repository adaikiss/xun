/**
 * 
 */
package org.adaikiss.xun;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author hlw
 * 
 */
public class JavaServiceTaskTest {

	@Test
	public void bookAvalaible() {
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneInMemProcessEngineConfiguration()
				.buildProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment()
				.addClasspathResource("bookorder.bpmn20.xml").deploy();
		Map<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put("bookTitle", "BPMN 2.0 with Activiti");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(
				"bookorder", processVariables);
		processVariables = runtimeService.getVariables(pi.getId());
		Assert.assertEquals(true, processVariables.get("bookAvailable"));
	}
}
