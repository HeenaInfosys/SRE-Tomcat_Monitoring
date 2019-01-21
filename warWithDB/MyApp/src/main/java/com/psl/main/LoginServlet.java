package com.psl.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.psl.main.LoginBean;
import com.psl.main.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String l_manufacturer 	= 	null ,
			   l_carName		= 	null ,
			   l_color			=	null ,
		       l_price			=	null ;
		boolean result			=   false;
		try
		{
			
			if("Get Inventory".equals(request.getParameter("commit"))){
				
				
				InventoryDAO.InvLogic();
				response.sendRedirect("InventoryDetails.jsp");
			}else if("Add".equals(request.getParameter("add"))){
				
				l_manufacturer = request.getParameter("manufacturer");
				l_carName = request.getParameter("carname");
				l_color = request.getParameter("color");
				l_price = request.getParameter("price");
				System.out.println(l_manufacturer);
				 if(InventoryDAO.validateFields(l_manufacturer)
						 && InventoryDAO.validateFields(l_carName)
						 && InventoryDAO.validateFields(l_color)
						 && InventoryDAO.validatePrice(l_price)){
					 InventoryDAO.showModifiedDtls(l_manufacturer,l_carName,l_color,l_price);
				 }
				response.sendRedirect("InventoryDetails.jsp");
			}
			else{
				LoginBean user = new LoginBean();
				user.setUserName(request.getParameter("uname"));
				user.setPassword(request.getParameter("password"));
				result = LoginDAO.validateFields(request.getParameter("uname"), request.getParameter("password"));
				if(result){
					user = LoginDAO.login(user);
					if(user.isValid())
					{
						HttpSession session = request.getSession(true);
						session.setAttribute("currentSessionUser",user);
						response.sendRedirect("LoginSuccess.jsp");
					}else
						response.sendRedirect("LoginFailed.jsp");
				}else{
					response.sendRedirect("LoginFailed.jsp");
				}
			}
		} catch (Throwable exc)
		{
			exc.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
