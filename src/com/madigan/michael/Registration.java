package com.madigan.michael;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.madigan.michael.HashWithSalt;
import java.sql.*;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
			
		try
		{
			String encryptedPasswd = HashWithSalt.getHash(password);
			
			CreateUserDBRequest createUser = new CreateUserDBRequest(userName, encryptedPasswd, firstName, lastName, email);
		         
			createUser.ProcessDB();
			
			UserDBRequest userRequest = new UserDBRequest(userName);
			
			userRequest.ProcessDB();
			
			UserResultSet userRecord = userRequest.getUserRecord();
			
			if (userRecord.isValid())
			{
				Integer standardRoleID = new Integer(getServletContext().getInitParameter("STANDARD_ROLE_ID"));
				AddRoles addRolesRequest = new AddRoles(userRecord.userID, standardRoleID.intValue());
				addRolesRequest.ProcessDB();
			}
			
		     System.out.println("Success\n");
		     response.getWriter().println("Password was " + password + " and is now " + encryptedPasswd + " length=" + encryptedPasswd.length());
			  
		}catch(Exception e){
			 e.printStackTrace();
	       // Oops
			 response.getWriter().println(e.getMessage () + "\n");
			 return;
		}
		
	}

}
