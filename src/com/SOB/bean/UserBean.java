package com.SOB.bean;

public class UserBean {
	//用户登录id（昵称）
	private String userId;
	//登录密码
	private String userPwd;
	//邮箱
	private String email;
	//用户权限
	private String userRight;
	
	
	public UserBean() {
		super();
	}
	public UserBean(String userId, String userPwd, String email,
			String userRight) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.email = email;
		this.userRight = userRight;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserRight() {
		return userRight;
	}
	public void setUserRight(String userRight) {
		this.userRight = userRight;
	}
	
	
	
	
}
