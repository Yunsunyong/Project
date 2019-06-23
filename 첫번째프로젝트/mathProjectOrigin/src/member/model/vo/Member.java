package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private Date registDate;
	private Date lastModified;
	private String memberLevel;
	private String salt;
	
	public Member() {}
	
	public Member(String userId, String userName, String phone, Date registDate, Date lastModified,
			String memberLevel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.registDate = registDate;
		this.lastModified = lastModified;
		this.memberLevel = memberLevel;
	}

	public Member(String userId, String userPwd, String userName, String phone, Date registDate, Date lastModified,
			String memberLevel, String salt) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.registDate = registDate;
		this.lastModified = lastModified;
		this.memberLevel = memberLevel;
		this.salt = salt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return userId + ", " + userPwd + ", " + userName + ", " + phone + ", " + registDate + ", " + lastModified + ", "
				+ memberLevel + ", " + salt;
	}


	
	
}
