package com.xiaowen.exception;

public class LoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8167384919494484570L;

	private int code = 300;
	private String msg = "登录失败";
	
	public LoginException() {
	}
	
	public LoginException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public LoginException(int code) {
		super("参数异常");
		this.code = code;
	}
	
	public LoginException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "LoginException [code=" + code + ", msg=" + msg + "]";
	}
	
}
