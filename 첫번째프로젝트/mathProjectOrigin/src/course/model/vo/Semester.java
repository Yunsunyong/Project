package course.model.vo;

public class Semester implements java.io.Serializable{
	private static final long serialVersionUID = 20190399L;
	
	private int semesterNo;
	private String semesterName;
	
	public Semester() {}

	public Semester(int semesterNo, String semesterName) {
		super();
		this.semesterNo = semesterNo;
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

	@Override
	public String toString() {
		return semesterNo + ", " + semesterName;
	}
	
}
