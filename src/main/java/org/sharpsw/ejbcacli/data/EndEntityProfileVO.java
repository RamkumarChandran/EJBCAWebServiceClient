package org.sharpsw.ejbcacli.data;

import java.io.Serializable;

/**
 * This class represents an end entity profile in the context of the EJBCA server.
 * @author andersonkmi
 *
 */
public class EndEntityProfileVO implements Serializable {
	private static final long serialVersionUID = -3351088461215109497L;

	private Integer id;
	private String name;
	
	/**
	 * Default constructor.
	 */
	public EndEntityProfileVO() {
		this.id = new Integer(0);
		this.name = "";
	}
	
	/**
	 * Class constructor that initializes an instance using the supplied arguments.
	 * @param id Id number.
	 * @param name End entity profile name.
	 */
	public EndEntityProfileVO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Sets the end entity profile id.
	 * @param id Integer number representing the id information.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the id number information.
	 * @return Id number
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the end entity profile name information.
	 * @param name String representing the profile name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the profile name information.
	 * @return String containing the profile name.
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
