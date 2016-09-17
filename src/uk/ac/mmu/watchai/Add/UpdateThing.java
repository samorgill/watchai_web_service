package uk.ac.mmu.watchai.Add;

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

import uk.ac.mmu.watchai.DAO.DAO;
import uk.ac.mmu.watchai.Model.Thing;

import java.util.List;;

/**
 * 
 * @author Samuel Orgill 15118305
 * @version 4
 * 15/9/2016
 * Manchester Metropolitan University
 * NW.5 Smartwatch Control of Environment
 * Supervisor: Nick Whittaker
 * 
 */


 /**
 * A servlet to update the state of a thing, ie on/off, in the database
 *
 */

@WebServlet("/UpdateThing")
@SuppressWarnings("serial")
public class UpdateThing extends HttpServlet {

/**
 * Do get method
 */
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	System.out.println("Updating thing... ");
	
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
	
	System.out.println(things);
	request.setAttribute("thingList", things);
	
	Gson gson = new Gson();
	String json = gson.toJson(things);
	request.setAttribute("json", json);
	
	String outputPage;
	  response.setContentType("application/json");
	  outputPage = "/WEB-INF/results/course-json.jsp";
	
	RequestDispatcher dispatcher =
	request.getRequestDispatcher(outputPage);
	
		dispatcher.include(request, response);
	} catch (ServletException | EntityNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

/**
 * Do post method
 */

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
	}

}