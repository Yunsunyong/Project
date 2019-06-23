package question.model.service;

import java.sql.*;
import java.util.ArrayList;

import question.model.dao.QuestionDao;
import question.model.vo.Question;
import static common.JDBCTemplate.*;

public class QuestionService {
	public QuestionService() {}
	private QuestionDao qdao = new QuestionDao();
	
	public ArrayList<Question> semesterSelect() {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.semesterSelect(conn);
		close(conn);
		return list;
	}

	public ArrayList<Question> bookSelect(String semester) {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.bookSelect(conn, semester);
		close(conn);
		return list;
	}

	public ArrayList<Question> chapterSelect(String semester, String book) {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.chapterSelect(conn, semester, book);
		close(conn);
		return list;
	}

	public ArrayList<Question> QuestionImgSelect(String semester, String book, String chapter) {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.QuestionImgSelect(conn, semester, book, chapter);
		close(conn);
		return list;
	}

	public int makeSemester(String newSemester) {
		Connection conn = getConnection();
		int result = qdao.makeSemester(conn, newSemester);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int makeBook(String semester, String newBook) {
		Connection conn = getConnection();
		int result = qdao.makeBook(conn, semester, newBook);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int makeChapter(String semester, String book, String newChapter) {
		Connection conn = getConnection();
		int result = qdao.makeChapter(conn, semester, book, newChapter);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int uploadImg(String semester, String book, String chapter, String imgName) {
		Connection conn = getConnection();
		int result = qdao.uploadImg(conn, semester, book, chapter, imgName);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteSemester(String semester) {
		Connection conn = getConnection();
		int result = qdao.deleteSemester(conn, semester);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteBook(String semester, String book) {
		Connection conn = getConnection();
		int result = qdao.deleteBook(conn, semester, book);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteChapter(String semester, String book, String chapter) {
		Connection conn = getConnection();
		int result = qdao.deleteChapter(conn, semester, book, chapter);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteImg(String semester, String book, String chapter, String imgName) {
		Connection conn = getConnection();
		int result = qdao.deleteImg(conn, semester, book, chapter, imgName);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

}
