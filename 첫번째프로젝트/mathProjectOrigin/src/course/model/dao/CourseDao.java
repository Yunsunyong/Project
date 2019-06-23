package course.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import course.model.vo.Book;
import course.model.vo.Course;
import course.model.vo.Member;
import course.model.vo.Semester;

import static common.JDBCTemplate.*;

public class CourseDao {

	public ArrayList<Course> courseAllList(int currentPage, int limit, String sOption, String bOption, Connection conn) {
		ArrayList<Course> cList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startPage = (currentPage - 1 ) * limit;

		String query = "";
		if(sOption != "" && bOption != "") {
			query = "SELECT * " + 
					"FROM TB_COURSE C " + 
					"JOIN TB_BOOK B ON C.BOOK_NO = B.BOOK_NO " + 
					"JOIN TB_SEMESTER S ON B.SEMESTER_NO = S.SEMESTER_NO " + 
					"WHERE S.SEMESTER_NAME = ? AND B.BOOK_NAME = ? " + 
					"ORDER BY C.COURSE_NO DESC " + 
					"LIMIT ?, ?";
		}else {
			query = "SELECT * " + 
					"FROM TB_COURSE " + 
					"ORDER BY COURSE_NO DESC " + 
					"LIMIT ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(sOption != "" && bOption != "") {
				pstmt.setString(1, sOption);
				pstmt.setString(2, bOption);
				pstmt.setInt(3, startPage);
				pstmt.setInt(4, limit);
			}else {
				pstmt.setInt(1, startPage);
				pstmt.setInt(2, limit);
			}
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Course course = new Course();
				
				course.setCourseNo(rset.getInt("course_no"));
				course.setCourseTitle(rset.getString("course_title"));
				course.setCourseContent(rset.getString("course_content"));
				course.setCourseDate(rset.getDate("course_date"));
				course.setBookNo(rset.getInt("book_no"));
				course.setSemesterNo(rset.getInt("semester_no"));
				
				cList.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cList;
	}

	public int allListCount(String sOption, String bOption, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "";
		if(sOption != "" && bOption != "") {		
			query = "SELECT COUNT(*) FROM TB_COURSE CC WHERE CC.COURSE_NO IN " + 
					"(SELECT C.COURSE_NO FROM TB_BOOK B "  + 
					"JOIN TB_SEMESTER S ON B.SEMESTER_NO = S.SEMESTER_NO " + 
					"JOIN TB_COURSE C ON B.BOOK_NO = C.BOOK_NO " + 
					"WHERE S.SEMESTER_NAME = ? AND B.BOOK_NAME = ?)";
		}else {
			query = "SELECT COUNT(*) FROM TB_COURSE";
		}
		try {
			pstmt = conn.prepareStatement(query);
			if(sOption != "" && bOption != "") {
				pstmt.setString(1, sOption);
				pstmt.setString(2, bOption);
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Semester> semList(Connection conn) {
		ArrayList<Semester> semList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_SEMESTER ORDER BY SEMESTER_NAME";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Semester sem = new Semester();
				sem.setSemesterNo(rset.getInt("semester_no"));
				sem.setSemesterName(rset.getString("semester_name"));
				semList.add(sem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return semList;
	}

	public ArrayList<Book> bookList(String semester, Connection conn) {
		ArrayList<Book> bList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT B.BOOK_NAME " + 
				"FROM TB_BOOK B " + 
				"WHERE B.SEMESTER_NO = (SELECT S.SEMESTER_NO " + 
				"							FROM TB_SEMESTER S " + 
				"							WHERE S.SEMESTER_NAME = ?) "
				+ "ORDER BY B.BOOK_NAME";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, semester);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book book = new Book();
				
				book.setBookName(rset.getString("book_name"));
				
				bList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	public int courseWrite(Connection conn,Course course, String sOption, String bOption) {
		int result = 0;
		PreparedStatement pstmt = null;
		String option = "["+sOption+" "+bOption+"] ";
		String query = "INSERT INTO TB_COURSE (COURSE_TITLE, COURSE_CONTENT, COURSE_DATE, BOOK_NO, SEMESTER_NO) VALUES " + 
				"(?, ?, NOW(),(SELECT B.BOOK_NO FROM TB_BOOK B JOIN TB_SEMESTER S ON B.SEMESTER_NO = S.SEMESTER_NO WHERE S.SEMESTER_NAME = ? AND B.BOOK_NAME = ?), " + 
				"(SELECT SS.SEMESTER_NO FROM TB_SEMESTER SS WHERE SS.SEMESTER_NAME = ?))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, option+course.getCourseTitle());
			pstmt.setString(2, course.getCourseContent());
			pstmt.setString(3, sOption);
			pstmt.setString(4, bOption);
			pstmt.setString(5, sOption);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Course courseDetail(int courseNo, Connection conn) {
		Course course = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT *  FROM TB_COURSE WHERE COURSE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, courseNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				course = new Course();
				course.setCourseNo(courseNo);
				course.setCourseTitle(rset.getString("course_title"));
				course.setCourseContent(rset.getString("course_content"));
				course.setCourseDate(rset.getDate("course_date"));
				course.setBookNo(rset.getInt("book_no"));
				course.setSemesterNo(rset.getInt("semester_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return course;
	}

	public int courseDelete(int courseNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM TB_COURSE WHERE COURSE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, courseNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int courseUpdate(Course course, String sOption, String bOption, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String option = "["+sOption+bOption+"] ";
		String query = "UPDATE TB_COURSE C SET C.COURSE_TITLE = ?, C.COURSE_CONTENT = ?, C.SEMESTER_NO = (SELECT S.SEMESTER_NO FROM TB_SEMESTER S WHERE S.SEMESTER_NAME = ?), " + 
				"C.BOOK_NO = (SELECT BR.BOOK_NO FROM (SELECT B.BOOK_NO FROM TB_BOOK B WHERE B.SEMESTER_NO = (SELECT SS.SEMESTER_NO FROM TB_SEMESTER SS WHERE SS.SEMESTER_NAME = ?) AND B.BOOK_NAME = ?)BR ) " + 
				"WHERE C.COURSE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, option+course.getCourseTitle());
			pstmt.setString(2, course.getCourseContent());
			pstmt.setString(3, sOption);
			pstmt.setString(4, sOption);
			pstmt.setString(5, bOption);
			pstmt.setInt(6, course.getCourseNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member userMember(String userId, Connection conn) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT  *  FROM TB_USER WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				
				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setPhone(rset.getString("phone"));
				member.setRegistDate(rset.getDate("regist_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setmemberLevel(rset.getString("member_level"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}


	public String selectSName(int courseNo, Connection conn) {
		String sName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT S.SEMESTER_NAME " + 
				"FROM TB_SEMESTER S " + 
				"WHERE S.SEMESTER_NO = (SELECT C.SEMESTER_NO " + 
				"				         					 FROM TB_COURSE C  " + 
				"											 WHERE C.COURSE_NO = ?)"; 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, courseNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				sName = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return sName;
	}

	public String selectBName(int courseNo, Connection conn) {
		String bName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT B.BOOK_NAME " + 
				"FROM TB_BOOK B " + 
				"WHERE B.BOOK_NO = " + 
				"(SELECT C.BOOK_NO " + 
				"FROM TB_COURSE C " + 
				"WHERE C.COURSE_NO = ?)"; 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, courseNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				bName = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bName;
	}

}
