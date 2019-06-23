package course.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.model.service.CourseService;
import course.model.vo.Course;

/**
 * Servlet implementation class CourseWriteServlet
 */
@WebServlet("/cwrite")
public class CourseWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String sOption = request.getParameter("sOption");
		String bOption = request.getParameter("bOption");
		
		Course course = new Course();
		course.setCourseTitle(request.getParameter("cTitle"));
		course.setCourseContent(request.getParameter("content"));
		
		int result = new CourseService().courseWrite(course,sOption,bOption);
		if(result > 0) {
			response.sendRedirect("/math/clist");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/course/courseError.jsp");
			request.setAttribute("message", "글작성 실패");
			view.forward(request, response);
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
