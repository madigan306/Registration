package com.madigan.michael;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBRequest {
	
	public void ProcessDB() throws SQLException;
	public String getStatement();
	public Connection getConnection() throws SQLException;
}
