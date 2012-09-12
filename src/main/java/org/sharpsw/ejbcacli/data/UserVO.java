package org.sharpsw.ejbcacli.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class holds information for a user registered in the EJBCA server.
 * @author andersonkmi
 *
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = -8313630009712648597L;
	private String userName;
	private String password;
	private String certificateAuthority;
	private String endEntityProfile;
	private String certificateProfile;
	private String email;
	private String subjectAlternativeName;
	private String subjectDN;
	private UserStatus status;
	private TokenType tokenType;
	private boolean clearPassword;
	private Calendar startTime;
	
	public UserVO() {
		this.userName = "";
		this.password = "";
		this.certificateAuthority = "";
		this.endEntityProfile = "";
		this.certificateProfile = "";
		this.email = "";
		this.subjectAlternativeName = "";
		this.status = UserStatus.NEW;
		this.tokenType = TokenType.PEM;
		this.clearPassword = true;
		this.startTime = Calendar.getInstance();
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setCertificateAuthority(String authority) {
		this.certificateAuthority = authority;
	}
	
	public String getCertificateAuthority() {
		return this.certificateAuthority;
	}
	
	public void setEndEntityProfile(String profile) {
		this.endEntityProfile = profile;
	}
	
	public String getEndEntityProfile() {
		return this.endEntityProfile;
	}
	
	public void setCertificateProfile(String profile) {
		this.certificateProfile = profile;
	}
	
	public String getCertificateProfile() {
		return this.certificateProfile;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setSubjectAlternativeName(String name) {
		this.subjectAlternativeName = name;
	}
	
	public String getSubjectAlternativeName() {
		return this.subjectAlternativeName;
	}
	
	public void setSubjectDN(String name) {
		this.subjectDN = name;
	}
	
	public String getSubjectDN() {
		return this.subjectDN;
	}
	
	public void setUserStatus(UserStatus status) {
		this.status = status;
	}
	
	public UserStatus getUserStatus() {
		return this.status;
	}
	
	public void setTokenType(TokenType type) {
		this.tokenType = type;
	}
	
	public TokenType getTokenType() {
		return this.tokenType;
	}
	
	public void setClearPassword(boolean flag) {
		this.clearPassword = flag;
	}
	
	public boolean isClearPassword() {
		return this.clearPassword;
	}
	
	public void setStartTime(Calendar start) {
		this.startTime = start;
	}
	
	public Calendar getStartTime() {
		return this.startTime;
	}
}
