package com.bw.Add;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bw.DAO;
import com.google.gson.Gson;

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

    /*
     * Methods for xml, json and text with their
     * corresponding JSP pages which format results. 
     */

    	String zone = request.getParameter("zone");
    	String user = request.getParameter("user4");
    	
    	
    	DAO.INSTANCE.addZone(zone, user);
    	
    	String d = zone + " " + user;
    	
    	request.setAttribute("d", d);
    	
    	Gson gson = new Gson();
    	String json = gson.toJson(d);
    	request.setAttribute("json", json);
    	
    	String text = d.toString();
    	request.setAttribute("text", text);
    	
    	String format = request.getParameter("format");
    	String outputPage;
    	if ("xml".equals(format)) {
    	    response.setContentType("text/xml");
    	    outputPage = "/WEB-INF/results/insertXML.jsp";
    	  } else if ("json".equals(format)) {
    	  	
    	    response.setContentType("application/json");
    	    outputPage = "/WEB-INF/results/course-json.jsp";
    	  } else if ("text".equals(format)){
    	    response.setContentType("text/plain");
    	    outputPage = "/WEB-INF/results/insertText.jsp";
    	  } else {
    	  	response.setContentType("application/json");
    	        outputPage = "/WEB-INF/results/course-json.jsp";
    	  }
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
