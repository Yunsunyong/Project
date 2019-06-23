package course.model.vo;

public class Book implements java.io.Serializable{
	private static final long serialVersionUID = 20190300L;
	
	private int bookNo;
	private int semesterNo;
	private String bookName;
	
	public Book() {}

	public Book(int bookNo, int semesterNo, String bookName) {
		super();
		this.bookNo = bookNo;
		this.semesterNo = semesterNo;
		this.bookName = bookName;
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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return bookNo + ", " + semesterNo + ", " + bookName;
	}
	
}
