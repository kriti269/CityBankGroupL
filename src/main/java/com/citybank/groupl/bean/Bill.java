package com.citybank.groupl.bean;

public class Bill {
	private int billId;
	private Account userAccount;
	private String merchantName;
	private String merchantAccount;
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public Account getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantAccount() {
		return merchantAccount;
	}
	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}
	

}
