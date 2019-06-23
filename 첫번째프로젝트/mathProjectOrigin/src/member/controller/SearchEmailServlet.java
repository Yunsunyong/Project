package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SearchEmailServlet
 */
@WebServlet("/semail")
public class SearchEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String phone = request.getParameter("phone");
		System.out.println(phone);
		ArrayList<Member> email = new MemberService().searchEmail(phone);
		PrintWriter out =null;
		if(email.size()==0) {
			out = response.getWriter();
			String no = "no";
			out.write(no);
			out.flush();
			out.close();
		}else{
		JSONObject sendJson = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		for(Member m : email) {
			JSONObject job = new JSONObject();
			job.put("email", m.getUserId());
			jsonArr.add(job);			
		}
		
		sendJson.put("list", jsonArr);
		System.out.println("sendJson : " + sendJson.toJSONString());
		
		response.setContentType("application/json; charset=utf-8");
		out = response.getWriter();
		out.write(sendJson.toJSONString());
		out.flush();
		out.close();
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
