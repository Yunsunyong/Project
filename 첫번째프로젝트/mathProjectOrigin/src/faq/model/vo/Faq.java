package faq.model.vo;

import java.sql.Date;

public class Faq implements java.io.Serializable{
	private static final long serialVersionUID = 20190318L;
	
	private int faqNo;
	private String questionContent;
	private String answerContent;
	private Date faqDate;
	private String adminId;
	
	public Faq() {}

	public Faq(int faqNo, String questionContent, String answerContent, Date faqDate, String adminId) {
		super();
		this.faqNo = faqNo;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.faqDate = faqDate;
		this.adminId = adminId;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Date getFaqDate() {
		return faqDate;
	}

	public void setFaqDate(Date faqDate) {
		this.faqDate = faqDate;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return faqNo + ", " + questionContent + ", " + answerContent + ", " + faqDate + ", " + adminId;
	}
	
}
