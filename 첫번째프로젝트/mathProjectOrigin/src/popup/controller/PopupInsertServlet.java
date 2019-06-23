package popup.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import popup.model.service.PopupService;
import popup.model.vo.Popup;

/**
 * Servlet implementation class PopupInsertServlet
 */
@WebServlet("/pinsert")
public class PopupInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/popup/popupError.jsp");
			request.setAttribute("message", "업로드불가능");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 100;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "files/popup";
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		Popup popup = new Popup();
		
		popup.setPopupName(mrequest.getParameter("ptitle"));
		popup.setPopupLink(mrequest.getParameter("plink"));
		popup.setPopupX(Integer.parseInt(mrequest.getParameter("pX")));
		popup.setPopupY(Integer.parseInt(mrequest.getParameter("pY")));
		popup.setPopupWidth(Integer.parseInt(mrequest.getParameter("pWidth")));
		popup.setPopupHeight(Integer.parseInt(mrequest.getParameter("pHeight")));
		popup.setPopupDate(Date.valueOf(mrequest.getParameter("startDate")));
		popup.setPopupEndDate(Date.valueOf(mrequest.getParameter("endDate")));
		String originalFileName = mrequest.getFilesystemName("imagelink");
		popup.setPopupImagePath(originalFileName);
		popup.setPopupImgLink(mrequest.getParameter("imgl"));
		popup.setPopupExplan(mrequest.getParameter("discrip"));
		popup.setAdminId(mrequest.getParameter("adminId"));
		
		
		int result = new PopupService().insertPopup(popup);
		
		if(result > 0) {
			response.sendRedirect("/math/plist");
		}else {
			view = request.getRequestDispatcher("views/popup/popupError.jsp");
			request.setAttribute("message", "글쓰기 실패하였습니다");
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
