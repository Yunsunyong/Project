package admin.model.vo;

import java.io.Serializable;

public class Semester implements Serializable{

	private static final long serialVersionUID = 12321L;
	private int semesterNo;
	private String userId;
	private String semesterName;
	public Semester() {
		super();
	}
	public Semester(int semesterNo, String semesterName) {
		super();
		this.semesterNo = semesterNo;
		this.semesterName = semesterName;
	}
	public Semester(String userId, String semesterName) {
		super();
		this.userId = userId;
		this.semesterName = semesterName;
	}
	public Semester(int semesterNo, String userId, String semesterName) {
		super();
		this.semesterNo = semesterNo;
		this.userId = userId;
		this.semesterName = semesterName;
	}
	public int getSemesterNo() {
		return semesterNo;
	}
	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return semesterNo + ", " + userId + ", " + semesterName;
	}
}
