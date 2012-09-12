package org.sharpsw.ejbcacli.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.sharpsw.ejbcacli.ApprovalException_Exception;
import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.CADoesntExistsException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.EjbcaWSService;
import org.sharpsw.ejbcacli.UserDataVOWS;
import org.sharpsw.ejbcacli.UserDoesntFullfillEndEntityProfile_Exception;
import org.sharpsw.ejbcacli.WaitingForApprovalException_Exception;
import org.sharpsw.ejbcacli.configuration.Configuration;
import org.sharpsw.ejbcacli.configuration.SSLEnvSetupException;

public class MainService {
	private Configuration config;
	
	public MainService() {
		this.config = new Configuration("truststore.jks", "anderson", "superadmin.p12", "anderson");
		try {
			this.config.configureSSL();
		} catch (SSLEnvSetupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void execute() throws MalformedURLException, AuthorizationDeniedException_Exception, EjbcaException_Exception, ApprovalException_Exception, CADoesntExistsException_Exception, UserDoesntFullfillEndEntityProfile_Exception, WaitingForApprovalException_Exception {
		final QName qname = new QName("http://ws.protocol.core.ejbca.org/", "EjbcaWSService");
		URL wsdlUrl = new URL("https://localhost:8443/ejbca/ejbcaws/ejbcaws?wsdl");
		final EjbcaWSService service = new EjbcaWSService(wsdlUrl, qname);
 		EjbcaWS webService = service.getEjbcaWSPort();
		//List<NameAndId> certAuths = webService.getAvailableCAs();
		
		UserDataVOWS endUser = new UserDataVOWS();
		endUser.setUsername("castec");
		endUser.setPassword("anderson");
		endUser.setCaName("SharpSW End-user CA");
		endUser.setEndEntityProfileName("End-user");
		endUser.setSubjectDN("CN=Teste,OU=Home,O=Home,C=BR");
		endUser.setCertificateProfileName("SharpSW End-user AC");
		endUser.setSubjectAltName("RFC822NAME=teste@teste.com");
		endUser.setEmail("teste@teste.com");
		endUser.setStatus(10);
		endUser.setTokenType("USERGENERATED");
		endUser.setClearPwd(true);
		
		webService.editUser(endUser);
	}
}
