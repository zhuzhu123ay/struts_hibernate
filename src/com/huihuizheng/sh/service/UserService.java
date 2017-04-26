package com.huihuizheng.sh.service;

import com.huihuizheng.sh.dao.UserDao;
import com.huihuizheng.sh.entity.User;

public class UserService {
	/**
	 *注册（不允许用户名重名） 
	 *1.先判断参数中的用户名是否已经被占用
	 *2.如果被占用，抛出异常，如果没占用，保存成功
	 * */
	UserDao userDao=new UserDao();
	public void register(User user) throws Exception {
		//根据参数中的username去数据库中查找，如果db_staff不为空，该用户名被占用
		User db_user=userDao.getUserByName(user.getUsername());
		if(user==null){
			//直接将用户名信息保存到数据库中
			userDao.insertUser(user);
		}else{
			//占用
			throw new Exception("该用户名已经被占用");
		}
	}

	/**
	 * 登录
	 * 1.通过username找用户的信息staff
	 * 2.password==staff.getPassword();
	 * 如果正确登录成功，否则失败
	 * 3.将staff返回
	 * */
	public User login(String username, String password) throws Exception {
		/*User user=null;
		User db_user=*/
		/*if(db_user!=null){
			//判断用户输入的密码与数据库中的密码是否匹配
			if(password.equals(db_user.getPassword())){
				user=db_user;
				System.out.println(user);
			}else{
				throw new Exception("密码错误！");
			}
		}else{
			throw new Exception("用户名不存在！");
		}*/
		return userDao.findUserByStaffNameAndPwd(username, password);
	}
	
}
