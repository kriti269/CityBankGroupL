package com.citybank.groupl.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

public class BillPaymentDao {
	
	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int payBill(int billId, int transactionId) {
		String sql = "insert into bill_payment(bill_id, transaction_id) values(?,?)";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {billId, transactionId},
					new int[] {java.sql.Types.INTEGER, java.sql.Types.INTEGER});
		}
		catch(Exception ex) {
			System.out.println("Failed to pay bill "+ ex.getMessage());
		}
		return result;
	}

}
