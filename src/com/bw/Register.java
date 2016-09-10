package com.bw;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.bw.DAO;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 7
 * 
 * A servlet to insert a course into the database
 *
 */

@WebServlet("/Register")
@SuppressWarnings("serial")
public class Register extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	
	System.out.println("Registering... ");
	
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");

/*
 * Methods for xml, json and text with their
 * corresponding JSP pages which format results. 
 */

	String user = request.getParameter("user2");
	String pass = request.getParameter("pass2");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	String hub = request.getParameter("hub");
	
	DAO.INSTANCE.reg(user, pass,email, phone, hub);
	
	String d = user + " " + pass;
	
	request.setAttribute("d", d);
	
	Gson gson = new Gson();
	String json = gson.toJson(d);
	request.setAttribute("json", json);
	
	String text = d.toString();
	request.setAttribute("text", text);
	
	String format = request.getParameter("format");
	String outputPage;
	if ("xml".equals(format)) {
	    response.setContentType("text/xml");
	    outputPage = "/WEB-INF/results/insertXML.jsp";
	  } else if ("json".equals(format)) {
	  	
	    response.setContentType("application/json");
	    outputPage = "/WEB-INF/results/course-json.jsp";
	  } else if ("text".equals(format)){
	    response.setContentType("text/plain");
	    outputPage = "/WEB-INF/results/insertText.jsp";
	  } else {
	  	response.setContentType("application/json");
	        outputPage = "/WEB-INF/results/course-json.jsp";
	  }
	RequestDispatcher dispatcher =
		      request.getRequestDispatcher(outputPage);
		    try {
				dispatcher.include(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	response.sendRedirect("localhost:8888/#tab3");
	}


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(request, response);
}

}