package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{
	private static final long serialVersionUID = 20190309L;
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private int noticeCount;
	private String originalFileName;
	private String renameFileName;
	private String writerName;
	
	public Notice() {}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeDate, int noticeCount,
			String originalFileName, String renameFileName, String writerName) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.noticeCount = noticeCount;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.writerName = writerName;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return noticeNo + ", " + noticeTitle + ", " + noticeContent + ", " + noticeDate + ", " + noticeCount + ", "
				+ originalFileName + ", " + renameFileName + ", " + writerName;
	}
		
}
