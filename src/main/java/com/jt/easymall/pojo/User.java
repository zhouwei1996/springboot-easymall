package com.jt.easymall.pojo;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;
	private String userName;
	private String userPassword;
	private String userNickname;
	private String userEmail;
	private Integer userType;//业务逻辑上并没有使用type
	//一旦添加了数据,有可能在新增时,出现null指针异常,1 表示初级用户,2 铜牌用户,3 银牌用户,4 金牌用户,5 钻石用户,6 白金用户7
	//默认新增时,全是0

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	

}
