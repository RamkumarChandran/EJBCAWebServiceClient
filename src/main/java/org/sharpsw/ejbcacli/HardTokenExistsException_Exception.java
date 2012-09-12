
package org.sharpsw.ejbcacli;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "HardTokenExistsException", targetNamespace = "http://ws.protocol.core.ejbca.org/")
public class HardTokenExistsException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2859527332181296181L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private HardTokenExistsException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public HardTokenExistsException_Exception(String message, HardTokenExistsException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public HardTokenExistsException_Exception(String message, HardTokenExistsException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.sharpsw.ejbcacli.HardTokenExistsException
     */
    public HardTokenExistsException getFaultInfo() {
        return faultInfo;
    }

}