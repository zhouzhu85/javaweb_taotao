package com.taotao.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJdis {
	@Test
	public void test1() throws Exception{
		Jedis jedis=new Jedis("192.168.25.175",6379);
		jedis.set("test1","test1");
		String result=jedis.get("test1");
		System.out.println(result);
		jedis.close();
	}
	
	@Test
	public void test2() throws Exception{
		JedisPool jPool=new JedisPool("192.168.25.175",6379);
		Jedis jedis=jPool.getResource();
		jedis.set("test2","test2");
		String result=jedis.get("test2");
		System.out.println(result);
		jedis.close();
		jPool.close();
	}
	
	@Test
	public void test3(){
		Set<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.175", 7001));
		nodes.add(new HostAndPort("192.168.25.175", 7002));
		nodes.add(new HostAndPort("192.168.25.175", 7003));
		nodes.add(new HostAndPort("192.168.25.175", 7004));
		nodes.add(new HostAndPort("192.168.25.175", 7005));
		nodes.add(new HostAndPort("192.168.25.175", 7006));
		JedisCluster jedisCluster=new JedisCluster(nodes);
		//jedisCluster.set("cluset-test","hello jedis cluset");
		String result=jedisCluster.get("cluset-test");
		System.out.println(result);
		jedisCluster.close();
	}
}
