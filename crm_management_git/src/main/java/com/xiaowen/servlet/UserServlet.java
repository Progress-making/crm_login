package com.xiaowen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaowen.base.ResultInfo;
import com.xiaowen.exception.LoginException;
import com.xiaowen.exception.ParamException;
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
	 * queryByName() 异步请求查询指定用户名是否存在
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月19日下午2:53:22
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		System.out.println("*****" + userName + "*****");
		User user = new User();
		ResultInfo result = new ResultInfo();
		try {
			user = userService.getUserByUsername(userName);
			System.out.println(user);
			result.setCode(200);
			result.setMsg("操作成功！");
		} catch (ParamException e) {
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
			result.setResult(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(result);
		System.out.println(resultJson);
		PrintWriter writer = resp.getWriter();
		writer.write(resultJson);
		writer.flush();
	}
	
	/**
	 * queryByCookie() 从cookie中根据cookieName查询当前登录状态
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月24日下午3:38:40
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryByCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		ResultInfo result = new ResultInfo();
		try {
			user = userService.getUserByIdFromCookie(req);
			System.out.println(user);
			result.setCode(200);
			result.setMsg("操作成功！");
			result.setResult(user);
		} catch (LoginException e) {
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
			result.setResult(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(result);
		System.out.println(resultJson);
		PrintWriter writer = resp.getWriter();
		writer.write(resultJson);
		writer.flush();
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
			user = userService.login(userName, userPwd, resp);
			System.out.println(user);
			// 跳转至主页面
			req.getRequestDispatcher("/main.do").forward(req, resp);
		} catch (ParamException e){
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		} catch (LoginException e) {
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		} catch (Exception e) {
			// 其他运行时异常 均视为系统内部错误！
			e.printStackTrace();
			req.setAttribute("code", 500);
			req.setAttribute("msg", "系统内部错误");
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		}
		
	}

	/**
	 * register(),通过反射机制调用本方法用于注册
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月18日下午5:04:51
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userName = req.getParameter("userName");
		String userPwd = req.getParameter("userPwd");
		String confirmPwd = req.getParameter("confirmPwd");
		String trueName = req.getParameter("trueName");
		
		User user = new User();
		user.setUserName(userName);
		user.setUserPwd(userPwd);
		user.setTrueName(trueName);
		
		try {
			userService.register(user, confirmPwd);
			// 跳转至登录页面(重定向)
			resp.sendRedirect(req.getServletContext().getContextPath() + "/index.jsp");
		} catch (ParamException e) {
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("code", "500");
			req.setAttribute("msg", "系统内部错误");
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 跳转至更新密码页面
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月23日下午3:20:17
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			userService.getUserByIdFromCookie(req);
			req.getRequestDispatcher("/WEB-INF/views/updatePwd.jsp").forward(req, resp);
		} catch (LoginException e) {
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("code", 500);
			req.setAttribute("msg", "系统错误");
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 更新密码
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月23日下午3:41:56
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void executeUpdatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*String userIdStr = req.getParameter("userId");*/
		String oldPwd = req.getParameter("oldPwd");
		String newPwd = req.getParameter("newPwd");
		String confirmPwd = req.getParameter("confirmPwd");
		ResultInfo result = new ResultInfo();
		try {
//			User user = userService.getUserByIdFromCookie(req);
			userService.updatUserPwd(req, oldPwd, newPwd, confirmPwd);
			result.setCode(200);
			result.setMsg("操作成功！");
		} catch (ParamException e) {
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
		} catch (LoginException e) {
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json; charset=utf-8");
		
		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(result);
		System.out.println(resultJson);
		PrintWriter writer = resp.getWriter();
		writer.write(resultJson);
		writer.flush();
	}
	
	/**
	 * 跳转至编辑资料界面
	 * 功能：将用户信息转发至编辑资料界面
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午3:33:29
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User user = userService.getUserByIdFromCookie(req);
			System.out.println(user);
			req.setAttribute("userInfo", user);
		} catch (LoginException e) {
			e.printStackTrace();
			req.setAttribute("code", e.getCode());
			req.setAttribute("msg", e.getMsg());
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("code", 500);
			req.setAttribute("msg", "系统内部错误");
			req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
	}
	
	/**
	 * edit 实施编辑功能
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午9:58:28
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String trueName = req.getParameter("trueName");
		System.out.println(userName + "-----" + trueName);
		ResultInfo result = new ResultInfo(); 
		try {
			userService.updateUserInfo(req, userName, trueName);
			result.setCode(200);
			result.setMsg("操作成功！");
		} catch (LoginException e) {
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
		} catch(ParamException e) {
			e.printStackTrace();
			result.setCode(e.getCode());
			result.setMsg(e.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(result);
		System.out.println(resultJson);
		PrintWriter writer = resp.getWriter();
		writer.write(resultJson);
		writer.flush();
	}
	
	/**
	 * main页面的跳转
	 * 
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月26日下午9:49:02
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void main(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跳转至main页面
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
	}
	
	/**
	 * toErrorPage 转发请求 跳转至WEB-INF、views/error.jsp
	 * @Description
	 * @author xiaowen
	 * @date 2021年8月23日下午1:38:24
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", req.getParameter("msg"));
		req.setAttribute("code", req.getParameter("code"));
		req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
	}
}
