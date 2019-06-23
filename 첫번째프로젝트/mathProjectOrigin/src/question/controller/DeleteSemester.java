package question.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;

/**
 * Servlet implementation class DeleteSemester
 */
@WebServlet("/dSemester")
public class DeleteSemester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSemester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getSession().getServletContext().getRealPath("/workbook/");
		String semester = request.getParameter("semester");
		String path = root + semester;
	
		String message = "";
		int result = 0;
		File delfile = new File(path);
		if(delfile.exists()) {
			delfile.delete();
			if(delfile.exists() == false) {
				result = new QuestionService().deleteSemester(semester);
			}else {
				message = "학기 삭제 실패! (하위 디렉토리 부터 삭제해주세요)";
			}
		}else {
			message = "학기 삭제 실패!";
		}
		
		if(result > 0) {
			response.sendRedirect("/math/views/question/adminQuestionPrint.jsp");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/question/questionError.jsp");
			request.setAttribute("message", message);
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
