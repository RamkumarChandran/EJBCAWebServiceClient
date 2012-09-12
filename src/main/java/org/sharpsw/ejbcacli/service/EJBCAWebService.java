package org.sharpsw.ejbcacli.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.EjbcaWSService;
import org.sharpsw.ejbcacli.service.exception.ServiceCreationException;

/**
 * Base service class for other services available in the library
 * @author Anderson Ito
 * EJBCA namespace: http://ws.protocol.core.ejbca.org/
 * EJBCA local part: EjbcaWSService
 * EJBCA wsdl: https://localhost:8443/ejbca/ejbcaws/ejbcaws?wsdl
 */
public class EJBCAWebService {
	private EjbcaWS ws;
	private String nameSpace;
	private String localPart;
	private String wsdl;

	public EJBCAWebService() {
		this.nameSpace = "";
		this.localPart = "";
		this.wsdl = "";
	}
	
	public EJBCAWebService(String namespace, String localPart, String wsdl) throws ServiceCreationException {
		this.nameSpace = namespace;
		this.localPart = localPart;
		this.wsdl = wsdl;
		
		try {
			final QName qname = new QName(this.nameSpace, this.localPart);
			URL wsdlUrl = new URL(this.wsdl);			
			final EjbcaWSService service = new EjbcaWSService(wsdlUrl, qname);
			this.ws = service.getEjbcaWSPort();
		} catch (MalformedURLException exception) {
			throw new ServiceCreationException("An error has occured when creating the WSDL URL information.", exception);
		} catch (RuntimeException exception) {
			throw new ServiceCreationException("An runtime error has occured when creating the web-service.", exception);
		}
	}
	
	public void initialize() throws ServiceCreationException {
		try {
			final QName qname = new QName(this.nameSpace, this.localPart);
			URL wsdlUrl = new URL(this.wsdl);			
			final EjbcaWSService service = new EjbcaWSService(wsdlUrl, qname);
			this.ws = service.getEjbcaWSPort();
		} catch (MalformedURLException exception) {
			throw new ServiceCreationException("An error has occured when creating the WSDL URL information.", exception);
		} catch (RuntimeException exception) {
			throw new ServiceCreationException("An runtime error has occured when creating the web-service.", exception);
		}
	}
		
	public EjbcaWS getService() {
		return this.ws;
	}
}
