package popup.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popup.model.service.PopupService;
import popup.model.vo.Popup;

/**
 * Servlet implementation class PopupUpdateViewServlet
 */
@WebServlet("/pupview")
public class PopupUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int popupNo = Integer.parseInt(request.getParameter("no"));
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		Popup popup = new PopupService().selectPDetail(popupNo);
		
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(popup != null) {
			view = request.getRequestDispatcher("views/popup/popupUpdateView.jsp");
			request.setAttribute("popup", popup);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/popup/popupError.jsp");
			request.setAttribute("messgae", popupNo + "번 수정페이지창으로 갈수 없습니다.");
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
