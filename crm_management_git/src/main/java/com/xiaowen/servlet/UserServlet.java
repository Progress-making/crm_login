package com.xiaowen.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaowen.exception.LoginException;
import com.xiaowen.pojo.User;
import com.xiaowen.service.UserService;
import com.xiaowen.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符编码集为UTF-8，保证与环境的字符编码一致，避免乱码。
		request.setCharacterEncoding("utf-8");
		// 获取servlet路径  为uri/项目名/*中的/*部分。这里为/*.do
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		System.out.println(request.getServletContext().getContextPath());
		// 去掉前缀/与后缀.do获取 方法名
		String methodStr = servletPath.substring(servletPath.lastIndexOf("/")+1, servletPath.length() - 3);
		//通过反射定位对应方法
		try {
			Method method = getClass().getDeclaredMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * login(),通过反射机制调用本方法用于登录
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月14日上午11:57:52
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userName = req.getParameter("userName");
		String userPwd = req.getParameter("userPwd");
		
		User user = new User();
		try {
			user = userService.login(userName, userPwd);
			System.out.println(user);
			// 跳转至主页面
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
		} catch (LoginException e) {
//			e.getMsg();
//			resp.sendRedirect(req.getServletContext().getContextPath() + "/error.jsp");
			req.setAttribute("msg", e.getMsg());
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		}
		
	}

}
