package com.madigan.michael;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	int m_role;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
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
		
		try
		{		
			String encryptedPasswd = HashWithSalt.getHash(password);
		
			UserDBRequest dbRequest = new UserDBRequest(userName);
		
			dbRequest.ProcessDB();
			
		    UserResultSet user = dbRequest.getUserRecord();
		     
	    	 if (user.isValid() && user.password.equalsIgnoreCase(encryptedPasswd))
	         {
	    		 response.getWriter().println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Welcome</title></head><body>");
		    	 
	    		 response.getWriter().println("<h3>Welcome " + user.userName + "</h3>");
	    		
	    		 // Get Roles
	    		 UserRolesDBRequest rolesDBRequest = new UserRolesDBRequest(user.userID);
	    		 rolesDBRequest.ProcessDB();
	    		 
	    		 RolesResultSet roles = rolesDBRequest.getRoles();
	    		 
	    		 if (roles.isValid() && !roles.m_roles.isEmpty())
	    		 {
	    			 Integer standardRoleID = new Integer(getServletContext().getInitParameter("STANDARD_ROLE_ID"));
	    			 if (roles.m_roles.get(0) < standardRoleID.intValue())
	    			 {
	    				 //Give some admin love.
	    				 response.getWriter().println("<b>Add a Role</b>" +
	    						 					"<form name=getUserForRoleForm action=\"http://localhost:8080/Registration/GetUserForRole\" method=\"POST\" >" +
	    						 					"<table><tr><td>UserName to Add Minister:</td><td><input type=\"text\" name=\"userName\" value=\"\" maxlength=\"44\" />" +
	    						 					"</td></tr></table>");	
	    				 response.getWriter().println("<br><input type=\"submit\" value=\"Submit\" /></form>");
	    					
	    			 }
	    		 }
	    		 else
	    		 {
	    			 response.getWriter().println("No roles were found");		
	    		 }
	    		 
	    		 response.getWriter().println("</body></html>");
	 		    
	         }
	    	 else
	    	 {
	    		 response.getWriter().println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Welcome</title></head><body>");
	    		 response.getWriter().println("Fix your inputs fucktard</body></html>");
			    	 
	    		 if (!user.isValid())
		         {
	    			 System.out.println("Could not find " + userName);
		         }
	    		 else if (!user.password.equalsIgnoreCase(encryptedPasswd)){
	    			 System.out.println("Bad Password");
	    		 }
	    		 else {
		    		 outputFailure(response.getWriter());	
	    		 }	    		
	    	 }
	    	 
		    
		}catch(Exception e){
			e.printStackTrace();
	       // Oops
			 response.getWriter().println(e.getMessage () + "\n");
		}
		
	}
	
	protected void outputFailure(PrintWriter writer)
	{
		// throw up an error
		writer.println("Failure");	
	}

}
