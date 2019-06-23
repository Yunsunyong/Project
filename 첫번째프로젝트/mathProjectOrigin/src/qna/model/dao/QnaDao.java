package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import qna.model.vo.Qna;

import static common.JDBCTemplate.*;
public class QnaDao {
	public QnaDao() {}
	
	public ArrayList<Qna> searchAllList(String searchTitle, String qOption, int currentPage, int limit, Connection conn){
		ArrayList<Qna> qList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startPage = (currentPage - 1) * limit;
		
		String query = "";
		if(qOption.equals("qTitle")) {
			query = "SELECT * " + 
					"FROM TB_QNA Q1 " + 
					"WHERE Q1.PARENT_NO IN (SELECT Q2.PARENT_NO FROM TB_QNA Q2 " + 
					"WHERE Q2.QNA_NO = Q2.PARENT_NO AND Q2.ANSWER_REF > 0 OR Q2.QNA_TITLE LIKE ?) " + 
					"ORDER BY Q1.PARENT_NO DESC, Q1.ANSWER_REF ASC, Q1.QNA_LEVEL ASC, Q1.QNA_INDEX ASC " + 
					"LIMIT ?, ?";
		}else if(qOption.equals("qTContent")) {
			query = "SELECT * " + 
					"FROM TB_QNA Q1 " + 
					"WHERE Q1.PARENT_NO IN (SELECT Q2.PARENT_NO FROM TB_QNA Q2 " + 
					"WHERE (Q2.QNA_NO = Q2.PARENT_NO AND Q2.ANSWER_REF > 0) OR (Q2.QNA_TITLE LIKE ? OR Q2.QNA_CONTENT LIKE ?)) " + 
					"ORDER BY Q1.PARENT_NO DESC, Q1.ANSWER_REF ASC, Q1.QNA_LEVEL ASC, Q1.QNA_INDEX ASC " + 
					"LIMIT ?, ?";
		}else if(qOption.equals("qUserId")) {
			query = "SELECT * " + 
					"FROM TB_QNA Q1 " + 
					"WHERE Q1.PARENT_NO IN (SELECT Q2.PARENT_NO FROM TB_QNA Q2 " + 
					"WHERE Q2.QNA_NO = Q2.PARENT_NO AND Q2.QNA_WRITER LIKE ?) " + 
					"ORDER BY Q1.PARENT_NO DESC, Q1.ANSWER_REF ASC, Q1.QNA_LEVEL ASC, Q1.QNA_INDEX ASC " + 
					"LIMIT ?, ?";
		}else {
			query = "SELECT * " + 
					"FROM TB_QNA " + 
					"ORDER BY PARENT_NO DESC, ANSWER_REF ASC, QNA_LEVEL ASC, QNA_INDEX ASC " + 
					"LIMIT ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(qOption.equals("qTitle")) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setInt(2, startPage);
				pstmt.setInt(3, limit);
			}else if(qOption.equals("qTContent")) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setString(2, "%"+searchTitle+"%");
				pstmt.setInt(3, startPage);
				pstmt.setInt(4, limit);
			}else if(qOption.equals("qUserId")) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setInt(2, startPage);
				pstmt.setInt(3, limit);
			}else {
				pstmt.setInt(1, startPage);
				pstmt.setInt(2, limit);
			}
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setQnaContent(rset.getString("qna_content"));
				qna.setQnaDate(rset.getDate("qna_date"));
				qna.setOriginalQname(rset.getString("original_qname"));
				qna.setRenameQname(rset.getString("rename_qname"));
				qna.setParentNo(rset.getInt("parent_no"));
				qna.setAnswerRef(rset.getInt("answer_ref"));
				qna.setQnaLevel(rset.getInt("qna_level"));
				qna.setQnaIndex(rset.getInt("qna_index"));
				qna.setQnaStatus(rset.getString("qna_status"));
				qna.setQnaWriter(rset.getString("qna_writer"));
				
				qList.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return qList;
	}
	
	public Qna qnaDetail(int qnaNo, Connection conn) {
		Qna qna = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_QNA WHERE QNA_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				qna = new Qna();
				
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setQnaContent(rset.getString("qna_content"));
				qna.setQnaDate(rset.getDate("qna_date"));
				qna.setOriginalQname(rset.getString("original_qname"));
				qna.setRenameQname(rset.getString("rename_qname"));
				qna.setParentNo(rset.getInt("parent_no"));
				qna.setAnswerRef(rset.getInt("answer_ref"));
				qna.setQnaLevel(rset.getInt("qna_level"));
				qna.setQnaIndex(rset.getInt("qna_index"));
				qna.setQnaStatus(rset.getString("qna_status"));
				qna.setQnaWriter(rset.getString("qna_writer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return qna;
	}
	
	public int insertAnswerWrite(Qna qna, Connection conn) {
		int aInsert = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO TB_QNA (QNA_TITLE, QNA_CONTENT, QNA_DATE, ORIGINAL_QNAME, RENAME_QNAME, PARENT_NO, ANSWER_REF, QNA_LEVEL, QNA_INDEX, QNA_STATUS, QNA_WRITER) "
				+ "VALUES (?,?,NOW(),?,?,?,(SELECT IFNULL(MAX(Q.QNA_NO) ,0) +1 FROM TB_QNA Q),1,1,'Y',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getQnaTitle());
			pstmt.setString(2, qna.getQnaContent());
			pstmt.setString(3, qna.getOriginalQname());
			pstmt.setString(4, qna.getRenameQname());
			pstmt.setInt(5, qna.getParentNo());
			pstmt.setString(6, qna.getQnaWriter());
			
			aInsert = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return aInsert;
	}
	
	public int answerUpdate(Qna qna, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_QNA SET QNA_TITLE = ?, QNA_CONTENT = ?, ORIGINAL_QNAME = ?, RENAME_QNAME = ? WHERE QNA_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getQnaTitle());
			pstmt.setString(2, qna.getQnaContent());
			pstmt.setString(3, qna.getOriginalQname());
			pstmt.setString(4, qna.getRenameQname());
			pstmt.setInt(5, qna.getQnaNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int qnaDelete(int qnaNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM TB_QNA " + 
				"WHERE QNA_NO IN ( " + 
				"SELECT QNA_NO " + 
				"FROM "
				+ "(SELECT Q.QNA_NO "
				+ "FROM TB_QNA Q " + 
				"WHERE Q.PARENT_NO IN (SELECT Q2.PARENT_NO " + 
				"                        FROM TB_QNA Q2 " + 
				"                        WHERE Q2.QNA_NO = ?)) QQ)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int qnaAnswerDelete(int qnaNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM TB_QNA WHERE QNA_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int allsearchListCount(String searchTitle, String qOption, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "";
		if(qOption.equals("qTitle")) {
			query = "SELECT COUNT(*) FROM TB_QNA WHERE QNA_TITLE LIKE ?";
		}else if(qOption.equals("qTContent")) {
			query = "SELECT COUNT(*) FROM TB_QNA WHERE QNA_TITLE LIKE ? OR QNA_CONTENT LIKE ?";
		}else if(qOption.equals("qUserId")) {
			query = "SELECT COUNT(*) FROM TB_QNA WHERE QNA_WRITER LIKE ?";
		}else {
			query = "SELECT COUNT(*) FROM TB_QNA";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(qOption.equals("qTitle")) {
				pstmt.setString(1, "%"+searchTitle+"%");
			}else if(qOption.equals("qTContent")) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setString(2, "%"+searchTitle+"%");
			}else if(qOption.equals("qUserId")) {
				pstmt.setString(1, "%"+searchTitle+"%");
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

	public int qnaUpdateQStatus(int qnaNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_QNA " + 
				"SET QNA_STATUS = 'N' " + 
				"WHERE QNA_NO = (SELECT PARENT_NO " + 
				"                        FROM TB_QNA " + 
				"                        WHERE QNA_NO = ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int qnaUpdateStatus(int qnaNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_QNA SET QNA_STATUS = 'Y' WHERE QNA_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Qna> qnaUserMyList(String userId, int currentPage, int limit, Connection conn) {
		ArrayList<Qna> qList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startPage = (currentPage - 1) * limit;
		
		String query = "SELECT * " + 
				"FROM TB_QNA Q1 " + 
				"WHERE Q1.PARENT_NO IN (SELECT Q2.PARENT_NO FROM TB_QNA Q2 " + 
				"WHERE Q2.QNA_NO = Q2.PARENT_NO AND Q2.QNA_WRITER = ?) " + 
				"ORDER BY Q1.PARENT_NO DESC, Q1.ANSWER_REF ASC, Q1.QNA_LEVEL ASC, Q1.QNA_INDEX ASC " + 
				"LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, startPage);
			pstmt.setInt(3, limit);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setQnaContent(rset.getString("qna_content"));
				qna.setQnaDate(rset.getDate("qna_date"));
				qna.setOriginalQname(rset.getString("original_qname"));
				qna.setRenameQname(rset.getString("rename_qname"));
				qna.setParentNo(rset.getInt("parent_no"));
				qna.setAnswerRef(rset.getInt("answer_ref"));
				qna.setQnaLevel(rset.getInt("qna_level"));
				qna.setQnaIndex(rset.getInt("qna_index"));
				qna.setQnaStatus(rset.getString("qna_status"));
				qna.setQnaWriter(rset.getString("qna_writer"));
				
				qList.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return qList;
	}

	public int myUserListCount(String userId, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) " + 
				"FROM (SELECT * FROM TB_QNA Q1 WHERE Q1.PARENT_NO IN ( " + 
				"SELECT Q2.PARENT_NO " + 
				"FROM TB_QNA Q2 " + 
				"WHERE Q2.QNA_NO = Q2.PARENT_NO AND Q2.QNA_WRITER = ? " + 
				"))QQ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
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

	public int insertQuestionWrite(Qna qna, Connection conn) {
		int qInsert = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO TB_QNA (QNA_TITLE, QNA_CONTENT, QNA_DATE, ORIGINAL_QNAME, RENAME_QNAME, PARENT_NO, ANSWER_REF, QNA_LEVEL, QNA_INDEX, QNA_STATUS, QNA_WRITER) "
				+ "VALUES (?,?,NOW(),?,?,(SELECT IFNULL(MAX(Q.QNA_NO) ,0) +1 FROM TB_QNA Q),DEFAULT,DEFAULT,DEFAULT,'N',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getQnaTitle());
			pstmt.setString(2, qna.getQnaContent());
			pstmt.setString(3, qna.getOriginalQname());
			pstmt.setString(4, qna.getRenameQname());
			pstmt.setString(5, qna.getQnaWriter());
			
			qInsert = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return qInsert;
	}
	
}
