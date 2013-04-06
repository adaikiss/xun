/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.bridge;

/**
 * @author hlw
 * 
 */
public class SinaMailBox extends MailBox {

	private MailProvider mailProvider;

	public SinaMailBox(MailProvider mailProvider) {
		this.mailProvider = mailProvider;
	}

	@Override
	public MailProvider getMailProvider() {
		return this.mailProvider;
	}

}
