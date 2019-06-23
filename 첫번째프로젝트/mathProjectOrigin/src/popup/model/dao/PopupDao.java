package popup.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static common.JDBCTemplate.*;

import popup.model.vo.Popup;

public class PopupDao {
	
	public PopupDao() {}
	
public ArrayList<Popup> listPopup(String searchTitle,int currentPage, int limit, Connection conn) {
		ArrayList<Popup> pArr = new ArrayList<Popup>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startRow = (currentPage - 1) * limit;
		
		String query = "";
		if(searchTitle != null) {
		query = "SELECT * " + 
				"FROM TB_POPUP " + 
				"WHERE POPUP_NAME LIKE ? " +
				"ORDER BY POPUP_NO DESC " + 
				"LIMIT ?, ?";
		
		}else {
			query = "SELECT * " + 
					"FROM TB_POPUP " +
					"ORDER BY POPUP_NO DESC " + 
					"LIMIT ?, ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			
			if(searchTitle != null) {
				pstmt.setString(1, "%"+searchTitle+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
			}else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Popup popup = new Popup();
				
				popup.setPopupNo(rset.getInt("popup_no"));
				popup.setPopupName(rset.getString("popup_name"));
				popup.setPopupLink(rset.getString("popup_link"));
				popup.setPopupX(rset.getInt("popup_x"));
				popup.setPopupY(rset.getInt("popup_Y"));
				popup.setPopupWidth(rset.getInt("popup_width"));
				popup.setPopupHeight(rset.getInt("popup_height"));
				popup.setPopupDate(rset.getDate("popup_date"));
				popup.setPopupEndDate(rset.getDate("popup_enddate"));
				popup.setPopupImagePath(rset.getString("popup_imagepath"));
				popup.setPopupImgLink(rset.getString("popup_imgLink"));
				popup.setPopupExplan(rset.getString("popup_explan"));
				
				pArr.add(popup);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return pArr;
	}

	public int updatePopup(Connection conn, Popup popup) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE TB_POPUP SET POPUP_NAME = ?, POPUP_LINK = ?, POPUP_X = ?, POPUP_Y = ?, POPUP_WIDTH = ?, POPUP_HEIGHT = ?, POPUP_DATE = ?, POPUP_ENDDATE = ?, POPUP_IMAGEPATH = ?, POPUP_IMGLINK = ?, POPUP_EXPLAN = ? WHERE POPUP_NO = ? ";
		
		try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, popup.getPopupName());
				pstmt.setString(2, popup.getPopupLink());
				pstmt.setInt(3, popup.getPopupX());
				pstmt.setInt(4, popup.getPopupY());
				pstmt.setInt(5, popup.getPopupWidth());
				pstmt.setInt(6, popup.getPopupHeight());
				pstmt.setDate(7, popup.getPopupDate());
				pstmt.setDate(8, popup.getPopupEndDate());
				pstmt.setString(9, popup.getPopupImagePath());
				pstmt.setString(10, popup.getPopupImgLink());
				pstmt.setString(11, popup.getPopupExplan());
				pstmt.setInt(12, popup.getPopupNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deletePopup(Connection conn, int popupNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM TB_POPUP WHERE POPUP_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, popupNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertPopup(Connection conn, Popup popup) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TB_POPUP (POPUP_NAME, POPUP_LINK, POPUP_X, POPUP_Y, POPUP_WIDTH, POPUP_HEIGHT, POPUP_DATE, POPUP_ENDDATE, POPUP_IMAGEPATH, POPUP_IMGLINK, POPUP_EXPLAN, ADMIN_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'admin01')";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, popup.getPopupName());
			pstmt.setString(2, popup.getPopupLink());
			pstmt.setInt(3, popup.getPopupX());
			pstmt.setInt(4, popup.getPopupY());
			pstmt.setInt(5, popup.getPopupWidth());
			pstmt.setInt(6, popup.getPopupHeight());
			pstmt.setDate(7, popup.getPopupDate());
			pstmt.setDate(8, popup.getPopupEndDate());
			pstmt.setString(9, popup.getPopupImagePath());
			pstmt.setString(10, popup.getPopupImgLink());
			pstmt.setString(11, popup.getPopupExplan());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Popup selectPopup(Connection conn, int popupNo) {
		Popup popup= null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_POPUP WHERE POPUP_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, popupNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				popup = new Popup();
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return popup;
	}

	public int AllSearchListCount(String searchTitle, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if(searchTitle != null) {
			query = "SELECT COUNT(*) FROM TB_POPUP WHERE POPUP_NAME LIKE ?";
		}else {
			query = "SELECT COUNT(*) FROM TB_POPUP";
		}
		try {
			pstmt = conn.prepareStatement(query);
			if(searchTitle != null) {
				pstmt.setString(1, "%" + searchTitle + "%");
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

	public Popup selectPDetail(int popupNo, Connection conn) {
		Popup popup = new Popup();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_POPUP WHERE POPUP_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, popupNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
	
				
				popup.setPopupNo(popupNo);
				popup.setPopupName(rset.getString("popup_name"));
				popup.setPopupLink(rset.getString("popup_link"));
				popup.setPopupX(rset.getInt("popup_x"));
				popup.setPopupY(rset.getInt("popup_y"));
				popup.setPopupWidth(rset.getInt("popup_width"));
				popup.setPopupHeight(rset.getInt("popup_height"));
				popup.setPopupDate(rset.getDate("popup_date"));
				popup.setPopupEndDate(rset.getDate("popup_enddate"));
				popup.setPopupImagePath(rset.getString("popup_imagepath"));
				popup.setPopupImgLink(rset.getString("popup_imglink"));
				popup.setPopupExplan(rset.getString("popup_explan"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return popup;
	}

	public int popupBack(int popupNo, Connection conn) {
		int popupBack = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query ="SELECT POPUP_NO FROM TB_POPUP WHERE POPUP_NO IN (SELECT MAX(POPUP_NO) FROM TB_POPUP WHERE POPUP_NO < ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, popupNo);
			
			rset = pstmt.executeQuery();
			 if(rset.next()) {
				 popupBack = rset.getInt(1);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return popupBack;
	}

	public int popupNext(int popupNo, Connection conn) {
		int popupNext = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="SELECT POPUP_NO FROM TB_POPUP WHERE POPUP_NO IN (SELECT MIN(POPUP_NO) FROM TB_POPUP WHERE POPUP_NO > ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, popupNo);
			
			rset = pstmt.executeQuery();
			 if(rset.next()) {
				 popupNext = rset.getInt(1);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return popupNext;
	}

	public int popupMin(Connection conn) {
		int popupMin = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query ="SELECT MIN(POPUP_NO) FROM TB_POPUP";
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			 if(rset.next()) {
				 popupMin = rset.getInt(1);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return popupMin;
	}

	public ArrayList<Popup> popupMain(Connection conn) {
		ArrayList<Popup> pArr = new ArrayList<Popup>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TB_POPUP";
		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Popup popup = new Popup();
				
				popup.setPopupNo(rset.getInt("popup_no"));
				popup.setPopupName(rset.getString("popup_name"));
				popup.setPopupLink(rset.getString("popup_link"));
				popup.setPopupX(rset.getInt("popup_x"));
				popup.setPopupY(rset.getInt("popup_Y"));
				popup.setPopupWidth(rset.getInt("popup_width"));
				popup.setPopupHeight(rset.getInt("popup_height"));
				popup.setPopupDate(rset.getDate("popup_date"));
				popup.setPopupEndDate(rset.getDate("popup_enddate"));
				popup.setPopupImagePath(rset.getString("popup_imagepath"));
				popup.setPopupImgLink(rset.getString("popup_imglink"));
				popup.setPopupExplan(rset.getString("popup_explan"));
				
				pArr.add(popup);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return pArr;
	}
}
