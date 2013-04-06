/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.bridge;

import java.util.List;

/**
 * @author hlw
 *
 */
public abstract class MailProvider {
	public abstract List<Mail> getMailList();
	public abstract void sendMail(Mail mail);
}
