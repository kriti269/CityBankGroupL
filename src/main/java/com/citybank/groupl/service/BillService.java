package com.citybank.groupl.service;

import com.citybank.groupl.bean.Bill;

public interface BillService {
	public Bill addBill(Bill bill, double amount);
	public int payBill(int billId, int transactionId);
}
