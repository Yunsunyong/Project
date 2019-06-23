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
 * Servlet implementation class CourseSearchAllListServlet
 */
@WebServlet("/clist")
public class CourseAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseAllListServlet() {
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
		if(request.getParameter("bOption") != null) {
			bOption = request.getParameter("bOption");
		}

		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		CourseService cservice = new CourseService();
		
		int limit = 10;
		
		int allListCount = cservice.allListCount(sOption, bOption);
		int maxPage = (int)((double)allListCount / limit + 0.9);
		int startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		ArrayList<Course> cList = cservice.courseAllList(currentPage, limit, sOption, bOption);
		
		RequestDispatcher view = null;
		if(cList.size() > 0 || cList.size() == 0) {
			view = request.getRequestDispatcher("views/course/courseAdminListView.jsp");
			request.setAttribute("cList", cList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);;
			request.setAttribute("limit", limit);
			request.setAttribute("allListCount", allListCount);
			request.setAttribute("sOption", sOption);
			request.setAttribute("bOption", bOption);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/course/courseError.jsp");
			request.setAttribute("message", "강의목록이 없습니다.");
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
