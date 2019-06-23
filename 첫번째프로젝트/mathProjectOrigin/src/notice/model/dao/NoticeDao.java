package notice.model.dao;

import java.sql.*;
import java.util.ArrayList;

import notice.model.vo.Notice;

import static common.JDBCTemplate.*;
public class NoticeDao {
	public NoticeDao() {}

	public ArrayList<Notice> searchAllList(String searchTitle,String nOption,int currentPage, int limit, Connection conn) {
		ArrayList<Notice> nsList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (currentPage - 1) * limit;

		String query = "";		
		if(nOption.equals("ntitle")) {
			query = "SELECT * " + 
					"FROM TB_NOTICE " + 
					"WHERE NOTICE_TITLE LIKE ? " + 
					"ORDER BY NOTICE_NO DESC " + 
					"LIMIT ?, ?";
		}else if(nOption.equals("ntContent")){
			query = "SELECT * " + 
					"FROM TB_NOTICE " + 
					"WHERE NOTICE_TITLE LIKE ? " + 
					"ORDER BY NOTICE_NO DESC " + 
					"LIMIT ?, ?";
		}else {
			query = "SELECT * " + 
					"FROM TB_NOTICE  " + 
					"ORDER BY NOTICE_NO DESC " + 
					"LIMIT ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(nOption.equals("ntitle")) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
			}else if(nOption.equals("ntContent")){
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setString(2, "%"+searchTitle+"%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, limit);
			}else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
			}
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeCount(rset.getInt("notice_count"));
				notice.setOriginalFileName(rset.getString("original_filename"));
				notice.setRenameFileName(rset.getString("rename_filename"));
				notice.setWriterName(rset.getString("writer_name"));
				
				nsList.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return nsList;
	}
	

	public Notice selectNDetail(int noticeNo, Connection conn) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice();
				
				notice.setNoticeNo(noticeNo);
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeCount(rset.getInt("notice_count"));
				notice.setOriginalFileName(rset.getString("original_filename"));
				notice.setRenameFileName(rset.getString("rename_filename"));
				notice.setWriterName(rset.getString("writer_name"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int allSearchListCount(String searchTitle,String nOption, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if(nOption.equals("ntitle")){
			query = "SELECT COUNT(*) FROM TB_NOTICE WHERE NOTICE_TITLE LIKE ?";
		}else if(nOption.equals("ntContent")) {
			query = "SELECT COUNT(*) FROM TB_NOTICE WHERE NOTICE_TITLE LIKE ? OR NOTICE_CONTENT LIKE ?";
		}else {
			query = "SELECT COUNT(*) FROM TB_NOTICE";
		}
		try {
			pstmt = conn.prepareStatement(query);
			if(nOption.equals("ntitle")) {
				pstmt.setString(1, "%" + searchTitle + "%");
			}else if(nOption.equals("ntContent")) {
				pstmt.setString(1, "%" + searchTitle + "%");
				pstmt.setString(2, "%"+ searchTitle +"%");
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
	
	public int updateNotice(Notice notice, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_NOTICE SET NOTICE_TITLE = ?, NOTICE_CONTENT = ?, ORIGINAL_FILENAME = ?, RENAME_FILENAME = ? WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getOriginalFileName());
			pstmt.setString(4, notice.getRenameFileName());
			pstmt.setInt(5, notice.getNoticeNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertNoticeWrite(Notice notice, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TB_NOTICE (NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_COUNT, ORIGINAL_FILENAME, RENAME_FILENAME, WRITER_NAME) VALUES (?, ?, NOW(), DEFAULT, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getOriginalFileName());
			pstmt.setString(4, notice.getRenameFileName());
			pstmt.setString(5, notice.getWriterName());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int addReadCount(int noticeNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_NOTICE SET NOTICE_COUNT = NOTICE_COUNT + 1 WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int noticeDelete(int noticeNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM TB_NOTICE WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int noticeBack(int noticeNo,Connection conn) {
		int noticeBack = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query ="SELECT NOTICE_NO FROM TB_NOTICE WHERE NOTICE_NO IN (SELECT MAX(NOTICE_NO) FROM TB_NOTICE WHERE NOTICE_NO < ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			 if(rset.next()) {
				 noticeBack = rset.getInt(1);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeBack;
	}

	public int noticeNext(int noticeNo,Connection conn) {
		int noticeNext = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="SELECT NOTICE_NO FROM TB_NOTICE WHERE NOTICE_NO IN (SELECT MIN(NOTICE_NO) FROM TB_NOTICE WHERE NOTICE_NO > ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			 if(rset.next()) {
				 noticeNext = rset.getInt(1);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeNext;
	}

	public int noticeMin(Connection conn) {
		int noticeMin = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query ="SELECT MIN(NOTICE_NO) FROM TB_NOTICE";
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			 if(rset.next()) {
				 noticeMin = rset.getInt(1);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return noticeMin;
	}

}
