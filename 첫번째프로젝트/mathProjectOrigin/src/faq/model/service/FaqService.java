package faq.model.service;

import faq.model.dao.FaqDao;
import faq.model.vo.Faq;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
public class FaqService {
	private FaqDao fdao = new FaqDao();
	public FaqService() {}

	public ArrayList<Faq> searchAllList(String searchTitle, String fOption, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Faq> fList = fdao.searchAllList(searchTitle, fOption, currentPage, limit, conn);
		close(conn);
		return fList;
	}
	
	public Faq faqDetail(int faqNo) {
		Connection conn = getConnection();
		Faq faq = fdao.faqDetail(faqNo, conn);
		close(conn);
		return faq;
	}
	
	public int insertFaqWrite(Faq faq) {
		Connection conn = getConnection();
		int result = fdao.insertFaqWrite(faq, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int faqUpdate(Faq faq) {
		Connection conn = getConnection();
		int result = fdao.faqUpdate(faq, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int faqDelete(int faqNo) {
		Connection conn = getConnection();
		int result = fdao.faqDelete(faqNo, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int allSearchListCount(String searchTitle, String fOption) {
		Connection conn = getConnection();
		int result = fdao.allSearchListCount(searchTitle, fOption, conn);
		close(conn);
		return result;
	}

	public int faqBack(int faqNo) {
		Connection conn = getConnection();
		int faqBack = fdao.faqBack(faqNo, conn);
		close(conn);
		return faqBack;
	}

	public int faqNext(int faqNo) {
		Connection conn = getConnection();
		int faqNext = fdao.faqNext(faqNo, conn);
		close(conn);
		return faqNext;
	}
	
	public int faqMin() {
		Connection conn = getConnection();
		int faqMin = fdao.faqMin(conn);
		close(conn);
		return faqMin;
	}

	public ArrayList<Faq> faqUserList() {
		Connection conn = getConnection();
		ArrayList<Faq> fList = fdao.faqUserList(conn);
		close(conn);
		return fList;
	}
}
