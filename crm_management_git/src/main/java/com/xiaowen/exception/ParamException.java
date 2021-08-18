package com.xiaowen.exception;

public class ParamException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -538813616878462534L;
	
	private int code = 300;
	private String msg = "参数异常";
	
	public ParamException() {
	}
	
	public ParamException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ParamException(int code) {
		super("参数异常");
		this.code = code;
	}
	
	public ParamException(int code, String msg) {
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
		return "ParamException [code=" + code + ", msg=" + msg + "]";
	}
	
	

}
