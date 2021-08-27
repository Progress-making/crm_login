package com.xiaowen.base;

public class ResultInfo {

	private int code = 500;
	private String msg = "操作失败！系统内部错误！";
	private Object result;
	
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
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "ResultInfo [code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}
	
	
}
