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
	
<<<<<<< HEAD
=======
	//Add thing to the database
>>>>>>> 9766e2de672a1e74775e8ada6ff18893f969c952
	DAO.INSTANCE.add(thing, state, user, serial, type, zone, room);
	String d = thing + " " + state + " " + user + " " + serial + " " + type + " " + zone + " " + room;
	request.setAttribute("d", d);
	
	Gson gson = new Gson();
	String json = gson.toJson(d);
	request.setAttribute("json", json);
	
	//Format JSON
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
