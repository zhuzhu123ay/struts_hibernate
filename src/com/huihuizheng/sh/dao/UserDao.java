package com.huihuizheng.sh.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.huihuizheng.sh.entity.User;
import com.huihuizheng.sh.util.HibernateSessionFactory;

public class UserDao{
	
	
	public User findUserByStaffNameAndPwd(String username,
			String password) {
		User user = null;
		Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.getSessionFactory()
                    .openSession();
            tx = session.beginTransaction();
             String hql= "from User where username=? and password=?";
             Query query = session.createQuery(hql);
     		query.setParameter(0, username);
     		query.setParameter(1, password);
     		user = (User) query.uniqueResult();
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
        HibernateSessionFactory.closeSession();
		return user;
	}
	//保存User
	 public static void insertUser(User user) {
	        Transaction tx = null;
	        try {
	            Session session = HibernateSessionFactory.getSessionFactory()
	                    .openSession();
	            tx = session.beginTransaction();
	            session.save(user);

	            tx.commit();

	        } catch (HibernateException e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        HibernateSessionFactory.closeSession();
	    }//add
	    
	    
	    public static void deleteUser(String userId) {
	        Transaction tx = null;
	        try {
	            User user = getUser(userId);
	            Session session = HibernateSessionFactory.getSessionFactory()
	                    .openSession();
	            tx = session.beginTransaction();
	            session.delete(user);
	            tx.commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        HibernateSessionFactory.closeSession();
	    }//delete
	    
	    
	    public static void updateUser(User user) {
	        Transaction tx = null;
	        try {
	            Session session = HibernateSessionFactory.getSessionFactory()
	                    .openSession();
	            tx = session.beginTransaction();
	            session.update(user);
	            tx.commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        HibernateSessionFactory.closeSession();
	    }//update
	    
	    
	    public static User getUser(String userId) {
	        Transaction tx = null;
	        User user = null;
	        try {
	            Session session = HibernateSessionFactory.getSessionFactory()
	                    .openSession();
	            tx = session.beginTransaction();
	            user = (User) session.get(User.class, userId);
	            tx.commit();

	        } catch (HibernateException e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        HibernateSessionFactory.closeSession();
	        return user;
	    }//get one
	    
	    public static User getUserByName(String username) {
	    	Transaction tx = null;
			User user = null;
			try {
				Session session = HibernateSessionFactory.getSessionFactory()
						.openSession();
				tx = session.beginTransaction();
				user = (User) session.get(User.class, username);
				tx.commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}
			HibernateSessionFactory.closeSession();
	return user;
	    }//get one by name
	    
	    public static List<User> getUsers() {
	        Transaction tx = null;
	        List<User> list = null;
	        try {
	            Session session = HibernateSessionFactory.getSessionFactory()
	                    .openSession();
	            tx = session.beginTransaction();
	            Query query = session.createQuery("from User order by id desc");
	            list = query.list();
	            tx.commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        HibernateSessionFactory.closeSession();
	        return list;
	    }// get all
	    
	    public static List<User> checkUser(String userId,String userPwd) {
	        Transaction tx = null;
	        List<User> list = null;
	        try {
	            Session session = HibernateSessionFactory.getSessionFactory()
	                    .openSession();
	            tx = session.beginTransaction();
	            Query query = session.createQuery("from User as u where u.id=:id and u.password=:password");
	            query.setString("id", userId);    
	            query.setString("password", userPwd);
	            list = query.list();                            
	            tx.commit();}
	        catch (Exception e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        HibernateSessionFactory.closeSession();
	        return list;
	    }
	    
	    
	   /* //测试DAO
	    public static void main(String[] args){
	        List<User> list = UserDAO.getUsers();
	        
	        System.out.println("½øÈë²âÊÔº¯Êý");
	        
	        for(int i=0;i<list.size();i++){
	            User user= new User();
	            user = list.get(i);
	            System.out.println(user.getUserId());
	        }
	    }*/

}
