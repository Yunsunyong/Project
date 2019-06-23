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
 * Servlet implementation class DeleteImg
 */
@WebServlet("/dImg")
public class DeleteImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String strArr[] = request.getParameterValues("img");
		
		String value[] = strArr[0].split("/");
		String semester = request.getParameter("semesterDel");
		String book = request.getParameter("bookDel");
		String chapter = request.getParameter("chapterDel");
		String root = request.getSession().getServletContext().getRealPath("/");
		
		
		int result = 0;
		int count = 0;
		File delfile = null;
		for(String i:strArr) {
			String delPath = root + i.substring(6, i.length());
			String imgName[] = i.split("/");
		
			delfile = new File(delPath);
			if(delfile.exists()) {
				delfile.delete();
				result = new QuestionService().deleteImg(semester, book, chapter, imgName[6]);
				count += result;
			}
		}
		
		if(count == strArr.length) {
			response.sendRedirect("/math/views/question/adminQuestionPrint.jsp");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/question/questionError.jsp");
			request.setAttribute("message", "삭제 실패!  (문제 이미지" + strArr.length + "개 중 " + count + "개 삭제 완료!)");
			view.forward(request, response);
		}
		
		/*String items = request.getParameter("items");
		String img[] = items.split(",");
		String root = request.getSession().getServletContext().getRealPath("/");
		String semester = request.getParameter("semester");
		String book = request.getParameter("book");
		String chapter = request.getParameter("chapter");
		
		int result = 0;
		int count = 0;
		File delfile = null;
		for(String i:img) {
			String delPath = root + i.substring(6, i.length());
			String imgName[] = i.split("/");
			
			delfile = new File(delPath);
			if(delfile.exists()) {
				delfile.delete();
				result = new QuestionService().deleteImg(semester, book, chapter, imgName[6]);
				count += result;
			}
		}
		
		if(count == img.length) {
			response.sendRedirect("/math/views/question/adminQuestionPrint.jsp");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/question/questionError.jsp");
			request.setAttribute("message", "삭제 실패!  (문제 이미지" + img.length + "개 중 " + count + "개 삭제 완료!)");
			view.forward(request, response);
		}
		*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
