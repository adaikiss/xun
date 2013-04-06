/**
 * 
 */
package org.adaikiss.xun.mybatis.keygenerator;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;

/**
 * MyBatis not support custom KeyGenerator in current version.
 * @author hlw
 * 
 */
public class UUIDKeyGenerator implements KeyGenerator {

	@Override
	public void processBefore(Executor executor, MappedStatement ms,
			Statement stmt, Object parameter) {
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(parameter);
		generateKey(ms, stmt, parameters);
	}

	@Override
	public void processAfter(Executor executor, MappedStatement ms,
			Statement stmt, Object parameter) {
		throw new RuntimeException("post process key generating is not supported!");
	}

	public void generateKey(MappedStatement ms, Statement stmt,
			List<Object> parameters) {
		try {
			final Configuration configuration = ms.getConfiguration();
			final String[] keyProperties = ms.getKeyProperties();
			if (keyProperties != null) {
				for (Object parameter : parameters) {
					final MetaObject metaParam = configuration
							.newMetaObject(parameter);
					populateKeys(metaParam, keyProperties);
				}
			}
		} catch (Exception e) {
			throw new ExecutorException(
					"Error getting generated key or setting result to parameter object. Cause: "
							+ e, e);
		}
	}

	private void populateKeys(MetaObject metaParam, String[] keyProperties)
			throws SQLException {
		for (int i = 0; i < keyProperties.length; i++) {
			Object value = UUID.randomUUID();
			metaParam.setValue(keyProperties[i], value);
		}
	}
}
