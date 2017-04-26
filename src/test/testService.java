package test;

import org.junit.Test;

import com.huihuizheng.sh.service.UserService;

public class testService {
	@Test
	public void UserServiceTest() throws Exception{
		UserService us=new UserService();
		us.login("花花花", "花花");
	}
}
