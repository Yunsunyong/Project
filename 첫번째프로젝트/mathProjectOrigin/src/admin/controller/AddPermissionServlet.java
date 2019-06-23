package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.Semester;

/**
 * Servlet implementation class AddPermissionServlet
 */
@WebServlet("/addper")
public class AddPermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPermissionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userid");
		String per = request.getParameter("per");
		System.out.println(userId+"님의 추가할 권한은 ");
		System.out.println(per);
		String[] str = per.split(" ");
		ArrayList<Semester> addper = new ArrayList<>();
		ArrayList<Semester> addper2 = new ArrayList<>();
		int result =0;
		if(str[0].equals("모든권한")) {
			addper2 = new AdminService().selectMyPermission(userId);
			int result2 = new AdminService().removePermission(addper2);
			System.out.println(result2);
			addper = new AdminService().selectPermission();
			for(int i = 0 ; i<addper.size();i++) {
				addper.get(i).setUserId(userId);
			}
			result = new AdminService().addPermission(addper);
			new AdminService().lastModifiedPermission(userId);
		}else {
		addper = new AdminService().selectMyPermission(userId);
			for(int i = 0 ;i<addper.size();i++) {
				for(int x=0 ; x<str.length;x++) {
					System.out.print(addper.get(i).getSemesterName()+"=");
					System.out.println(str[x]);
					if(addper.get(i).getSemesterName().equals(str[x])) {
						new AdminService().overlapDelete(str[x],userId);
						
					}
				}
			}
			
		for(int i=0 ; i<str.length;i++) {
			Semester seme = new AdminService().selectSemesterNo(str[i]);
			addper2.add(new Semester(seme.getSemesterNo(),userId , seme.getSemesterName()));
		}
		result = new AdminService().addPermission(addper2);
		new AdminService().lastModifiedPermission(userId);
		}
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
