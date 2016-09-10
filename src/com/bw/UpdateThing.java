package com.bw;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

import java.util.List;
import com.bw.DAO;;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 7
 * 
 * A servlet to search for a particular course in the database
 *
 */

@WebServlet("/UpdateThing")
@SuppressWarnings("serial")
public class UpdateThing extends HttpServlet {
	
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	System.out.println("Getting door... ");
	
	String thing = request.getParameter("thing3");
	String state = request.getParameter("state");
	String user = request.getParameter("user4");
	String serial = request.getParameter("serial2");
	String type = request.getParameter("type2");
	String zone = request.getParameter("zone2");
	String room = request.getParameter("room2");
	
	try{
		
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
	
	Thing things = DAO.INSTANCE.updateThing(thing, state, user, serial, type, zone, room);
	
	/*
	 * Methods for xml, json and text with their
	 * corresponding JSP pages which format results. 
	 */
	
	System.out.println(things);
	request.setAttribute("thingList", things);
	
	
	
	Gson gson = new Gson();
	String json = gson.toJson(things);
	request.setAttribute("json", json);
	
	/*for(int i = 0; i < doors.size(); i++){
	String text = doors.get(i).toString();
	request.setAttribute("text", text);}*/
	
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
	
		dispatcher.include(request, response);
	} catch (ServletException | EntityNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
	}

}