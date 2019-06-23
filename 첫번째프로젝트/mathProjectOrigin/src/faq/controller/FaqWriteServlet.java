package faq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.Faq;


/**
 * Servlet implementation class FaqWriteServlet
 */
@WebServlet("/fwrite")
public class FaqWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Faq faq = new Faq();
		faq.setQuestionContent(request.getParameter("title"));
		faq.setAnswerContent(request.getParameter("content"));
		faq.setAdminId(request.getParameter("writer"));

		int result = new FaqService().insertFaqWrite(faq);
		if(result > 0) {
			response.sendRedirect("/math/fslist");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/faq/faqError.jsp");
			request.setAttribute("message", "FAQ 글쓰기 실패");
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
