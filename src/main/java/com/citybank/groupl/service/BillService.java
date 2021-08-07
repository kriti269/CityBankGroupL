package com.citybank.groupl.service;

import com.citybank.groupl.bean.Bill;

public interface BillService {
	public String[] addBill(Bill bill, String amount, int userId);
	public int payBill(int billId, int transactionId);
}
