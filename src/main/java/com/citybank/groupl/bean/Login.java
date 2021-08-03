package com.citybank.groupl.bean;

import java.io.Serializable;

public class Login implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6489254073442081454L;
	private String loginId;
	private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
