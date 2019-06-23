package admin.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import admin.model.vo.Admin;
import admin.model.vo.Semester;
import member.model.vo.Member;

public class AdminDao {
	public Admin loginAdmin(Connection conn, String userId, String password) {
		Admin admin = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query ="SELECT * FROM TB_ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
				String adminId = rset.getString(1);
				String adminPwd = rset.getString(2);
				String adminName = rset.getString(3);
				admin = new Admin(adminId, adminPwd, adminName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return admin;
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
				String userPwd = rset.getString(2);
				String userName = rset.getString(3);
				String phone = rset.getString(4);
				Date registDate = rset.getDate(5);
				Date lastModified = rset.getDate(6);
				String memberLevel = rset.getString(7);
				String salt = rset.getString(8);
				member = new Member(userId, userPwd, userName, phone, registDate, lastModified, memberLevel,salt);
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
		String qurey ="UPDATE TB_USER SET USER_PWD =?, PHONE =?, LASTMODIFIED = NOW() WHERE USER_ID = ?";
		try {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getUserId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectMemberAll(Connection conn) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT * FROM TB_USER ORDER BY REGIST_DATE DESC";
		try {
			pstmt  = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String userId = rset.getString(1);
				String userPwd = rset.getString(2);
				String userName = rset.getString(3);
				String phone = rset.getString(4);
				Date registDate = rset.getDate(5);
				Date lastModified = rset.getDate(6);
				String memberLevel = rset.getString(7);
				String salt = rset.getString(8);
				list.add(new Member(userId, userPwd, userName, phone, registDate, lastModified, memberLevel,salt));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int levelChange(Connection conn, String userid, int level) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="UPDATE TB_USER SET MEMBER_LEVEL=? WHERE USER_ID = ?";
		try {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setInt(1, level);
			pstmt.setString(2, userid);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	public int memberPasswordChange(Connection conn, String userId, String pass, String salt) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="UPDATE TB_USER SET USER_PWD=?,SALT=? WHERE USER_ID = ?";
		try {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setString(1, pass);
			pstmt.setString(2, salt);
			pstmt.setString(3, userId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int resetPassword(Connection conn, String userId, String hex, String salt) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="UPDATE TB_USER SET USER_PWD=?, SALT = ? WHERE USER_ID = ?";
		try {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setString(1, hex);
			pstmt.setString(2, salt);
			pstmt.setString(3, userId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int countMember(Connection conn) {
		int result = 0;
		PreparedStatement pstmt= null;
		ResultSet rset= null;
		String qurey ="SELECT COUNT(*) FROM TB_USER";
		try {
			pstmt= conn.prepareStatement(qurey);
			rset = pstmt.executeQuery();
			while(rset.next()){
				result= rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);	
		}
		return result;
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
	public ArrayList<Semester> selectMyPermission(Connection conn, String userId) {
		ArrayList<Semester> mylist = new ArrayList<Semester>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT S.SEMESTER_NAME, P.USER_ID, P.SEMESTER_NO FROM TB_PERMISSION P "
				+ "JOIN TB_SEMESTER S ON P.SEMESTER_NO = S.SEMESTER_NO WHERE P.USER_ID = ?";
				
		try {
			pstmt  = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String semesterName = rset.getString(1);
				int semesterNo = rset.getInt(3);
				mylist.add(new Semester(semesterNo, userId, semesterName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return mylist;
	}
	public int addPermission(Connection conn, ArrayList<Semester> addper) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey = "INSERT INTO TB_PERMISSION VALUES(?,?)";
		try {
			for(int i = 0 ; i<addper.size();i++) {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setInt(1, addper.get(i).getSemesterNo());
			pstmt.setString(2, addper.get(i).getUserId());		
			result += pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public Semester selectSemesterNo(Connection conn, String semeName) {
		Semester seme = null;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT * FROM TB_SEMESTER WHERE SEMESTER_NAME = ?";
		try {
			pstmt  = conn.prepareStatement(query);
			pstmt.setString(1, semeName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int semesterNo = rset.getInt(1);
				String semesterName = rset.getString(2);
				seme =new Semester(semesterNo, semesterName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return seme;
	}
	public int removePermission(Connection conn, ArrayList<Semester> addper) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="DELETE FROM TB_PERMISSION WHERE SEMESTER_NO =? AND USER_ID=?";
		try {
			for(int i = 0 ; i<addper.size();i++) {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setInt(1, addper.get(i).getSemesterNo());
			pstmt.setString(2, addper.get(i).getUserId());		
			result += pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int lastModifiedPermission(Connection conn, String userId) {
		
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="UPDATE TB_USER SET LASTMODIFIED = NOW() WHERE USER_ID = ?";
		try {
			
			pstmt= conn.prepareStatement(qurey);
			pstmt.setString(1, userId);		
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int overlapDelete(Connection conn, String semeName, String userId) {
		int result = 0;
		PreparedStatement pstmt= null;
		String qurey ="DELETE FROM TB_PERMISSION WHERE SEMESTER_NO = (SELECT S.SEMESTER_NO FROM TB_SEMESTER S WHERE S.SEMESTER_NAME = ?) AND USER_ID=?";
		try {
			pstmt= conn.prepareStatement(qurey);
			pstmt.setString(1, semeName);
			pstmt.setString(2, userId);		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int allSearchListCount(String searchTitle, String fOption, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "";
		if(fOption.equals("userid")) {
			query = "SELECT COUNT(*) FROM TB_USER WHERE USER_ID LIKE ?";
		}else if(fOption.equals("username")) {
			query = "SELECT COUNT(*) FROM TB_USER WHERE PHONE LIKE ?";
		}else if(fOption.equals("phone")){
			query = "SELECT COUNT(*) FROM TB_USER WHERE USER_NAME LIKE ?";
		}else {
			query = "SELECT COUNT(*) FROM TB_USER";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(fOption.equals("userid")) {
				pstmt.setString(1, "%"+searchTitle+"%");
			}else if(fOption.equals("username")) {
				pstmt.setString(1, "%"+searchTitle+"%");
			}else if(fOption.equals("phone")) {
				pstmt.setString(1, "%"+searchTitle+"%");
			}
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public ArrayList<Member> selectMemberAll(String searchTitle, String fOption, int currentPage, int limit,
			Connection conn) {
		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startPage = (currentPage - 1 ) * limit;
		
		String query = "";
		if(fOption.equals("userid")) {
			query = "SELECT * " + 
					"FROM TB_USER " + 
					"WHERE USER_ID LIKE ? " + 
					"ORDER BY LASTMODIFIED DESC " + 
					"LIMIT ?, ?";
		}else if(fOption.equals("username")) {
			query = "SELECT * " + 
					"FROM TB_USER " + 
					"WHERE USER_NAME LIKE ? " + 
					"ORDER BY LASTMODIFIED DESC " + 
					"LIMIT ?, ?";
		}else if(fOption.equals("phone")) {
			query = "SELECT * " + 
					"FROM TB_USER " + 
					"WHERE PHONE LIKE ? " + 
					"ORDER BY LASTMODIFIED DESC " + 
					"LIMIT ?, ?";
		}else {
			query = "SELECT * " + 
					"FROM TB_USER " + 
					"ORDER BY LASTMODIFIED DESC " + 
					"LIMIT ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(fOption.equals("userid")) {
				pstmt.setString(1, "%" + searchTitle + "%");
				pstmt.setInt(2, startPage);
				pstmt.setInt(3, limit);
			}else if(fOption.equals("username")) {
				pstmt.setString(1, "%" + searchTitle + "%");
				pstmt.setInt(2, startPage);
				pstmt.setInt(3, limit);
			}else if(fOption.equals("phone")) {
				pstmt.setString(1, "%" + searchTitle + "%");
				pstmt.setInt(2, startPage);
				pstmt.setInt(3, limit);
			}else {
				pstmt.setInt(1, startPage);
				pstmt.setInt(2, limit);
			}
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String userId = rset.getString(1);
				String userPwd = rset.getString(2);
				String userName = rset.getString(3);
				String phone = rset.getString(4);
				Date registDate = rset.getDate(5);
				Date lastModified = rset.getDate(6);
				String memberLevel = rset.getString(7);
				String salt = rset.getString(8);
				list.add(new Member(userId, userPwd, userName, phone, registDate, lastModified, memberLevel,salt));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;	
	}
	}


