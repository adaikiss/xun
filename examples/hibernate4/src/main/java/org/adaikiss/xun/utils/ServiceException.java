/**
 * 
 */
package org.adaikiss.xun.utils;

/**
 * @author hlw
 * 
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3545505592524875927L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(String message, Throwable t) {
		super(message, t);
	}
}
