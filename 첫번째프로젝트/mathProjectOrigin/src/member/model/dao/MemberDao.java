package member.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import member.model.vo.Member;
import member.model.vo.Semester;

public class MemberDao {

	public Member loginMember(Connection conn, String userId, String password) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query ="SELECT * FROM TB_USER WHERE USER_ID = ? AND USER_PWD = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
				member = new Member();
				
				member.setUserId(userId);
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setPhone(rset.getString("phone"));
				member.setRegistDate(rset.getDate("regist_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setMemberLevel(rset.getString("member_level"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public int selectCheckId(Connection conn, String userId) {
		int  result = 0;
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "SELECT COUNT(USER_ID) FROM TB_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public int insertMember(Connection conn, Member member) {
		int result=0;
		PreparedStatement pstmt =null;
		String query ="INSERT INTO TB_USER VALUES(?, ?, ?, ?,NOW(),NOW(),0,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getSalt());
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member selectMember(Connection conn, String userId) {
		Member member = new Member();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT * FROM TB_USER WHERE USER_ID = ?";
		try {
			pstmt  = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String userName = rset.getString(3);
				String phone = rset.getString(4);
				Date registDate = rset.getDate(5);
				Date lastModified = rset.getDate(6);
				String memberLevel = rset.getString(7);
				member = new Member(userId, userName, phone, registDate, lastModified, memberLevel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}
	public int memberUpdate(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="UPDATE TB_USER SET USER_PWD =?, PHONE =?,USER_NAME=?, LASTMODIFIED = NOW(), SALT=? WHERE USER_ID = ?";
		try {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getSalt());
			pstmt.setString(5, member.getUserId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int searchLv(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String qurey ="SELECT MEMBER_LEVEL FROM TB_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(qurey);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			 result = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public int checkId(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String qurey ="SELECT COUNT(1) FROM TB_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(qurey);
			pstmt.setString(1, userId);
			rset =pstmt.executeQuery();
			while(rset.next()) {
				 result = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> searchEmail(Connection conn, String phone) {
		ArrayList<Member> member = new ArrayList<Member>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT * FROM TB_USER WHERE PHONE = ?";
		try {
			pstmt  = conn.prepareStatement(query);
			pstmt.setString(1, phone);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String userId = rset.getString(1);
				String userName = rset.getString(2);
				String phone2 = rset.getString(3);
				Date registDate = rset.getDate(4);
				Date lastModified = rset.getDate(5);
				String memberLevel = rset.getString(6);
				member.add(new Member(userId, userName, phone2, registDate, lastModified, memberLevel));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public ArrayList<Semester> selectMyPermission(Connection conn, String userId) {
		ArrayList<Semester> mylist = new ArrayList<Semester>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT SEMESTER_NAME, USER_ID " + 
				"FROM TB_PERMISSION P " + 
				"JOIN TB_SEMESTER S ON P.SEMESTER_NO = S.SEMESTER_NO " + 
				"WHERE USER_ID = ?";
		
		try {
			pstmt  = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String semesterName = rset.getString(1);
				mylist.add(new Semester(userId, semesterName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return mylist;
	}

	public ArrayList<Semester> selectPermission(Connection conn) {
		ArrayList<Semester> slist = new ArrayList<Semester>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT * FROM TB_SEMESTER";
		try {
			pstmt  = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int semesterNo = rset.getInt(1);
				String semesterName = rset.getString(2);
				slist.add(new Semester(semesterNo, semesterName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return slist;
	}
	public String getSaltById(Connection conn, String userId) {
		String result = null;
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "SELECT SALT FROM TB_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
}
}

