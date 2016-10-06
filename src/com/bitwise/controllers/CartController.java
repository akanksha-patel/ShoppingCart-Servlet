package com.bitwise.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwise.models.Cart;
import com.bitwise.models.Product;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submit = request.getParameter("submit");
		String prod = request.getParameter("prod");
		String item = request.getParameter("item");
		String [] tokens = prod.split(",");
		Product product = new Product (tokens[0], Double.parseDouble(tokens[1]));
		Double price = product.getProductPrice();
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		
		
		if (submit == null) {
			return;
		}
		
		if (submit.equals("ADD TO CART")) {
			if (isCartNull(session)) {
				System.out.println("new cart");
				initializeCart(prod, price, session);
				response.sendRedirect("ViewCartController");
				return;
			} else {
				System.out.println("old cart");
				addItemToCart(prod,  price, session);
				response.sendRedirect("ViewCartController");
				return;
			}
		}
		
		if (submit.equals("REMOVE FROM CART")) {
			if (isCartNotEmpty(session)) {
				System.out.println("removing");
				removeItemFromCart(prod,  price,session);
				response.sendRedirect("ViewCartController");
				return;
			}else{
				pw.print("<font size='3' color='red'>Cart is Empty !!! </font>");
				
			}
		}
		
		if (submit.equals("DISPLAY CART")) {
			if (isCartNotEmpty(session)) {
				System.out.println("displaying");
				response.sendRedirect("ViewCartController");
				return;
			}else{
				pw.print("<font size='3' color='red'>Cart is Empty !!! </font>");
				
			}
		}
		
		 if(submit.equals("LOGOUT")){
				response.sendRedirect("ShowLogoutServlet");
			}
		
	}
	
	private boolean isCartNotEmpty(HttpSession session) {
		return session.getAttribute("cart") != null;
	}

	private boolean isCartNull(HttpSession session) {
		return session.getAttribute("cart") == null;
	}

	private void addItemToCart(String productName,Double productPrice,HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		cart.add(new Product(productName,productPrice));
		session.setAttribute("cart", cart);
	}
	
	private void removeItemFromCart(String productName,Double productPrice,HttpSession session){
		Product product = new Product(productName,productPrice);
		Cart cart = (Cart) session.getAttribute("cart");
		cart.remove(product);
	}

	private void initializeCart(String productName,Double productPrice,HttpSession session){
		Cart cart = new Cart ();
		session.setAttribute("cart", cart);
		cart.add(new Product(productName,productPrice));
	
	}


	
}
