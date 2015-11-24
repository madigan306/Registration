package com.madigan.michael;

import java.sql.SQLException;

public class UserRolesDBRequest extends MySQLDBRequest {

	private int m_userID;
	private RolesResultSet m_roles;
	
	public UserRolesDBRequest(int userID) {
		super();
		m_userID = userID;
	}

	public void ProcessDB() throws SQLException {
		
		ProcessQuery();
		
		m_roles = new RolesResultSet(m_resultSet);
		
		CleanUp();
	}

	@Override
	public String getStatement() {
		// TODO Auto-generated method stub
		return "SELECT * FROM user_roles WHERE user_id = " + m_userID;
	}
	
	public RolesResultSet getRoles() {
		return m_roles;
	}

}
