package com.madigan.michael;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetUserForRole
 */
public class GetUserForRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserForRole() {
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
		
		try
		{		
			UserDBRequest dbRequest = new UserDBRequest(userName);
		
			dbRequest.ProcessDB();
			
		    UserResultSet user = dbRequest.getUserRecord();
		     
	    	 if (user.isValid())
	         {
	    		 response.getWriter().println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Welcome</title></head><body>");
		    	 	
	    		 // Get Roles
	    		 UserRolesDBRequest rolesDBRequest = new UserRolesDBRequest(user.userID);
	    		 rolesDBRequest.ProcessDB();
	    		 
	    		 RolesResultSet roles = rolesDBRequest.getRoles();
	    		 
	    		 if (roles.isValid()) 
	    		 {
	    			 response.getWriter().println("Roles for " + user.userName);
	    		 }
	    		 else
	    		 {
	    			 response.getWriter().println("No roles were found for " + user.userName);		
	    		 }
	    		 
	    		 //Give some admin love.
				 response.getWriter().println("<form name=addRoleForm action=\"http://localhost:8080/Registration/AddRole\" method=\"POST\" >");
				 response.getWriter().println("<input type=\"hidden\" name=\"userID\" value=\"" + user.userID + "\">");
 				
				 response.getWriter().println("Select Role: <select name=role><option name=STANDARD_ROLE_ID value=STANDARD_ROLE_ID> STANDARD_ROLE_ID </option>" +
						 				"<option name=SUPER_USER_ROLE_ID value=SUPER_USER_ROLE_ID> SUPER_USER_ROLE_ID </option>" +
						 				"<option name=ADMIN_ROLE_ID value=ADMIN_ROLE_ID> ADMIN_ROLE_ID </option></select>");
				 
				 response.getWriter().println("<br><input type=\"submit\" value=\"Submit\" /></form>");
				 
	    		 response.getWriter().println("</body></html>");
	 		    
	         }
	    	 else
	    	 {
	    		 response.getWriter().println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Welcome</title></head><body>");
	    		 response.getWriter().println("Fix your inputs fucktard</body></html>");
			    
	    		 System.out.println("Could not find " + userName);	
	    	 }
	    	 
		    
		}catch(Exception e){
			e.printStackTrace();
	       // Oops
			 response.getWriter().println(e.getMessage () + "\n");
		}
	}

}
