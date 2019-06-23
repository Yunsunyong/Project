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
import admin.model.vo.Semester;
import member.model.vo.Member;

/**
 * Servlet implementation class memberDetailServlet
 */
@WebServlet("/mdetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userid");
		int page = Integer.parseInt(request.getParameter("page"));
		Member member = new AdminService().selectMember(userId);
		member.setUserPwd("");
		ArrayList<Semester> slist = new AdminService().selectPermission();
		ArrayList<Semester> mylsit = new AdminService().selectMyPermission(userId);
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(member != null) {
			view = request.getRequestDispatcher("views/member/memberManagerDetail.jsp"); 
			request.setAttribute("member", member);
			request.setAttribute("semester", slist);
			request.setAttribute("permission", mylsit);
			request.setAttribute("page",page);
			
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/member/adminMemberManagerError.jsp");
			request.setAttribute("message", userId+" 회원님의 정보 보기 실패");
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
