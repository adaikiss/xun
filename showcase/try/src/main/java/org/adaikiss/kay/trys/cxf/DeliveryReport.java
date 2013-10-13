
package org.adaikiss.kay.trys.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeliveryReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeliveryReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageDeliveryStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliveryReport", propOrder = {
    "messageDeliveryStatus",
    "messageId",
    "receivedAddress",
    "sendAddress",
    "statusCode"
})
public class DeliveryReport {

    @XmlElementRef(name = "messageDeliveryStatus", namespace = "http://entity.openmas.chinamobile.com/xsd", type = JAXBElement.class)
    protected JAXBElement<String> messageDeliveryStatus;
    @XmlElementRef(name = "messageId", namespace = "http://entity.openmas.chinamobile.com/xsd", type = JAXBElement.class)
    protected JAXBElement<String> messageId;
    @XmlElementRef(name = "receivedAddress", namespace = "http://entity.openmas.chinamobile.com/xsd", type = JAXBElement.class)
    protected JAXBElement<String> receivedAddress;
    @XmlElementRef(name = "sendAddress", namespace = "http://entity.openmas.chinamobile.com/xsd", type = JAXBElement.class)
    protected JAXBElement<String> sendAddress;
    @XmlElementRef(name = "statusCode", namespace = "http://entity.openmas.chinamobile.com/xsd", type = JAXBElement.class)
    protected JAXBElement<String> statusCode;

    /**
     * Gets the value of the messageDeliveryStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessageDeliveryStatus() {
        return messageDeliveryStatus;
    }

    /**
     * Sets the value of the messageDeliveryStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessageDeliveryStatus(JAXBElement<String> value) {
        this.messageDeliveryStatus = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessageId(JAXBElement<String> value) {
        this.messageId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the receivedAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReceivedAddress() {
        return receivedAddress;
    }

    /**
     * Sets the value of the receivedAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReceivedAddress(JAXBElement<String> value) {
        this.receivedAddress = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the sendAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSendAddress() {
        return sendAddress;
    }

    /**
     * Sets the value of the sendAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSendAddress(JAXBElement<String> value) {
        this.sendAddress = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusCode(JAXBElement<String> value) {
        this.statusCode = ((JAXBElement<String> ) value);
    }

}
