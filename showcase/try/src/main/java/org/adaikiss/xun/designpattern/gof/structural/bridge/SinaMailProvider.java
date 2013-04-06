/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.bridge;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * @author hlw
 *
 */
public class SinaMailProvider extends MailProvider {

	private LinkedList<Mail> received = new LinkedList<Mail>();

	public SinaMailProvider(){
		received.add(new Mail("Welcome", "Thank you to use Sina Mail!", new Date()));
	}

	@Override
	public List<Mail> getMailList() {
		return Collections.unmodifiableList(received);
	}

	@Override
	public void sendMail(Mail mail) {
		received.addFirst(reply(mail));
	}

	private Mail reply(Mail mail){
		return new Mail("RE:" + mail.getTitle(), "Send your sister!", new Date(), mail);
	}
}
