package course.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import course.model.service.CourseService;
import course.model.vo.Book;
import course.model.vo.Semester;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String semester = request.getParameter("semester");
		
		ArrayList<Book> bookList = new CourseService().bookList(semester);
		
		JSONObject sendJson = new JSONObject();
	    JSONArray jsonArr = new JSONArray();
	      
	      for(Book b : bookList) {
	    	  JSONObject userJson = new JSONObject();
	    	  
	    	  userJson.put("book", URLEncoder.encode(b.getBookName(), "utf-8"));
	    	  
	    	  jsonArr.add(userJson);
	      }
	      
	      sendJson.put("list",jsonArr);
	      
	      response.setContentType("application/json; charset=utf-8");
	      PrintWriter out = response.getWriter(); 
	      out.println(sendJson.toJSONString());
	      out.flush();
	      out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
