/**
 * 
 */
package org.adaikiss.kay.trys.cxf;

import javax.jws.WebService;

/**
 * @author hlw
 *
 */
@WebService
public interface SmsReceiveService {
	public String NotifySmsDeliveryReport(DeliveryReport deliveryReport);
}
