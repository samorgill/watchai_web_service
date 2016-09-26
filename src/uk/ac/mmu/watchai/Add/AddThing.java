package uk.ac.mmu.watchai.Add;

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

import uk.ac.mmu.watchai.DAO.DAO;

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
 * 
 * A servlet to insert a add a thing to the datastore
 *
 */

@WebServlet("/AddThing")
@SuppressWarnings("serial")
public class AddThing extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");

	String thing = request.getParameter("thing");
	String state = request.getParameter("state");
	String user = request.getParameter("user");
	String serial = request.getParameter("serial");
	String type = request.getParameter("type");
	String zone = request.getParameter("zone");
	String room = request.getParameter("room");
	
	DAO.INSTANCE.add(thing, state, user, serial, type, zone, room);
	String d = thing + " " + state + " " + user + " " + serial + " " + type + " " + zone + " " + room;
	request.setAttribute("d", d);
	
	Gson gson = new Gson();
	String json = gson.toJson(d);
	request.setAttribute("json", json);
	
	String outputPage;
	    response.setContentType("application/json");
	    outputPage = "/WEB-INF/results/course-json.jsp";
	
	RequestDispatcher dispatcher =
		      request.getRequestDispatcher(outputPage);
		    try {
				dispatcher.include(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
	response.sendRedirect("localhost:8888/#tab3");
	}

/**
 * Do get method
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(request, response);
}

}
