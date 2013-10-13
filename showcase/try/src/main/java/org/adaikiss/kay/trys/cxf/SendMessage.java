package org.adaikiss.kay.trys.cxf;

import javax.jws.WebService;

@WebService
public interface SendMessage {
	public String sendFreeMessage(String usernumber, String password,
			String mobiles, String message, String sendtime);
}
