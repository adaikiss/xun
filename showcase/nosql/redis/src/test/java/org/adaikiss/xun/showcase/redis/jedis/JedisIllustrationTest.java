/**
 * 
 */
package org.adaikiss.xun.showcase.redis.jedis;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adaikiss.xun.showcase.redis.jedis.dto.JavaDTO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author HuLingwei
 * 
 */
public class JedisIllustrationTest extends JedisTestCase {
	/**
	 * Set get/set
	 */
	@Test
	public void setIllustration() {
		String key = "soo";
		jedis.sadd(key, "a");
		jedis.sadd(key, "b");
		jedis.sadd(key, "c");
		Set<String> actual = jedis.smembers(key);
		Assert.assertThat(actual.size(), is(3));
		Assert.assertThat(actual, hasItems("a", "b", "c"));
	}

	/**
	 * List get/set
	 */
	@Test
	public void listIllustration() {
		String key = "loo";
		jedis.lpush(key, "a");
		jedis.lpush(key, "b");
		jedis.lpush(key, "c");
		List<String> actual = jedis.lrange(key, 0, -1);
		Assert.assertThat(actual.size(), is(3));
		Assert.assertThat(actual, hasItems("a", "b", "c"));
	}

	/**
	 * Hash get/set
	 */
	@Test
	public void hashIllustration() {
		String key = "hoo";
		jedis.hset(key, "a", "a");
		jedis.hset(key, "b", "b");
		jedis.hset(key, "c", "c");
		Map<String, String> actual = jedis.hgetAll(key);
		Assert.assertThat(actual.size(), is(3));
		Assert.assertThat(actual.get("a"), equalTo("a"));
	}

	/**
	 * String get/set
	 */
	@Test
	public void stringValueIllustration() {
		String key = "foo";
		String value = "hello world!";
		jedis.set(key, value);
		String actual = jedis.get(key);
		Assert.assertEquals(value, actual);
	}

	@Test
	public void javaSerializationIllustration() throws Exception {
		byte[] key = "java".getBytes();
		JavaDTO o = new JavaDTO(1l, "hello", new Date());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.flush();
		byte[] data = baos.toByteArray();
		jedis.set(key, data);
		data = jedis.get(key);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
				data));
		Object actual = ois.readObject();
		Assert.assertEquals(o, actual);
	}

	@Test
	@Ignore
	public void jsonSerializationIllustration() {
		// json serialzation, see showcase/serialization
	}

	@Test
	@Ignore
	public void msgpackSerializationIllustration() {
		// msgpack serialzation, see showcase/serialization
	}

	@Test
	@Ignore
	public void protobufSerializationIllustration() {
		// protobuf serialzation, see showcase/serialzation
	}
}
