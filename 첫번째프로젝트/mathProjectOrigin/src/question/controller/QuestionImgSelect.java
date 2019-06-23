package question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import question.model.service.QuestionService;
import question.model.vo.Question;

/**
 * Servlet implementation class QuestionImgSelect
 */
@WebServlet("/question")
public class QuestionImgSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionImgSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String semester = URLDecoder.decode(request.getParameter("semester"),"utf-8");
		String book = URLDecoder.decode(request.getParameter("book"),"utf-8");
		String chapter = URLDecoder.decode(request.getParameter("chapter"),"utf-8");

		ArrayList<Question> list = new QuestionService().QuestionImgSelect(semester, book, chapter);
		

		JSONObject sendJson = new JSONObject();
		
		JSONArray jsonArr = new JSONArray();

		for (Question q : list) {

			JSONObject userJson = new JSONObject();

			userJson.put("question", URLEncoder.encode(q.getQuestion(), "UTF-8"));

			jsonArr.add(userJson);
		}

		sendJson.put("list", jsonArr);

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(sendJson.toJSONString());
		out.flush();
		out.close();
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
