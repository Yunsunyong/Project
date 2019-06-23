package course.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import course.model.dao.CourseDao;
import course.model.vo.Book;
import course.model.vo.Course;
import course.model.vo.Member;
import course.model.vo.Semester;

import static common.JDBCTemplate.*;
public class CourseService {
	private CourseDao cdao = new CourseDao();

	public ArrayList<Course> courseAllList(int currentPage, int limit, String sOption, String bOption) {
		Connection conn = getConnection();
		ArrayList<Course> cList = cdao.courseAllList(currentPage, limit, sOption, bOption, conn);
		close(conn);
		return cList;
	}

	public int allListCount(String sOption, String bOption) {
		Connection conn = getConnection();
		int result = cdao.allListCount(sOption, bOption, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Semester> semList() {
		Connection conn = getConnection();
		ArrayList<Semester> semList = cdao.semList(conn);
		close(conn);
		return semList;
	}

	public ArrayList<Book> bookList(String semester) {
		Connection conn = getConnection();
		ArrayList<Book> bList = cdao.bookList(semester,conn);
		close(conn);
		return bList;
	}

	public int courseWrite(Course course, String sOption, String bOption) {
		Connection conn = getConnection();
		int result = cdao.courseWrite(conn,course, sOption, bOption);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Course courseDetail(int courseNo) {
		Connection conn = getConnection();
		Course course = cdao.courseDetail(courseNo, conn);
		close(conn);
		return course;
	}

	public int courseDelete(int courseNo) {
		Connection conn = getConnection();
		int result = cdao.courseDelete(courseNo, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int courseUpdate(Course course, String sOption, String bOption) {
		Connection conn = getConnection();
		int result = cdao.courseUpdate(course, sOption, bOption, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Member userMember(String userId) {
		Connection conn = getConnection();
		Member member = cdao.userMember(userId, conn);
		close(conn);
		return member;
	}

	public String selectSName(int courseNo) {
		Connection conn = getConnection();
		String sName = cdao.selectSName(courseNo, conn);
		close(conn);
		return sName;
	}

	public String selectBName(int courseNo) {
		Connection conn = getConnection();
		String bName = cdao.selectBName(courseNo, conn);
		close(conn);
		return bName;
	}
	
	
}
