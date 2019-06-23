package popup.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Popup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int popupNo;
	private String popupName;
	private String popupLink;
	private int popupX;
	private int popupY;
	private int popupWidth;
	private int popupHeight;
	private Date popupDate;
	private Date popupEndDate;
	private String popupImagePath;
	private String popupImgLink;
	private String popupExplan;
	private String adminId;
	
	public Popup() {}

	public Popup(int popupNo, String popupName, String popupLink, int popupX, int popupY, int popupWidth,
			int popupHeight, Date popupDate, Date popupEndDate, String popupImagePath, String popupImgLink, String popupExplan,
			String adminId) {
		super();
		this.popupNo = popupNo;
		this.popupName = popupName;
		this.popupLink = popupLink;
		this.popupX = popupX;
		this.popupY = popupY;
		this.popupWidth = popupWidth;
		this.popupHeight = popupHeight;
		this.popupDate = popupDate;
		this.popupEndDate = popupEndDate;
		this.popupImagePath = popupImagePath;
		this.popupImgLink = popupImgLink;
		this.popupExplan = popupExplan;
		this.adminId = adminId;
	}
	
	public String getAdminId() {
		return adminId;
	}
	
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public int getPopupNo() {
		return popupNo;
	}

	public void setPopupNo(int popupNo) {
		this.popupNo = popupNo;
	}

	public String getPopupName() {
		return popupName;
	}

	public void setPopupName(String popupName) {
		this.popupName = popupName;
	}

	public String getPopupLink() {
		return popupLink;
	}

	public void setPopupLink(String popupLink) {
		this.popupLink = popupLink;
	}

	public int getPopupX() {
		return popupX;
	}

	public void setPopupX(int popupX) {
		this.popupX = popupX;
	}

	public int getPopupY() {
		return popupY;
	}

	public void setPopupY(int popupY) {
		this.popupY = popupY;
	}

	public int getPopupWidth() {
		return popupWidth;
	}

	public void setPopupWidth(int popupWidth) {
		this.popupWidth = popupWidth;
	}

	public int getPopupHeight() {
		return popupHeight;
	}

	public void setPopupHeight(int popupHeight) {
		this.popupHeight = popupHeight;
	}

	public Date getPopupDate() {
		return popupDate;
	}

	public void setPopupDate(Date popupDate) {
		this.popupDate = popupDate;
	}

	public Date getPopupEndDate() {
		return popupEndDate;
	}

	public void setPopupEndDate(Date popupEndDate) {
		this.popupEndDate = popupEndDate;
	}

	public String getPopupImagePath() {
		return popupImagePath;
	}

	public void setPopupImagePath(String popupImagePath) {
		this.popupImagePath = popupImagePath;
	}
	
	public String getPopupImgLink() {
		return popupImgLink;
	}
	
	public void setPopupImgLink(String popupImgLink) {
		this.popupImgLink = popupImgLink;
	}

	public String getPopupExplan() {
		return popupExplan;
	}

	public void setPopupExplan(String popupExplan) {
		this.popupExplan = popupExplan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return this.popupNo + ", " + this.popupName + ", " 
				+ this.popupLink + ", " + this.popupX + ", " 
				+ this.popupY + ", " + this.popupWidth + ", " 
				+ this.popupHeight + ", " + this.popupDate + ", " 
				+ this.popupEndDate + ", " + this.popupImagePath + ", " 
				+ popupImgLink + ", "
				+ this.popupExplan + ", " + this.adminId; 
	}
}
