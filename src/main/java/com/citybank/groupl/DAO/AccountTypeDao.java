package com.citybank.groupl.DAO;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.citybank.groupl.bean.AccountType;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary Database operations related to account_type table are performed in
 *          account dao.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - Database
 * operations related to account_type table are performed in account dao.
 */

public class AccountTypeDao {
	public JdbcTemplate jdbcTemplate;

	// getter for jdbc template
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// setter for jdbc template
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// gets list of all account types available in account_type table
	public List<AccountType> getAllAccountTypes() {
		String sql = "select * from account_type";
		List<AccountType> accountTypes = null;
		try {
			accountTypes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AccountType>(AccountType.class));
		} catch (Exception ex) {
			// prints any exception occurred
			System.out.println("Unable to get all account types: " + ex.getMessage());
		}
		return accountTypes;
	}
}
