package uk.ac.mmu.watchai.Add;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import uk.ac.mmu.watchai.DAO.DAO;


/**
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 */

/**
 * Servlet implementation class Zones
 */
@WebServlet("/Zones")
public class Zones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   // @WebServlet("/addZone")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

    	System.out.println("Updating door state... ");
    	
    	response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

    	String zone = request.getParameter("zone");
    	String user = request.getParameter("user4");
    	
    	DAO.INSTANCE.addZone(zone, user);
    	
    	String d = zone + " " + user;
    	
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
