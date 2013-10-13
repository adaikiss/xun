/**
 * 
 */
package org.adaikiss.xun.showcase.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author HuLingwei
 *
 */
public class JedisShowcase {
	public static void main(String[] args){
		Jedis jedis = new Jedis("192.168.1.104", 6379);
		String foo = jedis.get("foo");
		System.out.println(foo);
	}
}
