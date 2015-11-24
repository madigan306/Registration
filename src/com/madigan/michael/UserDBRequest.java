package com.madigan.michael;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDBRequest extends MySQLDBRequest {

	private String m_userName = null;
	private UserResultSet m_userRecord;
	
	public UserDBRequest(String userName)
	{
		super();
		m_userName = userName;
	}
	
	public void ProcessDB() throws SQLException {
		
		if (m_userName == null)
		{
			System.out.println("UserDBRequest username is not set!");
			return;
		}
		
		ProcessQuery();
		
		m_userRecord = new UserResultSet(m_resultSet);
		
		CleanUp();
	}

	
	@Override
	public String getStatement() {
		return "SELECT * FROM user WHERE LOWER(USER_NAME) = '" + m_userName + "'";
	}

	public UserResultSet getUserRecord() {
		return m_userRecord;
	}
	
	public void SetUserName(String userName)
	{
		m_userName = userName;
	}
	
}
