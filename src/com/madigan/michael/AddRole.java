package com.madigan.michael;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddRole
 */
public class AddRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRole() {
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
		Integer userID = new Integer(request.getParameter("userID"));
		Integer roleID = new Integer(getServletContext().getInitParameter( request.getParameter("role")));
		
		try
		{
			AddRoles addRolesDBRequest = new AddRoles(userID.intValue(), roleID.intValue());
			addRolesDBRequest.ProcessDB();
			response.getWriter().println("Success\n");
		}catch(Exception e){
			e.printStackTrace();
		       // Oops
			response.getWriter().println(e.getMessage () + "\n");
		}
		
	}

}
