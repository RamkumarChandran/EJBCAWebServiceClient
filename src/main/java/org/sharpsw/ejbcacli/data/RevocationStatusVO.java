package org.sharpsw.ejbcacli.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class represents the revocation status information for a given
 * certificate.
 * @author andersonkmi
 *
 */
public class RevocationStatusVO implements Serializable {
	private static final long serialVersionUID = -5867630412495561148L;
	private RevocationReason reason;
	private Calendar revocationDate;
	private String serialNumber = "";
	
	/**
	 * Default class constructor. Initializes the instance with default values.
	 */
	public RevocationStatusVO() {
		this.reason = RevocationReason.NOT_REVOKED;
		this.revocationDate = Calendar.getInstance();
	}
	
	/**
	 * Sets the serial number information.
	 * @param serialNumber Certificate serial number.
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Retrieves the serial number information.
	 * @return String containing the serial number.
	 */
	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	/**
	 * Sets the revocation reason information.
	 * @param reason Revocation status.
	 */
	public void setReason(RevocationReason reason) {
		this.reason = reason;
	}
	
	/**
	 * Sets the revocation date time.
	 * @param date Date/time information.
	 */
	public void setDate(Calendar date) {
		this.revocationDate = date;
	}
	
	/**
	 * Retrieves the revocation reason.
	 * @return Revocation reason object.
	 */
	public RevocationReason getReason() {
		return this.reason;
	}
	
	/**
	 * Sets the revocation date time information.
	 * @return Date/time information.
	 */
	public Calendar getDate() {
		return this.revocationDate;
	}
}
