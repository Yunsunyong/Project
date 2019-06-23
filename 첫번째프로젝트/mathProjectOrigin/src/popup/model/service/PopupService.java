package popup.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.vo.Notice;
import popup.model.dao.PopupDao;
import popup.model.vo.Popup;

public class PopupService {
	private PopupDao pdao = new PopupDao();
	
	public PopupService() {}
	
	public ArrayList<Popup> searchAllList(String searchTitle, int currentPage, int limit){
		Connection conn = getConnection();
		ArrayList<Popup> plist = pdao.listPopup(searchTitle, currentPage, limit, conn);
		close(conn);
		return plist;
	}
	
	public int updatePopup(Popup popup) {
		Connection conn = getConnection();
		int result = pdao.updatePopup(conn, popup);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deletePopup(int popupNo) {
		Connection conn = getConnection();
		int result = pdao.deletePopup(conn, popupNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int insertPopup(Popup popup) {
		Connection conn = getConnection();
		int result = pdao.insertPopup(conn, popup);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Popup selectPopup(int popupNo) {
		Connection conn = getConnection();
		Popup popup = pdao.selectPopup(conn, popupNo);
		close(conn);
		return popup;
	}

	public int AllSearchListCount(String searchTitle) {
		Connection conn = getConnection();
		int result = pdao.AllSearchListCount(searchTitle,conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Popup selectPDetail(int popupNo) {
		Connection conn = getConnection();
		Popup popup = pdao.selectPDetail(popupNo, conn);
		close(conn);
		return popup;
	}

	public int popupBack(int popupNo) {
		Connection conn = getConnection();
		int popupMax = pdao.popupBack(popupNo,conn);
		close(conn);
		return popupMax;
	}

	public int popupNext(int popupNo) {
		Connection conn = getConnection();
		int popupMin = pdao.popupNext(popupNo,conn);
		close(conn);
		return popupMin;
	}

	public int popupMin() {
		Connection conn = getConnection();
		int popupMin = pdao.popupMin(conn);
		close(conn);
		return popupMin;
	}

	public ArrayList<Popup> popupMain() {
		Connection conn = getConnection();
		ArrayList<Popup> mplist = pdao.popupMain(conn);
		close(conn);
		return mplist;
	}
}
