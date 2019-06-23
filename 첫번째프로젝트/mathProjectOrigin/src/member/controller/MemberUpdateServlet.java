package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.service.SHA256Util;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mupdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member member = new Member();
		member.setUserId(request.getParameter("userid"));
		String salt = SHA256Util.generateSalt();
        String newPassword = SHA256Util.getEncrypt(request.getParameter("password"), salt);
        member.setUserPwd(newPassword);
        member.setSalt(salt);
		member.setPhone(request.getParameter("phone"));
		member.setUserName(request.getParameter("username"));
		System.out.println(member);
		int result = new MemberService().memberUpdate(member);
		if(result > 0) {
			response.sendRedirect("/math/myinfo");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/member/memberMyinfoError.jsp");
			request.setAttribute("message", member.getUserName()+" 회원님의 정보 수정 실패");
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
