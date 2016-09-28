package uk.ac.mmu.watchai.Get;

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
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 */

/**
 * A servlet to search for a particular course in the database
 *
 */

@WebServlet("/GetThing")
@SuppressWarnings("serial")
public class GetThing extends HttpServlet {
	
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	System.out.println("Getting thing... ");
	
	String searchThing = request.getParameter("thing2");
	
	try{
		
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
	
	Thing things = DAO.INSTANCE.getThing(searchThing);
	
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


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
	}

}