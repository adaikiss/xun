/**
 * 
 */
package org.adaikiss.xun.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * @author hlw
 * 
 */
public class DbUnitUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(DbUnitUtil.class);

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private IDataSet dataSet;
	private String[] xmlFilePaths;
	private String schema;
	private DataSource dataSource;
	private Class<?> testClass;

	public DbUnitUtil(Class<?> testClass) {
		this.testClass = testClass;
	}

	public DbUnitUtil(String...xmlFilePaths){
		this.xmlFilePaths = xmlFilePaths;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private TestData getAnnotationHeriency(Class<?> clazz) {
		if (null == clazz) {
			return null;
		}
		TestData anno = clazz.getAnnotation(TestData.class);
		if (null == anno) {
			return getAnnotationHeriency(clazz.getSuperclass());
		}
		return anno;
	}

	/**
	 * 获取Xml格式测试数据路径
	 * 
	 * @return
	 */
	private String[] getTestDataXmlPaths() {
		if (xmlFilePaths != null) {
			return xmlFilePaths;
		}
		TestData annotation = getAnnotationHeriency(testClass);
		if (null != annotation) {
			schema = annotation.schema();
			if (StringUtils.isNotBlank(schema)) {
				schema = schema.trim().toUpperCase();
			}
			String value = annotation.value();
			String[] paths = annotation.paths();
			String basePath = annotation.basePath();
			if (StringUtils.isNotBlank(value)) {
				xmlFilePaths = new String[] { value };
				return xmlFilePaths;
			}
			if (paths != null && paths.length != 0) {
				xmlFilePaths = paths;
				return xmlFilePaths;
			}
			xmlFilePaths = new String[] { basePath + testClass.getSimpleName()
					+ "-data.xml" };
			return xmlFilePaths;
		}
		xmlFilePaths = new String[0];
		return xmlFilePaths;
	}

	private IDataSet getDataSet(String... xmlFilePaths) throws DataSetException {
		List<IDataSet> dataSets = new ArrayList<IDataSet>(xmlFilePaths.length);
		for (String xmlPath : xmlFilePaths) {
			try {
				InputStream input = resourceLoader.getResource(xmlPath)
						.getInputStream();
				// FlatXmlDataSetBuilder fxdsb = new FlatXmlDataSetBuilder()
				// .setColumnSensing(true);
				// // turn off DTD validation
				// fxdsb.setDtdMetadata(false);

				// dataSets.add(fxdsb.build(input));
				dataSets.add(new CustomXmlDataSet(input));
			} catch (IOException e) {
				logger.warn(xmlPath + " file not found", e);
			}
		}
		if (dataSets.size() == 1) {
			return dataSets.get(0);
		}
		return new CompositeDataSet(dataSets.toArray(new IDataSet[0]));
	}

	/**
	 * 清除并插入XML数据文件到数据库.
	 * 
	 * XML数据文件中涉及的表在插入数据前会先进行清除.
	 * 
	 * @param xmlFilePaths
	 *            符合Spring Resource路径格式的文件列表.
	 */
	public void loadData() {
		execute(new CompositeOperation(DatabaseOperation.DELETE_ALL,
				DatabaseOperation.INSERT));
	}

	/**
	 * 插入XML数据文件到数据库.
	 */
	public void appendData() {
		execute(DatabaseOperation.INSERT);
	}

	/**
	 * 在数据库中删除XML数据文件中涉及的表的数据.
	 */
	public void removeData() {
		execute(DatabaseOperation.DELETE_ALL);
	}

	private void execute(DatabaseOperation operation) {
		try {
			IDatabaseConnection conn = getConnection(schema);
			ITableFilter filter = new DatabaseSequenceFilter(conn);
			operation.execute(conn, new FilteredDataSet(filter, getDataSet()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private IDatabaseConnection getConnection(String schema)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException, IOException, SQLException {
		IDatabaseConnection idc = (IDatabaseConnection) new DatabaseDataSourceConnection(dataSource);
		return idc;
	}

	private IDataSet getDataSet() throws DataSetException {
		if (dataSet == null) {
			dataSet = getDataSet(getTestDataXmlPaths());
		}
		return dataSet;
	}

}
