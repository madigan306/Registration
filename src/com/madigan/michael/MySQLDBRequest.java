package com.madigan.michael;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class MySQLDBRequest implements DBRequest {
	
	private DataSource m_dataSource;
	protected ResultSet m_resultSet;
	protected Connection m_connection;
	
	@Override
	public abstract void ProcessDB() throws SQLException;
	
	protected void ProcessQuery() throws SQLException {
		
		m_connection = getConnection(); 
		System.out.println("Connection: " + m_connection);

		//Get a Statement object
		Statement stmt = m_connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 					 ResultSet.CONCUR_READ_ONLY);

		m_resultSet = stmt.executeQuery(getStatement());
	}
	
	protected void ProcessUpdate() throws SQLException {
		
		m_connection = getConnection(); 
		System.out.println("Connection: " + m_connection);

		//Get a Statement object
		Statement stmt = m_connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 					 ResultSet.CONCUR_READ_ONLY);
		stmt.executeUpdate(getStatement());
	}
	@Override
	public abstract String getStatement();

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			m_dataSource = (DataSource)envContext.lookup("jdbc/mydb");
		} catch( NamingException ne ) {
			throw new RuntimeException( "Unable to aquire data source", ne );
		}
		
		return m_dataSource.getConnection(); 
	}
	
	public void CleanUp() throws SQLException {
		m_connection.close();
	}

}
