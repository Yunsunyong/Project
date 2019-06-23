package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailListServlet
 */
@WebServlet("/ndetail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("no"));
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		String nOption = "";
		if(request.getParameter("noption") != null) {
			nOption = request.getParameter("noption");
		}
		String searchTitle = "";
		if(request.getParameter("title") != null) {
			searchTitle = request.getParameter("title");
		}
		NoticeService nservice = new NoticeService();
		//조회수 1증가 처리
		nservice.addReadCount(noticeNo);
		
		Notice notice = nservice.selectNDetail(noticeNo);
		
		int noticeBack = nservice.noticeBack(noticeNo);
		int noticeNext = nservice.noticeNext(noticeNo);
		int noticeMin = nservice.noticeMin();
		
		RequestDispatcher view = null;
		response.setContentType("text/html; charset=utf-8");
		if(notice != null) {
			view = request.getRequestDispatcher("views/notice/noticeDetailView.jsp");
			request.setAttribute("notice", notice);
			request.setAttribute("noticeBack", noticeBack);
			request.setAttribute("noticeNext", noticeNext);
			request.setAttribute("noticeMin", noticeMin);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/notice/noticeError.jsp");
			request.setAttribute("message", noticeNo + "번의 상세보기 실패하셨습니다.");
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
