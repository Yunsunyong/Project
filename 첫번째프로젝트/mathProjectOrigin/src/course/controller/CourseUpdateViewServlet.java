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
import course.model.vo.Book;
import course.model.vo.Course;
import course.model.vo.Semester;

/**
 * Servlet implementation class CourseUpdateViewServlet
 */
@WebServlet("/cupview")
public class CourseUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseNo = Integer.parseInt(request.getParameter("no"));

		CourseService cservice = new CourseService();
		/*ArrayList<Semester> semList = cservice.semList();
		ArrayList<Book> bList = cservice.bookList();*/
		String sName = cservice.selectSName(courseNo);
		String bName = cservice.selectBName(courseNo);
		Course course = cservice.courseDetail(courseNo);
		
		RequestDispatcher view = null;
		if(course != null) {
			view = request.getRequestDispatcher("views/course/courseUpdateView.jsp");
			request.setAttribute("course", course);
			/*request.setAttribute("semList", semList);
			request.setAttribute("bList", bList);*/
			request.setAttribute("sName", sName);
			request.setAttribute("bName", bName);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/course/courseError.jsp");
			request.setAttribute("message", courseNo + "번 수정페이지로 가지 못합니다.");
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
