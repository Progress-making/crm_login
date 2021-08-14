package com.xiaowen.pojo;

public class User {

	private Integer id;
	private String userName;
	private String userPwd;
	private String trueName;
	
	public User() {
		super();
	}
	public User(Integer id, String userName, String userPwd, String trueName) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPwd = userPwd;
		this.trueName = trueName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + ", trueName=" + trueName + "]";
	}
	
	
}
