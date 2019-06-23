package question.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import question.model.service.QuestionService;

/**
 * Servlet implementation class UploadImg
 */
@WebServlet("/upImg")
public class UploadImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/question/questionError.jsp");
			request.setAttribute("message", "form의 enctype 속성 사용 안 되었음.");
			view.forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 100;
		
		String root = request.getSession().getServletContext().getRealPath("/workbook/");
		
		String temporaryPath = request.getSession().getServletContext().getRealPath("/resources/임시저장소");
		
		//request 를 MultipartRequest 객체로 변환함
		MultipartRequest mrequest = new MultipartRequest(request, temporaryPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String semester = mrequest.getParameter("semester");
		String book = mrequest.getParameter("book");
		String chapter = mrequest.getParameter("chapter");
		String imgName = mrequest.getFilesystemName("upImg");
		String savePath = root + semester + "/" + book + "/" + chapter + "/" + imgName;
	
		
		File orgFile = new File(temporaryPath + "/" + imgName);
	    File newFile = new File(savePath);
	            
	    byte[] buf = new byte[1024];
	    FileInputStream fin = null;
	    FileOutputStream fout = null;
	    
	    int result = 0;
		
	    if(orgFile.exists()) {
	        if(orgFile.renameTo(newFile)) {
	        	result = new QuestionService().uploadImg(semester, book, chapter, imgName);
	        }else {
	        	buf = new byte[1024];
		        fin = new FileInputStream(orgFile);
		        fout = new FileOutputStream(newFile);
		     
		        int read = 0;
		        while((read=fin.read(buf,0,buf.length))!=-1){
		            fout.write(buf, 0, read);
		        }
		        fin.close();
		        fout.close();
		        orgFile.delete();
		        
		        result = new QuestionService().uploadImg(semester, book, chapter, imgName);
	        }
	    }
	
	    if(result > 0) {
	    	response.sendRedirect("/math/views/question/adminQuestionPrint.jsp");
	    }else {
	    	view = request.getRequestDispatcher("views/question/questionError.jsp");
			request.setAttribute("message", "문제 이미지 업로드 실패!");
			view.forward(request, response);
	    }
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
