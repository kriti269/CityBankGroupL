package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.bean.BillPayment;

public interface BillService {
	public Bill addBill(Bill bill, double amount);
	public int payBill(int billId, int transactionId);
	public List<BillPayment> getBillPayments(int user_id);
}
