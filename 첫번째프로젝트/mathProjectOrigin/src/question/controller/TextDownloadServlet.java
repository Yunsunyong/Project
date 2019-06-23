package question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.TextCreate;

/**
 * Servlet implementation class TextDownloadServlet
 */
@WebServlet("/tdown")
public class TextDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String semester = request.getParameter("semester");
		String book = request.getParameter("book");
		String chapter = request.getParameter("chapter");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String str[] = name.split("/");
		ArrayList<String> arr= new ArrayList<>();
		for(int i = 0; i<str.length;i++) {
			arr.add(str[i]);
			arr.remove(book);
			arr.remove(chapter);
			arr.remove(semester);
			arr.remove("workbook");
			arr.remove("math");
			
		}
		ArrayList<String> arr2= new ArrayList<>();
		for(int i = 1; i<arr.size();i++) {
			String names = arr.get(i).substring(0, arr.get(i).length()-4);
			arr2.add(names);
		}
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "files/text";
		int result = new TextCreate().textCreate(savePath,semester,book,chapter,arr2,title);
		String returnValue=null;
		if(0<result) {
			returnValue="ok";
		}else if(result==0){
			returnValue="dup";
		}

		PrintWriter out = response.getWriter();
		out.append(returnValue);
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
