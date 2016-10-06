package com.bitwise.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwise.models.Product;
import com.bitwise.models.Products;
import com.bitwise.models.User;

@WebServlet ("/ProductController")
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Products products = new Products();
		out.print("<font size='3' color='red'>");
		out.println("WELCOME "+user.getUserName().toUpperCase());
		out.print("<font>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>YOUR PROFILE</title>");
		out.println("<link rel='stylesheet' type='text/css' href='css/home.css' />");
		out.println("<link rel='stylesheet' type='text/css' href='css/mainmenu.css' />");
		out.println("</head>");
		out.println("<body bgcolor=\"pink\">");
		out.println("<div id='main'>");
		out.println("<h3>"+" WELCOME TO THE WORLD OF SHOPPING "+"</h3>");
		out.println("<form action='CartController'>");
		out.println("<select  name='prod'>");
		
		for (Product item: products.getList()) {
			out.println("<option>");
			out.println(item.getProductName()+","+item.getProductPrice());
			out.println("</option>");
		
		}
		
		out.println("</select>");
		out.println("<input type='submit' name='submit' value='ADD TO CART' />" );
		out.println("<input type='submit' name='submit' value='REMOVE FROM CART' />");
		out.println("<input	type='submit' name='submit' value='DISPLAY CART' />");
		out.println("<input type='submit' value='LOGOUT' name='submit' />");
		
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
