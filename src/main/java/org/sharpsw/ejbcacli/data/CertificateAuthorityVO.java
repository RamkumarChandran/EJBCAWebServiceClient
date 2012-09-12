package org.sharpsw.ejbcacli.data;

import java.io.Serializable;

/**
 * This class represents the information related to a certiifcate authority
 * registered in the EJBCA system.
 * @author Anderson Ito (andersonkmi@acm.org)
 *
 */
public class CertificateAuthorityVO implements Serializable {	
	private static final long serialVersionUID = 5476917561787524022L;
	
	private String name = "";
	private Integer id = new Integer(0);
	
	/**
	 * Class constructor that receives as parameters the CA id number and the CA name
	 * @param id Integer number representing the identification number
	 * @param name String containing the CA name
	 */
	public CertificateAuthorityVO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Sets the certificate authority id number.
	 * @param id Number representing the identification number of the CA.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the CA id number.
	 * @return Identification number.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the CA name.
	 * @param name String containing the Certificate authority name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the certificate authority name.
	 * @return String containing the certificate authority name.
	 */
	public String getName() {
		return this.name;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[id = '").append(getId()).append("'; name = '").append(getName()).append("']");
		return buffer.toString();
	}
}
