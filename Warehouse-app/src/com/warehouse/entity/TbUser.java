package com.warehouse.entity;

public class TbUser {

	private int id;

	private String UserId;
	private String CuserName;
	private String LoginName;
	private String Password;
	private String DeptId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getCuserName() {
		return CuserName;
	}

	public void setCuserName(String cuserName) {
		CuserName = cuserName;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getDeptId() {
		return DeptId;
	}

	public void setDeptId(String deptId) {
		DeptId = deptId;
	}

}
