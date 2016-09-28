package uk.ac.mmu.watchai.Add;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gson.Gson;

import uk.ac.mmu.watchai.DAO.DAO;
import uk.ac.mmu.watchai.Model.Thing;


/**
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 */

/**
 * Servlet implementation class Monitor
 */
@WebServlet("/Monitor")
public class Monitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monitor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Updating thing... ");
		
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String user = request.getParameter("user");
		try{
			
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		//Add to the database
		DAO.INSTANCE.addMonitor(type, state, user);
		
		System.out.println(type);
		request.setAttribute("thingList", type);
		
		
		//Set as JSON
		Gson gson = new Gson();
		String json = gson.toJson(type);
		request.setAttribute("json", json);
		
		String outputPage;
		  response.setContentType("application/json");
		  outputPage = "/WEB-INF/results/course-json.jsp";
		
		RequestDispatcher dispatcher =
		request.getRequestDispatcher(outputPage);
		
			dispatcher.include(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
