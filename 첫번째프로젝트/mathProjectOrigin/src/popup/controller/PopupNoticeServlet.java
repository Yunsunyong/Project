package popup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import popup.model.service.PopupService;
import popup.model.vo.Popup;

/**
 * Servlet implementation class PopupNoticeServlet
 */
@WebServlet("/popup.do")
public class PopupNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Popup> nplist = new PopupService().popupMain();
		/*for (Question q : list) {
			System.out.println(q.getSemester());
		}*/
		// 전송할 json 객체 준비
		JSONObject sendJson = new JSONObject();
		// 리스트 객체들을 저장할 json 배열 객체 준비
		JSONArray jsonArr = new JSONArray();
		
		for (Popup p : nplist) {
			
			JSONObject userJson = new JSONObject();
			userJson.put("popupNo", p.getPopupNo());
			userJson.put("popupName", URLEncoder.encode(p.getPopupName(), "UTF-8"));
			userJson.put("popupLink", p.getPopupLink());
			userJson.put("popupX", p.getPopupX());
			userJson.put("popupY", p.getPopupY());
			userJson.put("popupWidth", p.getPopupWidth());
			userJson.put("popupHeight", p.getPopupHeight());
			userJson.put("startDate", p.getPopupDate().toString());
			userJson.put("endDate", p.getPopupEndDate().toString());
			userJson.put("popupImagePath", p.getPopupImagePath());
			userJson.put("popupImgLink", URLEncoder.encode(p.getPopupImgLink(), "UTF-8"));
			userJson.put("exPlan", URLEncoder.encode(p.getPopupExplan(), "UTF-8"));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
