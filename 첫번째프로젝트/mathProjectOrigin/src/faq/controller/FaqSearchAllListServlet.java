package faq.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.Faq;

/**
 * Servlet implementation class FaqSearchAllListServlet
 */
@WebServlet("/fslist")
public class FaqSearchAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSearchAllListServlet() {
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

		int limit = 5;
	
		String fOption = "";
		if(request.getParameter("fOption") != null) {
			fOption = request.getParameter("fOption");
		}
		String searchTitle = "";
		if(request.getParameter("title") != null) {
			searchTitle = request.getParameter("title");
		}
		FaqService fservice = new FaqService();
		
		int allSearchListCount = fservice.allSearchListCount(searchTitle, fOption);
		int maxPage = (int)((double)allSearchListCount / limit + 0.9);
		int startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		ArrayList<Faq> fList = fservice.searchAllList(searchTitle, fOption,currentPage, limit);
		
		RequestDispatcher view = null;
		if(fList.size() > 0 || fList.size() == 0) {
			view = request.getRequestDispatcher("views/faq/faqListView.jsp");
			request.setAttribute("fList", fList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);;
			request.setAttribute("limit", limit);
			request.setAttribute("allSearchListCount", allSearchListCount);
			request.setAttribute("fOption", fOption);
			request.setAttribute("searchTitle", searchTitle);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/faq/faqError.jsp");
			request.setAttribute("message", "FAQ글이 없습니다");
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
