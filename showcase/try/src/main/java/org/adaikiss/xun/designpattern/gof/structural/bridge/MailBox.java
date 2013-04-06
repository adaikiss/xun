/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.bridge;

import java.util.List;

/**
 * @author hlw
 *
 */
public abstract class MailBox {
	public abstract MailProvider getMailProvider();
	public void readMail(){
		List<Mail> mails = getMailProvider().getMailList();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(Mail mail : mails){
			System.out.println(mail);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public void sendMail(Mail mail){
		getMailProvider().sendMail(mail);
	}
}
