/**
 * 
 */
package org.adaikiss.xun.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hlw
 *
 */
@Repository
public class BaseDao<T, ID extends Serializable> {
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	@Autowired
	SessionFactory sessionFactory;
	private Class<T> persistanceClass;
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public BaseDao(){
		this.persistanceClass = getPersistanceClass();
	}

	protected T get(ID id){
		@SuppressWarnings("unchecked")
		T t = (T)getSession().get(persistanceClass, id);
		return t;
	}

	protected ID save(T t){
		@SuppressWarnings("unchecked")
		ID id = (ID)getSession().save(t);
		return id;
	}

	private Class<T> getPersistanceClass(){
		Type genType = getClass().getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn("Superclass not ParameterizedType");
			return null;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (!(params[0] instanceof Class)) {
			logger.warn("Not set the actual class on superclass generic parameter");
			return null;
		}
		@SuppressWarnings("unchecked")
		Class<T> clz = (Class<T>) params[0];
		return clz;
	}
	
}
