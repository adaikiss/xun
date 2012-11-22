/**
 * 
 */
package org.adaikiss.xun.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.adaikiss.xun.OperationLog;
import org.adaikiss.xun.enumeration.Sex;
import org.adaikiss.xun.model.User;
import org.adaikiss.xun.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author hlw
 *
 */
@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/ajax")
@Results({@Result(name = UserAction.JSON, type = "json", params = {"excludeNullProperties", "true"})})
public class UserAction extends ActionSupport{
	public static final String JSON = "json";
	private List<User> users;
	private Boolean success;
	@Autowired
	UserService userService;

	public String login(){
		success = Boolean.TRUE;
		return JSON;
	}

	@OperationLog(operation = "用户列表", arg0 = "success")
	public String list(){
		users = new ArrayList<User>();
		try {
			users.add(new User(1l, "Bird Dragon", 21, Sex.female, DateUtils.parseDateStrictly("1991-11-12", "yyyy-mm-dd")));
			users.add(new User(2l, "Great Zhou", 28, Sex.male, DateUtils.parseDateStrictly("1984-10-10", "yyyy-mm-dd")));
			users.add(new User(3l, "Brother B", 28, Sex.male, DateUtils.parseDateStrictly("1984-11-11", "yyyy-mm-dd")));
			users.add(new User(4l, "Messi", 26, Sex.male, DateUtils.parseDateStrictly("1986-11-12", "yyyy-mm-dd")));
			users.add(new User(5l, "Xiao Mogu", 24, Sex.male, DateUtils.parseDateStrictly("1987-11-12", "yyyy-mm-dd")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return JSON;
	}

	public String testList(){
		return SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
