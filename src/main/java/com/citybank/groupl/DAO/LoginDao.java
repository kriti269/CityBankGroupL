package com.citybank.groupl.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.citybank.groupl.bean.Login;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Database operations related to login table are performed in Login
 *          Dao.
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - Database
 * operations related to login table are performed in Login Dao.
 */

@Component
public class LoginDao {

	JdbcTemplate jdbcTemplate;

	// getter for jdbc template
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// setter for jdbc template
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// inserts new loginId and password into the login
	// table and returns the login object
	public Login saveLogin(final Login login) {
		String sql = "insert into login values(?,?)";
		try {
			// insert the row by using PreparedStatementSetter object to set model
			// attributes inside SQL query
			jdbcTemplate.update(sql, new PreparedStatementSetter() {

				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, login.getLoginId());
					ps.setString(2, login.getPassword());
				}
			});
			return login;
		} catch (Exception e) {
			// prints if any exception occurs
			System.out.println("Error occurred while saving login. " + e.getMessage());
		}
		// returns number of rows affected as either 0 or 1
		return null;
	}

	// gets a row from the database with provided loginId and password.
	// Returns a boolean based on row found
	public boolean isValidLogin(Login login) {
		String sql = "select * from login where login_id=? and password=?";
		Login userLogin = null;
		try {
			// executes the query and maps the row to User model.
			userLogin = jdbcTemplate.queryForObject(sql, new Object[] { login.getLoginId(), login.getPassword() },
					new int[] { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR },
					new BeanPropertyRowMapper<Login>(Login.class));
			if (userLogin != null)
				return true;
		} catch (Exception e) {
			// prints any exception occurred
			System.out.println("Error occurred while logging in. " + e.getMessage());
		}
		// returns the validated User
		return false;
	}

}
