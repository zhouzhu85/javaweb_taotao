package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJedisSpring {

	@Test
	public void test1(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient=context.getBean(JedisClient.class);
		jedisClient.set("jedisClient", "mytest");
		String v=jedisClient.get("jedisClient");
		System.out.println(v);
	}
}
