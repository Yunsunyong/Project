package question.model.vo;

public class Question implements java.io.Serializable{

	private static final long serialVersionUID = 13L;

	private String semester;
	private String book;
	private String chapter;
	private String question;
	
	public Question() {}

	public Question(String semester, String book, String chapter, String question) {
		super();
		this.semester = semester;
		this.book = book;
		this.chapter = chapter;
		this.question = question;
	}

	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return this.semester + ", " + this.book + ", " + this.chapter + ", " + this.question;
	}
	
}
