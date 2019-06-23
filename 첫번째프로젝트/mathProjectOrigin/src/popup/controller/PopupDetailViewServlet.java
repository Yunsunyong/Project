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
 * Servlet implementation class PopupDetailViewServlet
 */
@WebServlet("/pdetail")
public class PopupDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		int popupNo = Integer.parseInt(request.getParameter("no"));
	
		String pOption = "";
		if(request.getParameter("poption") != null) {
			pOption = request.getParameter("poption");
		}
		String psearchTitle = "";
		if(request.getParameter("title") != null) {
			psearchTitle = request.getParameter("title");
		}
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		PopupService pservice = new PopupService();
		
		Popup popup = pservice.selectPDetail(popupNo);
		
		int popupBack = pservice.popupBack(popupNo);
		int popupNext = pservice.popupNext(popupNo);
		int popupMin = pservice.popupMin();
		
		RequestDispatcher view = null;
		response.setContentType("text/html; charset=utf-8");
		if(popup != null) {
			view = request.getRequestDispatcher("views/popup/popupDetailView.jsp");
			request.setAttribute("popup", popup);
			request.setAttribute("popupBack", popupBack);
			request.setAttribute("popupNext", popupNext);
			request.setAttribute("popupMin", popupMin);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/popup/popupError.jsp");
			request.setAttribute("message", popupNo + "번의 상세보기 실패하셨습니다.");
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
