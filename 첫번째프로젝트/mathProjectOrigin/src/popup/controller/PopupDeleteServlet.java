package popup.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import popup.model.service.PopupService;

/**
 * Servlet implementation class PopupDeleteServlet
 */
@WebServlet("/pdelete")
public class PopupDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int popupNo = Integer.parseInt(request.getParameter("no"));
		
		int result = new PopupService().deletePopup(popupNo);
		
		if(result > 0) {
			response.sendRedirect("/math/plist");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/popup/popupError.jsp");
			request.setAttribute("message", popupNo + "번 글 삭제 실패");
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
