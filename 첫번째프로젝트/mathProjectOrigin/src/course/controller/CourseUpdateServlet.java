package course.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.model.service.CourseService;
import course.model.vo.Course;

/**
 * Servlet implementation class CourseUpdateServlet
 */
@WebServlet("/cupdate")
public class CourseUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String sOption = "";
		if(request.getParameter("sOption") != null) {
			sOption = request.getParameter("sOption");
			
		}
		String bOption = "";
		if(request.getParameter("sOption") != null) {
			bOption = request.getParameter("bOption");
		}
		String cTitle = request.getParameter("cTitle");
		String[] title = null;
		if(cTitle.contains("]")) {
			title = cTitle.split("]");
		}
		
		Course course = new Course();
		course.setCourseNo(Integer.parseInt(request.getParameter("no")));
		if(cTitle.contains("]")) {
		course.setCourseTitle(title[1]);
		}else {
			course.setCourseTitle(cTitle);
		}
		course.setCourseContent(request.getParameter("content"));
		
		int result = new CourseService().courseUpdate(course, sOption, bOption);
		if(result > 0) {
			response.sendRedirect("/math/cdetail?no=" + course.getCourseNo());
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/course/courseError.jsp");
			request.setAttribute("message", course.getCourseNo() + "번 글 수정이 안됬습니다.");
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
