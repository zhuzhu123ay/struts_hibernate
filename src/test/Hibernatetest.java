package test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huihuizheng.sh.entity.User;

public class Hibernatetest {
	public SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Before
	public void init(){
		Configuration configuration=new Configuration().configure();
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder()
		.applySettings(configuration.getProperties())
		.buildServiceRegistry();
		
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		session=sessionFactory.openSession();
		//开启事务
		transaction=session.beginTransaction();
		System.out.println("init");
	}
	@After
	public void destory(){
		System.out.println("destory");
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testSave(){
		User user=new User();
		user.setUsername("花花");
		user.setPassword("花花");
		
		System.out.println(user);
		session.save(user);
	}
	@Test
	public void testPersist(){
		User user=new User();
		user.setUsername("上方");
		user.setPassword("上方");
		
		System.out.println(user);
		session.persist(user);
	}
	@Test
	public void testGet(){
		User user=(User) session.get(User.class, 2);
		System.out.println(user);
	}
	@Test
	public void testLoad(){
		User user=(User) session.load(User.class, 2);
		System.out.println(user.getClass().getName());
		System.out.println(user);
	}
}
