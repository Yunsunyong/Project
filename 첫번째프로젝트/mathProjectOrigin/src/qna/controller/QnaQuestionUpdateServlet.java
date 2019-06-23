package qna.controller;

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

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaQuestionUpdateServlet
 */
@WebServlet("/qqupdate")
public class QnaQuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaQuestionUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/qna/qnaError.jsp");
			request.setAttribute("message", "업로드 실패");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 100;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "files/qna/";
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		Qna qna = new Qna();
		qna.setQnaNo(Integer.parseInt(mrequest.getParameter("qno")));
		qna.setQnaTitle(mrequest.getParameter("qATitle"));
		qna.setQnaContent(mrequest.getParameter("content"));
		qna.setQnaWriter(mrequest.getParameter("userId"));
		
		String oFileName = mrequest.getParameter("ofile");
		String rFileName = mrequest.getParameter("rfile");
		
		String originalFileName = mrequest.getFilesystemName("upfile");
		
		if(originalFileName != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date currentTime = new Date(System.currentTimeMillis());
			String renameFileName = sdf.format(currentTime) + originalFileName.substring(originalFileName.indexOf("."));
			
			File originFile = new File(savePath + originalFileName);
			File renameFile = new File(savePath + renameFileName);
			
			if(!originFile.renameTo(renameFile)) {
				int read = -1;
				byte[] buf = new byte[1024];
				
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				
				while((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
				fin.close();
				fout.close();
				originFile.delete();
			}
			if(rFileName != null) {
				(new File(savePath + "/" +rFileName)).delete();
			}
			
			qna.setOriginalQname(originalFileName);
			qna.setRenameQname(renameFileName);
		}else {
			qna.setOriginalQname(oFileName);
			qna.setRenameQname(rFileName);
		}
		int result = new QnaService().answerUpdate(qna);
		if(result > 0) {
			response.sendRedirect("/math/qudetail?no="+qna.getQnaNo());
		}else {
			view = request.getRequestDispatcher("views/qna/qnaError.jsp");
			request.setAttribute("message", qna.getQnaNo() + "번 수정 실패!" );
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
