package question.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;

/**
 * Servlet implementation class SuperUpload
 */
@WebServlet("/sUpload")
public class SuperUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getSession().getServletContext().getRealPath("/workbook");
		
		 // Create a file object
        File file = new File(root);
 
        
        QuestionService qService = new QuestionService();
        
        // 1. check if the file exists or not
        boolean isExists = file.exists();
         
        if(!isExists) {
            System.out.println("There is nothing.");
        }
         
        // 2. check if the object is directory or not.
        if(file.isDirectory()) {
            File[] fileList = file.listFiles();
            for(File tFile : fileList) {
                String newSemester = tFile.getName();
                qService.makeSemester(newSemester);
                File file2 = new File(root + "/" + newSemester);
                if(file2.isDirectory()) {
                	File[] fileList2 = file2.listFiles();
                	for(File tFile2 : fileList2) {
                		 String newBook = tFile2.getName();
                         qService.makeBook(newSemester, newBook);
                         File file3 = new File(root + "/" + newSemester + "/" + newBook);
                         if(file3.isDirectory()) {
                        	 File[] fileList3 = file3.listFiles();
                        	 for(File tFile3 : fileList3) {
                        		 String newChapter = tFile3.getName();
                                 qService.makeChapter(newSemester, newBook, newChapter);
                                 File file4 = new File(root + "/" + newSemester + "/" + newBook + "/" + newChapter);
                                 File[] fileList4 = file4.listFiles();
                                 
                                	 for(File tFile4 : fileList4) {
                                		 String imgName = tFile4.getName();
                                         qService.uploadImg(newSemester, newBook, newChapter, imgName);                                  
                                	 }
                                 
                        	 }
                         }
                	}
                }
            }          
        }
        System.out.println("대용량 업로드 성공..");

        response.sendRedirect("/math/views/question/adminQuestionPrint.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
