package uk.ac.mmu.watchai.Login;

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
 * A for logging into the webservice/apps
 *
 */

@WebServlet("/LogIn")
@SuppressWarnings("serial")
public class LogIn extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException {
	
	System.out.println("Logging in... ");
	
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");

    String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	
	DAO.INSTANCE.logIn(user, pass);
	
	String d = user + " " + pass;
	
	request.setAttribute("d", d);
	
	Gson gson = new Gson();
	String json = gson.toJson(d);
	request.setAttribute("json", json);
	
	String text = d.toString();
	request.setAttribute("text", text);
	
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
	
	response.sendRedirect("#tab2");
	}


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(request, response);
}

}
