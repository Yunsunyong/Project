package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.LoginManager;

/**
 * Servlet implementation class MemberAutoLoginServlet
 */
@WebServlet("/mautologin")
public class MemberAutoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberAutoLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String path = "index.jsp";
		LoginManager lm = new LoginManager();
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		String userId = (String) session.getAttribute("userId");
		if (lm.isUsing(userId)) {
			lm.removeSession(userId);
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String userid = cookie.getName();
					if (userid.equals("userId")) {
						if (lm.isUsing(cookie.getValue())) {
							lm.setSession(session, cookie.getValue());
							path = "index.jsp";
							break;
						}
					} else {
						path = "index.jsp";
					}
				}
			} else {
				path = "index.jsp";
			}
			
		} else {
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String userid = cookie.getName();
					if (userid.equals("userId")) {
						if (lm.isUsing(cookie.getValue())) {
							lm.setSession(session, cookie.getValue());
							path = "index.jsp";
							break;
						}
					} else {
						path = "index.jsp";
					}
				}
			} else {
				path = "index.jsp";
			}
		}
		view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
