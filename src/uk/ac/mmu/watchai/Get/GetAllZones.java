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
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 */

 /**
 * A servlet to get all the zones registered to a user
 *
 */

@WebServlet("/GetAllZones")
@SuppressWarnings("serial")
public class GetAllZones extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	
	System.out.println("Getting all zones... ");
	
	String user = request.getParameter("user5");
	
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");

	List<Entity> zones = DAO.INSTANCE.getAllZones(user);
	System.out.println(zones.toString());
	request.setAttribute("zoneList", zones);

	Gson gson = new Gson();
	String json = gson.toJson(zones);
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
