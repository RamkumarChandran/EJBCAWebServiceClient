package org.sharpsw.ejbcacli.data;

import java.io.Serializable;

/**
 * This class represents a certificate profile information in the context of the 
 * EJBCA server.
 * @author andersonkmi
 *
 */
public class CertificateProfileVO implements Serializable {
	private static final long serialVersionUID = -2312322508341360594L;

	private Integer id;
	private String name;
	
	/**
	 * Default class constructor. Initializes the fields to defaults.
	 */
	public CertificateProfileVO() {
		this.id = new Integer(0);
		this.name = "";
	}
	
	/**
	 * Class constructor that initializes an instance using the supplied arguments.
	 * @param id Certificate profile number
	 * @param name Certificate profile name.
	 */
	public CertificateProfileVO(int id, String name) {
		this.id = new Integer(id);
		this.name = name;
	}
	
	/**
	 * Sets a new id number.
	 * @param id Id number.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the certificate profile id number.
	 * @return Integer number representing the certificate profile number.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the certificate profile name information.
	 * @param name New certificate profile name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the certificate profile name information.
	 * @return String containing the certificate profile name.
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
