/**
 * 
 */
package org.adaikiss.xun.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.adaikiss.xun.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author hlw
 * 
 */
@Controller
@RequestMapping("/mvc/chat")
public class ChatController {
	private final Map<DeferredResult<List<String>>, Integer> chatRequests = new ConcurrentHashMap<DeferredResult<List<String>>, Integer>();

	@Autowired
	private ChatRepository chatRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<List<String>> getMessages(
			@RequestParam int messageIndex) {

		final DeferredResult<List<String>> deferredResult = new DeferredResult<List<String>>(null, Collections.emptyList());
		this.chatRequests.put(deferredResult, messageIndex);

		deferredResult.onCompletion(new Runnable() {
			@Override
			public void run() {
				chatRequests.remove(deferredResult);
			}
		});

		List<String> messages = this.chatRepository.getMessages(messageIndex);
		if (!messages.isEmpty()) {
			deferredResult.setResult(messages);
		}

		return deferredResult;
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void postMessage(@RequestParam String message) {

		this.chatRepository.addMessage(message);

		// Update all chat requests as part of the POST request
		// See Redis branch for a more sophisticated, non-blocking approach

		for (Entry<DeferredResult<List<String>>, Integer> entry : this.chatRequests.entrySet()) {
			List<String> messages = this.chatRepository.getMessages(entry.getValue());
			entry.getKey().setResult(messages);
		}
	}
}
