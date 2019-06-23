package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaSContentAllListServlet
 */
@WebServlet("/qslist")
public class QnaSearchAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaSearchAllListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		String qOption = "";
		if(request.getParameter("qOption") != null) {
			qOption = request.getParameter("qOption");
		}
		
		String searchTitle = "";
		if(request.getParameter("title") != null) {
			searchTitle = request.getParameter("title");
		}
		
		int limit = 10;
		
		QnaService qservice = new QnaService();
		int allSearchListCount = qservice.allSearchListCount(searchTitle, qOption);
		int maxPage = (int)((double)allSearchListCount / limit + 0.9);
		int startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		ArrayList<Qna> qList = qservice.searchAllList(searchTitle, qOption, currentPage, limit);
		
		RequestDispatcher view = null;
		if(qList.size() > 0 || qList.size() == 0) {
			view = request.getRequestDispatcher("views/qna/qnaListView.jsp");
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);;
			request.setAttribute("limit", limit);
			request.setAttribute("allSearchListCount", allSearchListCount);
			request.setAttribute("qList", qList);
			request.setAttribute("qOption", qOption);
			request.setAttribute("searchTitle", searchTitle);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/qna/qnaError.jsp");
			request.setAttribute("message", "QnA 목록이 없습니다.");
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
