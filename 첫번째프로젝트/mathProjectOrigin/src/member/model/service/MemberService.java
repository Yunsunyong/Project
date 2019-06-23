package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.model.vo.Semester;

public class MemberService {
	private MemberDao mdao = new MemberDao();
	public Member loginMember(String userId, String password) {
		Connection conn = getConnection();
		Member member = mdao.loginMember(conn,userId,password);
		close(conn);
		return member;
	}
	public int selectCheckId(String userId) {
		Connection conn = getConnection();
		int result = mdao.selectCheckId(conn, userId);
		close(conn);
		return result;
	}
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = mdao.insertMember(conn, member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Member selectMember(String userId) {
		Connection conn = getConnection();
		Member member = mdao.selectMember(conn,userId);
		close(conn);
		return member;
	}

	public int memberUpdate(Member member) {
		Connection conn = getConnection();
		int result = mdao.memberUpdate(conn, member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int searchLv(String userId) {
		Connection conn = getConnection();
		int result = mdao.searchLv(conn, userId);
		close(conn);
		return result;
	}
	public int checkId(String userId) {
		Connection conn = getConnection();
		int result = mdao.checkId(conn, userId);
		close(conn);
		return result;
	}
	public ArrayList<Member> searchEmail(String phone) {
		Connection conn = getConnection();
		ArrayList<Member> member = mdao.searchEmail(conn,phone);
		close(conn);
		return member;
	}
	public ArrayList<Semester> selectMyPermission(String userId) {
		Connection conn = getConnection();
		ArrayList<Semester>  mylist = mdao.selectMyPermission(conn,userId);
		 	close(conn);
		return mylist;
	}
	public ArrayList<Semester> selectPermission(String userId) {
		Connection conn = getConnection();
		ArrayList<Semester>  slist = mdao.selectPermission(conn);
		close(conn);
		return slist;
	}
	public String getSaltById(String userId) {
		Connection conn = getConnection();
		String salt = mdao.getSaltById(conn,userId);
		close(conn);
		return salt;
	}
}
