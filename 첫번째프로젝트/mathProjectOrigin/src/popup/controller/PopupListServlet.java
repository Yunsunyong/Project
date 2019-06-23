package popup.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popup.model.service.PopupService;
import popup.model.vo.Popup;

/**
 * Servlet implementation class PopupListServlet
 */
@WebServlet("/plist")
public class PopupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 5;
		
		PopupService pservice = new PopupService();
		String searchTitle = "";
		if(request.getParameter("title") != null) {
			searchTitle = request.getParameter("title");
		}
		int AllSearchListCount = pservice.AllSearchListCount(searchTitle);
		
		int maxPage = (int)((double)AllSearchListCount / limit + 0.9);
		int startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		ArrayList<Popup> plist = pservice.searchAllList(searchTitle, startPage, limit);
		
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(plist.size() >= 0) {
			view = request.getRequestDispatcher("views/popup/popupListView.jsp");
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);;
			request.setAttribute("limit", limit);
			request.setAttribute("searchTitle", searchTitle);
			request.setAttribute("plist", plist);
			request.setAttribute("AllSearchListCount", AllSearchListCount);
			view.forward(request, response);
		}else{
			view = request.getRequestDispatcher("views/popup/popupError.jsp");
			request.setAttribute("message", "조회 불가");
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
