package test;

import org.junit.Test;

import com.huihuizheng.sh.dao.UserDao;
import com.huihuizheng.sh.entity.User;

public class testDao {
	@Test
	public void testUserDao(){
		UserDao ud=new UserDao();
		/*User user=new User(null,"张si","张4");
		ud.updateUser(user);*/
		User user=ud.findUserByStaffNameAndPwd("花花花", "花花花");
		System.out.println(user);
	}
}
