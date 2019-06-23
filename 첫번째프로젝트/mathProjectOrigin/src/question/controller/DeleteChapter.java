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
 * Servlet implementation class DeleteChapter
 */
@WebServlet("/dChapter")
public class DeleteChapter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteChapter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getSession().getServletContext().getRealPath("/workbook/");
		String semester = request.getParameter("semester");
		String book = request.getParameter("book");
		String chapter = request.getParameter("chapter");
		String path = root + semester + "/" + book + "/" + chapter;
		
		String message = "";
		int result = 0;
		File delfile = new File(path);
		if(delfile.exists()) {
			delfile.delete();
			if(delfile.exists() == false) {
				result = new QuestionService().deleteChapter(semester, book, chapter);
			}else {
				message = "챕터 삭제 실패! (문제 이미지 파일 부터 삭제해주세요)";
			}
		}else {
			message = "챕터 삭제 실패!";
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
