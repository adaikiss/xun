package org.adaikiss.xun.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.adaikiss.xun.core.util.Constant;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 操作日志类
 * 
 * @author hlw
 * 
 */
@Entity
@Table(name = Constant.TABLE_PREFIX + "log")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_EMPTY)
public class Log extends IdEntity {
	public static final String CREATE = "创建";
	public static final String READ = "浏览";
	public static final String UPDATE = "修改";
	public static final String DELETE = "删除";

	/**
	 * 操作者
	 */
	@JsonProperty
	private String user;
	/**
	 * 操作时间
	 */
	@JsonProperty(value = "act_time")
	private Date time;
	/**
	 * 操作类型
	 */
	@JsonProperty
	private String action;
	/**
	 * 操作对象
	 */
	@JsonProperty
	private String target;

	public Log(){
		this.time = new Date();
	}

	public Log(String user, String action){
		this();
		this.user = user;
		this.action = action;
	}

	public Log(String user, String action, String target){
		this(user, action);
		this.target = target;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
