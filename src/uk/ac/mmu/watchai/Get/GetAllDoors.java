package uk.ac.mmu.watchai.Get;

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
 * A servlet to get all doors in the database
 * Deprecated now with GetAllThings
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
	String outputPage;
	  response.setContentType("application/json");
	  outputPage = "/WEB-INF/results/course-json.jsp";

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
