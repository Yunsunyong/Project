package question.model.dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import static common.JDBCTemplate.*;
import question.model.vo.Question;

public class QuestionDao {
   public QuestionDao() {}

   public ArrayList<Question> semesterSelect(Connection conn) {
      ArrayList<Question> list = new ArrayList<Question>();
      
      Statement stmt = null;
      ResultSet rset = null;
      
      String query = "SELECT SEMESTER_NAME FROM TB_SEMESTER ORDER BY SEMESTER_NO";
      
      try {
         stmt = conn.createStatement();
         rset = stmt.executeQuery(query);
         
         while(rset.next()) {
            Question q = new Question();
            q.setSemester(rset.getString("semester_name"));
            
            list.add(q);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(rset);
         close(stmt);
      }
      return list;
   }

   public ArrayList<Question> bookSelect(Connection conn, String semester) {
      ArrayList<Question> list = new ArrayList<Question>();
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "SELECT B.BOOK_NAME FROM TB_BOOK B JOIN TB_SEMESTER S ON B.SEMESTER_NO = S.SEMESTER_NO WHERE S.SEMESTER_NAME = ? "
      		+ "ORDER BY B.BOOK_NO";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, semester);
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Question q = new Question();
            q.setBook(rset.getString("book_name"));          
            list.add(q);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
         close(rset);
      }
      return list;
   }

   public ArrayList<Question> chapterSelect(Connection conn, String semester, String book) {
      ArrayList<Question> list = new ArrayList<Question>();
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "SELECT C.CHAPTER_NAME FROM TB_SEMESTER S "
            + "JOIN TB_BOOK B ON S.SEMESTER_NO = B.SEMESTER_NO "
            + "JOIN TB_CHAPTER C ON B.BOOK_NO = C.BOOK_NO WHERE S.SEMESTER_NAME = ? AND B.BOOK_NAME = ? "
            + "ORDER BY C.CHAPTER_NAME*1";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, semester);
         pstmt.setString(2, book);
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Question q = new Question();
            q.setChapter(rset.getString("chapter_name"));
            list.add(q);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
         close(rset);
      }
      return list;
   }

   public ArrayList<Question> QuestionImgSelect(Connection conn, String semester, String book, String chapter) {
      ArrayList<Question> list = new ArrayList<Question>();
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "SELECT Q.QUESTION_IMG FROM TB_SEMESTER S " + 
            "JOIN TB_BOOK B ON S.SEMESTER_NO = B.SEMESTER_NO " + 
            "JOIN TB_CHAPTER C ON B.BOOK_NO = C.BOOK_NO " + 
            "JOIN TB_QUESTION Q ON C.CHAPTER_NO = Q.CHAPTER_NO " +
            "WHERE SEMESTER_NAME = ? AND BOOK_NAME = ?  AND CHAPTER_NAME = ? " +
            "ORDER BY Q.SEMESTER_NO, Q.BOOK_NO, Q.CHAPTER_NO, Q.QUESTION_IMG";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, semester);
         pstmt.setString(2, book);
         pstmt.setString(3, chapter);
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Question q = new Question();
            q.setQuestion(rset.getString("question_img"));
            
            list.add(q);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
         close(rset);
      }
      return list;
   }

   public int makeSemester(Connection conn, String newSemester) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "INSERT INTO TB_SEMESTER (SEMESTER_NAME) VALUES (?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, newSemester);
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int makeBook(Connection conn, String semester, String newBook) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "INSERT INTO TB_BOOK (SEMESTER_NO, BOOK_NAME) VALUES ((SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?), ?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, semester);
         pstmt.setString(2, newBook);
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int makeChapter(Connection conn, String semester, String book, String newChapter) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "INSERT INTO TB_CHAPTER (BOOK_NO, SEMESTER_NO, CHAPTER_NAME) VALUES ((SELECT BOOK_NO FROM TB_BOOK WHERE BOOK_NAME = ? AND SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?)), "
            + "(SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?), ?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, book);
         pstmt.setString(2, semester);
         pstmt.setString(3, semester);
         pstmt.setString(4, newChapter);
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int uploadImg(Connection conn, String semester, String book, String chapter, String imgName) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "INSERT INTO TB_QUESTION VALUES (?, (SELECT CHAPTER_NO FROM TB_CHAPTER WHERE CHAPTER_NAME = ? AND BOOK_NO = (SELECT BOOK_NO FROM TB_BOOK WHERE BOOK_NAME = ? AND SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?))), "
            + "(SELECT BOOK_NO FROM TB_BOOK WHERE BOOK_NAME = ? AND SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?)), "
            + "(SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?))";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, imgName);
         pstmt.setString(2, chapter);
         pstmt.setString(3, book);
         pstmt.setString(4, semester);
         pstmt.setString(5, book);
         pstmt.setString(6, semester);
         pstmt.setString(7, semester);
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int deleteSemester(Connection conn, String semester) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "DELETE FROM TB_SEMESTER WHERE SEMESTER_NAME = ?";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, semester);
         
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int deleteBook(Connection conn, String semester, String book) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "DELETE FROM TB_BOOK WHERE BOOK_NAME = ? AND SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, book);
         pstmt.setString(2, semester);
         
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int deleteChapter(Connection conn, String semester, String book, String chapter) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "DELETE FROM TB_CHAPTER WHERE CHAPTER_NAME = ? AND BOOK_NO = (SELECT BOOK_NO FROM TB_BOOK WHERE BOOK_NAME = ?) AND "
            + "SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, chapter);
         pstmt.setString(2, book);
         pstmt.setString(3, semester);
         
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public int deleteImg(Connection conn, String semester, String book, String chapter, String imgName) {
      int result = 0;
      PreparedStatement pstmt = null;
      String query = "DELETE FROM TB_QUESTION WHERE QUESTION_IMG = ? AND CHAPTER_NO = (SELECT CHAPTER_NO FROM TB_CHAPTER WHERE CHAPTER_NAME = ? AND BOOK_NO = (SELECT BOOK_NO FROM TB_BOOK WHERE BOOK_NAME = ? AND SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?))) "
            + "AND BOOK_NO = (SELECT BOOK_NO FROM TB_BOOK WHERE BOOK_NAME = ? AND SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?)) AND "
            + "SEMESTER_NO = (SELECT SEMESTER_NO FROM TB_SEMESTER WHERE SEMESTER_NAME = ?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, imgName);
         pstmt.setString(2, chapter);
         pstmt.setString(3, book);
         pstmt.setString(4, semester);
         pstmt.setString(5, book);
         pstmt.setString(6, semester);
         pstmt.setString(7, semester);
         result = pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }
      
      return result;
   }

}