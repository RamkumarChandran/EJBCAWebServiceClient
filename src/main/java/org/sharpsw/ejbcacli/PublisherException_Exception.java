
package org.sharpsw.ejbcacli;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "PublisherException", targetNamespace = "http://ws.protocol.core.ejbca.org/")
public class PublisherException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4207648538871917204L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PublisherException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public PublisherException_Exception(String message, PublisherException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public PublisherException_Exception(String message, PublisherException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.sharpsw.ejbcacli.PublisherException
     */
    public PublisherException getFaultInfo() {
        return faultInfo;
    }

}
