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
 * Servlet implementation class GetMonitors
 */
@WebServlet("/GetMonitors")
public class GetMonitors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMonitors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Getting all monitors... ");
		
		String user = request.getParameter("user");
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		List<Entity> monitors = DAO.INSTANCE.getAllMonitors(user);
		System.out.println(monitors.toString());
		request.setAttribute("zoneList", monitors);

		Gson gson = new Gson();
		String json = gson.toJson(monitors);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
