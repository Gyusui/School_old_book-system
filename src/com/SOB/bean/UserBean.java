package com.SOB.bean;

public class UserBean {
	//�û���¼id���ǳƣ�
	private String userId;
	//��¼����
	private String userPwd;
	//����
	private String email;
	//�û�Ȩ��
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
