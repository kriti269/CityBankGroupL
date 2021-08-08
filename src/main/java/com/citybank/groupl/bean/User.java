package com.citybank.groupl.bean;

import java.io.Serializable;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary It stores the user details and maps to the user table in the
 *          database.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - It
 * stores the user details and maps to the user table in the database.
 */

public class User implements Serializable {
	private static final long serialVersionUID = -4278991705035701579L;
	private int userId;
	private Login login;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNumber;
	private String gender;
	private Address address;
	private boolean isAdmin;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
