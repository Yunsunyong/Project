package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberManagerServlet
 */
@WebServlet("/mmanager")
public class MemberManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberManagerServlet() {
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
			System.out.println(fOption);
		}
		String searchTitle = "";
		if(request.getParameter("title") != null) {
			searchTitle = request.getParameter("title");
		}
		AdminService aservice = new AdminService();		
		int allSearchListCount = aservice.allSearchListCount(searchTitle, fOption);
		int maxPage = (int)((double)allSearchListCount / limit + 0.9);
		int startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		ArrayList<Member> list = new AdminService().selectMemberAll(searchTitle, fOption,currentPage, limit);
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(list.size() >= 0) {
			view = request.getRequestDispatcher("views/member/memberManager.jsp");
			request.setAttribute("list", list);
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
			view = request.getRequestDispatcher("views/member/adminMemberManagerError.jsp");
			request.setAttribute("message","모든 회원 정보 보기 실패");
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
