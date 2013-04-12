/**
 * 
 */
package org.adaikiss.xun.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.adaikiss.xun.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author hlw
 * 
 */
@Repository
public class UserRepository {
	private static AtomicLong sequence = new AtomicLong(0);

	private static Map<Long, User> users = new HashMap<Long, User>();

	static {
		User user = new User();
		user.setId(sequence.addAndGet(1l));
		user.setDisplayName("从来都没有什么救世主");
		user.setLoginName("adaikiss");
		user.setEmail("adaikiss@163.com");
		user.setNickName("敏");
		users.put(user.getId(), user);
		user = new User();
		user.setId(sequence.addAndGet(1l));
		user.setDisplayName("你表妹夫");
		user.setLoginName("jetty");
		user.setEmail("jetty@gmail.com");
		user.setNickName("你表妹夫");
		users.put(user.getId(), user);
		user = new User();
		user.setId(sequence.addAndGet(1l));
		user.setDisplayName("山岭巨人");
		user.setLoginName("tiny");
		user.setEmail("tiny@hotmail.com");
		user.setNickName("小小");
		users.put(user.getId(), user);
	}

	public List<User> list(){
		return new ArrayList<User>(users.values());
	}

	public User get(Long id) {
		return users.get(id);
	}

	public void add(User user) {
		user.setId(sequence.addAndGet(1l));
		users.put(user.getId(), user);
	}

	public void update(User user) {
		if(user.getPassword() == null){
			user.setPassword(users.get(user.getId()).getPassword());
		}
		users.put(user.getId(), user);
	}

	public void delete(Long id) {
		users.remove(id);
	}
}
