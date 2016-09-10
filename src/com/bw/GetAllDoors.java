package com.bw;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 7
 * 
 * A servlet to get all courses in the database
 *
 */

@WebServlet("/GetAllDoors")
@SuppressWarnings("serial")
public class GetAllDoors extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	
	System.out.println("Getting all doors... ");
		
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");

	/*
	 * Methods for xml, json and text with their
	 * corresponding JSP pages which format results. 
	 */

	List<Entity> doors = DAO.INSTANCE.getThings();
	System.out.println(doors.toString());
	request.setAttribute("doorList", doors);

	Gson gson = new Gson();
	String json = gson.toJson(doors);
	request.setAttribute("json", json);

	for(int i = 0; i < doors.size(); i++){
	String text = doors.get(i).toString();
	request.setAttribute("text", text);}

	String format = request.getParameter("format");

	String outputPage;

	if ("xml".equals(format)) {
	  response.setContentType("text/xml");
	  outputPage = "/WEB-INF/results/course-xml.jsp";
	} else if ("json".equals(format)) {
	  response.setContentType("application/json");
	  outputPage = "/WEB-INF/results/course-json.jsp";
	} else if ("text".equals(format)){
	  response.setContentType("text/plain");
	  outputPage = "/WEB-INF/results/course-string.jsp";
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
	}
}
