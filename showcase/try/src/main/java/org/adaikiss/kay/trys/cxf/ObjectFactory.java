
package org.adaikiss.kay.trys.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.chinamobile.openmas.entity.xsd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeliveryReportMessageId_QNAME = new QName("http://entity.openmas.chinamobile.com/xsd", "messageId");
    private final static QName _DeliveryReportMessageDeliveryStatus_QNAME = new QName("http://entity.openmas.chinamobile.com/xsd", "messageDeliveryStatus");
    private final static QName _DeliveryReportReceivedAddress_QNAME = new QName("http://entity.openmas.chinamobile.com/xsd", "receivedAddress");
    private final static QName _DeliveryReportSendAddress_QNAME = new QName("http://entity.openmas.chinamobile.com/xsd", "sendAddress");
    private final static QName _DeliveryReportStatusCode_QNAME = new QName("http://entity.openmas.chinamobile.com/xsd", "statusCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.chinamobile.openmas.entity.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeliveryReport }
     * 
     */
    public DeliveryReport createDeliveryReport() {
        return new DeliveryReport();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.openmas.chinamobile.com/xsd", name = "messageId", scope = DeliveryReport.class)
    public JAXBElement<String> createDeliveryReportMessageId(String value) {
        return new JAXBElement<String>(_DeliveryReportMessageId_QNAME, String.class, DeliveryReport.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.openmas.chinamobile.com/xsd", name = "messageDeliveryStatus", scope = DeliveryReport.class)
    public JAXBElement<String> createDeliveryReportMessageDeliveryStatus(String value) {
        return new JAXBElement<String>(_DeliveryReportMessageDeliveryStatus_QNAME, String.class, DeliveryReport.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.openmas.chinamobile.com/xsd", name = "receivedAddress", scope = DeliveryReport.class)
    public JAXBElement<String> createDeliveryReportReceivedAddress(String value) {
        return new JAXBElement<String>(_DeliveryReportReceivedAddress_QNAME, String.class, DeliveryReport.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.openmas.chinamobile.com/xsd", name = "sendAddress", scope = DeliveryReport.class)
    public JAXBElement<String> createDeliveryReportSendAddress(String value) {
        return new JAXBElement<String>(_DeliveryReportSendAddress_QNAME, String.class, DeliveryReport.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entity.openmas.chinamobile.com/xsd", name = "statusCode", scope = DeliveryReport.class)
    public JAXBElement<String> createDeliveryReportStatusCode(String value) {
        return new JAXBElement<String>(_DeliveryReportStatusCode_QNAME, String.class, DeliveryReport.class, value);
    }

}
