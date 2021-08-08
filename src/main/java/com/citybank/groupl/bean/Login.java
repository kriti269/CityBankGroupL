package com.citybank.groupl.bean;

import java.io.Serializable;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary It stores the login details of users and maps to the login table in
 *          the database.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - It
 * stores the login details of users and maps to the login table in the
 * database.
 */

public class Login implements Serializable {

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
