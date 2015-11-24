package com.madigan.michael;

import java.sql.SQLException;

public class CreateUserDBRequest extends MySQLDBRequest {

	private String m_userName = null;
	private String m_password = null;
	private String m_firstName = null;
	private String m_lastName = null;
	private String m_email = null;
		
	public CreateUserDBRequest(String userName, String password, String firstName, String lastName, String email) {
		super();
		m_userName = userName;
		m_password = password;
		m_firstName = firstName;
		m_lastName = lastName;
		m_email = email;
	}

	@Override
	public void ProcessDB() throws SQLException {
		
		ProcessUpdate();
		CleanUp();
	}

	@Override
	public String getStatement() {
		
		return "INSERT INTO USER (USER_NAME, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD) " +
        "VALUES('" + m_userName + "', '" + m_email + "', '" + m_firstName + "', '" 
        + m_lastName + "', '" + m_password + "')";
	}

}
