package admin.model.vo;

import java.io.Serializable;

public class Admin implements Serializable{

	private static final long serialVersionUID = 2L;
	private String adminId;
	private String adminPwd;
	private String adminName;
	
	public Admin() {}

	public Admin(String adminId, String adminPwd, String adminName) {
		super();
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return adminId + ", " + adminPwd + ", " + adminName;
	}
	
	
}
