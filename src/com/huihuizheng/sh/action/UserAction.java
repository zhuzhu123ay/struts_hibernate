package com.huihuizheng.sh.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.huihuizheng.sh.entity.User;
import com.huihuizheng.sh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware{
	UserService us=new UserService();
	private String username;
	private String password;
	private HttpServletResponse response;
	private ServletContext application;
	private HttpServletRequest request;
	User user =null;
	
	public String login() throws Exception{
		user=us.login(username, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			System.out.println("-----------------user----------"
					+ user.toString());
			return "success";
		} else {
			request.getSession().setAttribute("loginError", "用户名或密码不正确！");// 向前台传值
			return "login";
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
