package faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import faq.model.vo.Faq;
import static common.JDBCTemplate.*;
public class FaqDao {
	
	public FaqDao() {}
	
	public ArrayList<Faq> searchAllList(String searchTitle, String fOption, int currentPage, int limit, Connection conn) {
		ArrayList<Faq> fList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startPage = (currentPage - 1 ) * limit;
		
		String query = "";
		if(fOption.equals("fTitle")) {
			query ="SELECT * " + 
					"FROM TB_FAQ " + 
					"WHERE QUESTION_CONTENT LIKE ? " + 
					"ORDER BY FAQ_NO DESC " + 
					"LIMIT ?, ?";
		}else if(fOption.equals("fTContent")) {
			query ="SELECT * " + 
					"FROM TB_FAQ " +
					"WHERE QUESTION_CONTENT LIKE ? OR ANSWER_CONTENT LIKE ? " +
					"ORDER BY FAQ_NO DESC " + 
					"LIMIT ?, ?";
		}else {
			query ="SELECT * " + 
					"FROM TB_FAQ " + 
					"ORDER BY FAQ_NO DESC " + 
					"LIMIT ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(fOption.equals("fTitle")) {
				pstmt.setString(1, "%" + searchTitle + "%");
				pstmt.setInt(2, startPage);
				pstmt.setInt(3, limit);
			}else if(fOption.equals("fTContent")) {
				pstmt.setString(1, "%" + searchTitle + "%");
				pstmt.setString(2, "%" + searchTitle + "%");
				pstmt.setInt(3, startPage);
				pstmt.setInt(4, limit);
			}else {
				pstmt.setInt(1, startPage);
				pstmt.setInt(2, limit);
			}
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Faq faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setQuestionContent(rset.getString("question_content"));
				faq.setAnswerContent(rset.getString("answer_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setAdminId(rset.getString("admin_id"));
				
				fList.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return fList;
	}
	
	public Faq faqDetail(int faqNo, Connection conn) {
		Faq faq = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT *  FROM TB_FAQ WHERE FAQ_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				faq = new Faq();
				faq.setFaqNo(faqNo);
				faq.setQuestionContent(rset.getString("question_content"));
				faq.setAnswerContent(rset.getString("answer_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setAdminId(rset.getString("admin_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return faq;
	}
	
	public int insertFaqWrite(Faq faq, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO TB_FAQ (QUESTION_CONTENT, ANSWER_CONTENT, FAQ_DATE, ADMIN_ID) VALUES (?, ?, NOW(), ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faq.getQuestionContent());
			pstmt.setString(2, faq.getAnswerContent());
			pstmt.setString(3, faq.getAdminId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int faqUpdate(Faq faq, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_FAQ SET QUESTION_CONTENT = ?, ANSWER_CONTENT = ? WHERE FAQ_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faq.getQuestionContent());
			pstmt.setString(2, faq.getAnswerContent());
			pstmt.setInt(3, faq.getFaqNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int faqDelete(int faqNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM TB_FAQ WHERE FAQ_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int allSearchListCount(String searchTitle, String fOption, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "";
		if(fOption.equals("fTitle")) {
			query = "SELECT COUNT(*) FROM TB_FAQ WHERE QUESTION_CONTENT LIKE ?";
		}else if(fOption.equals("fTContent")) {
			query = "SELECT COUNT(*) FROM TB_FAQ WHERE QUESTION_CONTENT LIKE ? OR ANSWER_CONTENT LIKE ?";
		}else {
			query = "SELECT COUNT(*) FROM TB_FAQ";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(fOption.equals("fTitle")) {
				pstmt.setString(1, "%"+searchTitle+"%");
			}else if(fOption.equals("fTContent")) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setString(2, "%"+searchTitle+"%");
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

	public int faqBack(int faqNo, Connection conn) {
		int faqBack = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT FAQ_NO FROM TB_FAQ WHERE FAQ_NO IN (SELECT MAX(FAQ_NO) FROM TB_FAQ WHERE FAQ_NO < ?)";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				faqBack = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return faqBack;
	}

	public int faqNext(int faqNo, Connection conn) {
		int faqNext = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT FAQ_NO FROM TB_FAQ WHERE FAQ_NO IN (SELECT MIN(FAQ_NO) FROM TB_FAQ WHERE FAQ_NO > ?)";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				faqNext = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return faqNext;
	}

	public int faqMin(Connection conn) {
		int faqMin = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT MIN(FAQ_NO) FROM TB_FAQ";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				faqMin = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return faqMin;
	}

	public ArrayList<Faq> faqUserList(Connection conn) {
		ArrayList<Faq> fList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_FAQ ORDER BY FAQ_NO DESC";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Faq faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setQuestionContent(rset.getString("question_content"));
				faq.setAnswerContent(rset.getString("answer_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setAdminId(rset.getString("admin_id"));
				
				fList.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return fList;
	}
	
}
