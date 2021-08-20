package com.xiaowen.util;

import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
import com.xiaowen.exception.SystemException;

/**
 * 封装登录异常以及参数异常的抛出异常信息的方法
 * 
 * @Description
 * @author xiaowen
 * @version
 * @date 2021年8月18日下午4:05:38
 *
 */
public class AssertUtils {

	public static void isTrue(boolean flag, String msg) {
		if (flag) {
			throw new ParamException(msg);
		}
	}
	
	public static void isTrue(boolean flag, Integer code, String msg) {
		if (flag) {
			throw new ParamException(code, msg);
		}
	}
	
	public static void isLoginFail(boolean flag, String msg) {
		if (flag) {
			throw new LoginException(msg);
		}
	}
	
	/**
	 * 确认为系统错误并抛出
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月18日下午9:51:26
	 * @param flag
	 */
	public static void isSystemError(boolean flag) {
		if (flag) {
			throw new SystemException();
		}
	}
}
