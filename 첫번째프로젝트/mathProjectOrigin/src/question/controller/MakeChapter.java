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
 * Servlet implementation class MakeChapter
 */
@WebServlet("/mChapter")
public class MakeChapter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeChapter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String newChapter = request.getParameter("newChapter");
		String semester = request.getParameter("semester");
		String book = request.getParameter("book");
		
		System.out.println("s : " + semester);
		System.out.println("b : " + book);
		System.out.println("c : " + newChapter);
		int result = 0;
		
		String root = request.getSession().getServletContext().getRealPath("/workbook/");
		String path = root + semester + "/" + book + "/" + newChapter;
	
		File Folder = new File(path);

		String message = "챕터 생성 실패!";
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
				result = new QuestionService().makeChapter(semester, book, newChapter);
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			message = "이미 같은 이름의 챕터가 존재 합니다.";
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
