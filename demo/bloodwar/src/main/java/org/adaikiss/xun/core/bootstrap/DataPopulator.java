/**
 * 
 */
package org.adaikiss.xun.core.bootstrap;

import javax.sql.DataSource;

import org.adaikiss.xun.test.DbUnitUtil;

/**
 * @author hlw
 *
 */
public class DataPopulator{

	private DbUnitUtil dbunitUtil;

	public void populate() throws Exception{
		dbunitUtil.loadData();
	}

	public DataPopulator(DataSource dataSource, String...xmlFilePaths) throws Exception{
		dbunitUtil = new DbUnitUtil(xmlFilePaths);
		this.dbunitUtil.setDataSource(dataSource);
	}
}
