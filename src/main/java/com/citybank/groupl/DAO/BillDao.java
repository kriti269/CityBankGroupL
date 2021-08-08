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

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Database operations related to bill table are performed in Bill dao.
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - Database
 * operations related to bill table are performed in Bill dao.
 */

public class BillDao {

	public JdbcTemplate jdbcTemplate;

	// getter for jdbc template
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// setter for jdbc tempalte
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Inserts new bill into the bill table, sets the generated
	// billId in the object and returns the object
	public Bill addBill(Bill bill) {
		String sql = "insert into bill(account_id, merchant_name, merchant_account)" + " values(?,?,?)";

		try {
			List<SqlParameter> listParams = Arrays.asList(new SqlParameter(Types.INTEGER, "account_id"),
					new SqlParameter(Types.VARCHAR, "merchant_name"),
					new SqlParameter(Types.VARCHAR, "merchant_account"));

			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, listParams) {
				{
					setReturnGeneratedKeys(true);
					setGeneratedKeysColumnNames("bill_id");
				}
			};

			@SuppressWarnings("unchecked")
			List<? extends Object> billParams = Arrays.asList(bill.getAccount().getAccountId(), bill.getMerchantName(),
					bill.getMerchantAccount());
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(billParams);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(psc, keyHolder);
			// setting bill id
			bill.setBillId(Integer.parseInt(keyHolder.getKey().toString()));
			return bill;
		} catch (Exception ex) {
			System.out.println("Error occurred while adding bill " + ex.getMessage());
		}
		return bill;

	}

}
