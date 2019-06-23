package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Semester;
import member.model.service.MemberService;
import member.model.service.SHA256Util;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberMyinfoServlet
 */
@WebServlet("/myinfo")
public class MemberMyinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMyinfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String userId = (String)session.getAttribute("userId");
		Member member = new MemberService().selectMember(userId);
		member.setUserPwd("");
		ArrayList<Semester> allList = new MemberService().selectPermission(userId);
		ArrayList<Semester> slist = new MemberService().selectMyPermission(userId);
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(member != null) {
			view = request.getRequestDispatcher("views/member/memberDetailView.jsp"); 
			request.setAttribute("member", member);
			request.setAttribute("semester", allList);
			request.setAttribute("permission", slist);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/member/memberMyinfoError.jsp");
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
