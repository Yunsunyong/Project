package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import admin.model.vo.Admin;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/alogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");
		request.setCharacterEncoding("utf-8");
		HttpSession session =request.getSession();
		String admin= request.getParameter("userid");
		String userPwd = request.getParameter("password");
		RequestDispatcher view = null;
		Admin loginAdmin = new AdminService().loginAdmin(admin, userPwd);
		System.out.println("2");
		if(loginAdmin !=null) {
			session.setAttribute("admin",admin);
			response.sendRedirect("/math/nslist");
		}else {
			view = request.getRequestDispatcher("/views/member/adminError.jsp");
			request.setAttribute("message", "로그인실패!");
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
