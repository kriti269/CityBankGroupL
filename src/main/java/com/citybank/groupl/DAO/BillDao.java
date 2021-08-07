package com.citybank.groupl.DAO;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.citybank.groupl.bean.Bill;

public class BillDao {
	
	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Bill addBill(Bill bill) {
		String sql = "insert into bill(account_id, merchant_name, merchant_account)"
				+ " values(?,?,?)";
		
		try {
			List listParams = Arrays.asList(new SqlParameter(Types.INTEGER, "account_id"),
					new SqlParameter(Types.VARCHAR, "merchant_name"), 
					new SqlParameter(Types.VARCHAR, "merchant_account"));

			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, listParams) {
				{
					setReturnGeneratedKeys(true);
					setGeneratedKeysColumnNames("bill_id");
				}
			};

			List billParams = Arrays.asList(bill.getAccount().getAccountId(), bill.getMerchantName(), bill.getMerchantAccount());
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(billParams);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(psc, keyHolder);
			bill.setBillId(Integer.parseInt(keyHolder.getKey().toString()));
			return bill;
		}
		catch(Exception ex) {
			System.out.println("Error occurred while adding bill " + ex.getMessage());
		}
		return bill;
		
	}

}
