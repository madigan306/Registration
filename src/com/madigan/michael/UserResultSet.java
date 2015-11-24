package com.madigan.michael;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSet {

	int userID;
	String userName;
	String email;
	String firstName;
	String lastName;
	String password;
	boolean isValid = false;
	
	public UserResultSet(ResultSet rs) throws SQLException {
		
		 if(rs.next())
	     {
			 isValid = true;
			 int index = 1;
			 userID = 	 rs.getInt(index++);
			 userName =  rs.getString(index++);
			 email = 	 rs.getString(index++);
			 firstName = rs.getString(index++);
			 lastName =  rs.getString(index++);
			 password =  rs.getString(index++);
	     }
	}
	
	public boolean isValid()
	{
		return isValid;
	}

}
