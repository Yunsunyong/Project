package course.model.vo;

import java.sql.Date;

public class Course implements java.io.Serializable{
	private static final long serialVersionUID = 20190328L;
	
	private int courseNo;
	private String courseTitle;
	private String courseContent;
	private Date courseDate;
	private int bookNo;
	private int semesterNo;
	
	public Course() {}

	public Course(int courseNo, String courseTitle, String courseContent, Date courseDate, int bookNo, int semesterNo) {
		super();
		this.courseNo = courseNo;
		this.courseTitle = courseTitle;
		this.courseContent = courseContent;
		this.courseDate = courseDate;
		this.bookNo = bookNo;
		this.semesterNo = semesterNo;
	}

	public int getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public Date getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getSemesterNo() {
		return semesterNo;
	}

	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return courseNo + ", " + courseTitle + ", " + courseContent + ", " + courseDate + ", " + bookNo + ", "
				+ semesterNo;
	}

}
