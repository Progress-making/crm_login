package com.xiaowen.exception;

/**
 * 系统内部运行时异常
 * 界面统一友好提示为：操作失败：系统内部错误
 * 
 * @Description
 * @author xiaowen
 * @version
 * @date 2021年8月18日下午8:41:35
 *
 */
public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2441464483978937473L;
	
	private static int code = 500;
	private static String msg = "操作失败：系统内部错误";
	
	public SystemException() {
		super();
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "SystemException [code=" + code + ", msg=" + msg + "]";
	}

}
