package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDao;
import admin.model.vo.Admin;
import admin.model.vo.Semester;
import member.model.vo.Member;

public class AdminService {
	private AdminDao adao = new AdminDao();
	public Admin loginAdmin(String userId, String password) {
		Connection conn = getConnection();
		Admin admin = adao.loginAdmin(conn,userId,password);
		close(conn);
		return admin;
	}
	public Member selectMember(String userId) {
		Connection conn = getConnection();
		Member member = adao.selectMember(conn,userId);
		close(conn);
		return member;
	}
	public int memberUpdate(Member member) {
		Connection conn = getConnection();
		int result = adao.memberUpdate(conn, member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public ArrayList<Member> selectMemberAll() {
		Connection conn = getConnection();
		ArrayList<Member>  list = adao.selectMemberAll(conn);
		close(conn);
		return list;
	}
	public int levelChange(String userid, int level) {
		Connection conn = getConnection();
		int result = adao.levelChange(conn, userid,level);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int memberPasswordChange(String userId, String pass, String salt) {
		Connection conn = getConnection();
		int result = adao.memberPasswordChange(conn,userId, pass,salt);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int resetPassword(String userId, String hex, String salt) {
		Connection conn = getConnection();
		int result = adao.resetPassword(conn,userId, hex,salt);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int countMember() {
		Connection conn = getConnection();
		int result = adao.countMember(conn);
	
		close(conn);
		return result;
	}
	public ArrayList<Semester> selectPermission() {
		Connection conn = getConnection();
		ArrayList<Semester>  slist = adao.selectPermission(conn);
		close(conn);
		return slist;
	}
	public ArrayList<Semester> selectMyPermission(String userId) {
		Connection conn = getConnection();
		ArrayList<Semester>  mylist = adao.selectMyPermission(conn,userId);
		close(conn);
		return mylist;
	}
	public int addPermission(ArrayList<Semester> addper) {
		Connection conn = getConnection();
		int result = adao.addPermission(conn,addper);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public Semester selectSemesterNo(String semeName) {
		Connection conn = getConnection();
		Semester seme = adao.selectSemesterNo(conn,semeName);
		close(conn);
		return seme;
	}
	public int removePermission(ArrayList<Semester> addper) {
		Connection conn = getConnection();
		int result = adao.removePermission(conn,addper);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public void lastModifiedPermission(String userId) {
		Connection conn = getConnection();
		int result = adao.lastModifiedPermission(conn,userId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
	}
	public void overlapDelete(String semeName, String userId) {
		Connection conn = getConnection();
		int result = adao.overlapDelete(conn,semeName,userId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	public int allSearchListCount(String searchTitle, String fOption) {
		Connection conn = getConnection();
		int result = adao.allSearchListCount(searchTitle, fOption, conn);
		close(conn);
		return result;
	}
	public ArrayList<Member> selectMemberAll(String searchTitle, String fOption, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Member> list = adao.selectMemberAll(searchTitle, fOption, currentPage, limit, conn);
		close(conn);
		return list;	
	}
}
