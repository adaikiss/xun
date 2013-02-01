/**
 * 
 */
package org.adaikiss.xun.repository;

import java.util.List;

public interface ChatRepository {

	List<String> getMessages(int messageIndex);

	void addMessage(String message);

}

