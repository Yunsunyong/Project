package notice.controller;

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

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/nupdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/notice/noticeError.jsp");
			request.setAttribute("message", "수정하기 실패");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 100;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "files/notice/";
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		Notice notice = new Notice();
		notice.setNoticeNo(Integer.parseInt(mrequest.getParameter("no")));
		notice.setNoticeTitle(mrequest.getParameter("title"));
		notice.setWriterName(mrequest.getParameter("writer"));
		notice.setNoticeContent(mrequest.getParameter("content"));
		
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
			
			notice.setOriginalFileName(originalFileName);
			notice.setRenameFileName(renameFileName);
		}else {
			notice.setOriginalFileName(oFileName);
			notice.setRenameFileName(rFileName);
		}
		int result = new NoticeService().updateNotice(notice);
		if(result > 0) {
			response.sendRedirect("/math/ndetail?no="+notice.getNoticeNo());
		}else {
			view = request.getRequestDispatcher("views/notice/noticeError.jsp");
			request.setAttribute("message", notice.getNoticeNo() + "번 수정 실패!" );
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
