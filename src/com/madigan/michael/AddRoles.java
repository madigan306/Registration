package com.madigan.michael;

import java.sql.SQLException;

public class AddRoles extends MySQLDBRequest {

	int m_userID;
	int m_roleID;
	
	public AddRoles(int userID, int roleID) {
		super();
		m_userID = userID;
		m_roleID = roleID;
	}

	@Override
	public void ProcessDB() throws SQLException {

		ProcessUpdate();
		CleanUp();
	}

	@Override
	public String getStatement() {
		// TODO Auto-generated method stub
		return "INSERT INTO USER_ROLES (USER_ID, ROLE_ID) " +
        "VALUES(" + m_userID + ", " + m_roleID + ")";
	}

}
