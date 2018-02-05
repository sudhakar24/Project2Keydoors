package com.backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Dao.UserDao;
import com.model.User;

public class usertest {
static UserDao userDao;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext configApplnContext=new AnnotationConfigApplicationContext();
		configApplnContext.scan("com");
		configApplnContext.refresh();
		
		//SessionFactory sessionFactory=(SessionFactory)configApplnContext.getBean("DBConfig.class");
		
		userDao=(UserDao)configApplnContext.getBean("userDao");
	}
	/*@Test
	public void adduserTest()
	{
		User user=new User();
		user.setEmail("sudha@gmail.com");
		user.setFirstname("first");
		user.setLastname("s");
		user.setPassword("pass");
	
	}*/
}
