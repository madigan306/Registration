package com.madigan.michael;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesResultSet {
	
	List<Integer> m_roles = null;
	private boolean m_isValid = false;
	
	public RolesResultSet(ResultSet rs) throws SQLException {
		
		m_roles = new ArrayList<Integer>();
		
		 while(rs.next())
	     {
			 m_isValid = true;
			 int index = 1;
			 int rowID  =  rs.getInt(index++);
			 int userID =  rs.getInt(index++);
			 int roleID =  rs.getInt(index++);
			 m_roles.add(roleID);
	     }
		 Collections.sort(m_roles);
	}
	
	public boolean isValid() {
		return m_isValid;
	}
}
