/**
 * 
 */
package org.adaikiss.xun.mybatis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author hlw
 *
 */
public class SqlUtil {

	private static final Logger logger = LoggerFactory.getLogger(SqlUtil.class);

	/**
	 * run a .sql file on classpath
	 * @param url connection url
	 * @param user username
	 * @param password password
	 * @param driver connection driver
	 * @param sqlFile .sql file on classpath
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void runScript(String url, String user, String password, String driverClassName, String sqlFile) throws SQLException, IOException, ClassNotFoundException{
		logger.debug(new StringBuilder("Running script with [").append(url).append(", ").append(user).append(", ").append(password).append(", ").append(driverClassName).append(", ").append(sqlFile).toString());
		Class<? extends java.sql.Driver> driver = (Class<Driver>)Class.forName(driverClassName);
		Connection conn = getConnection(url, user, password, driver);
		runScript(conn, sqlFile);
		conn.close();
	}

	/**
	 * run a .sql file on classpath
	 * @param url connection url
	 * @param user username
	 * @param password password
	 * @param driver connection driver
	 * @param sqlFile .sql file on classpath
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void runScript(String url, String user, String password, Class<? extends java.sql.Driver> driver, String sqlFile) throws SQLException, IOException, ClassNotFoundException{
		Connection conn = getConnection(url, user, password, driver);
		runScript(conn, sqlFile);
		conn.close();
	}

	public static void runScript(Connection conn, String sqlFile) throws IOException{
		File file = Resources.getResourceAsFile(sqlFile);
		ScriptRunner runner = new ScriptRunner(conn);
		runner.runScript(new BufferedReader(new FileReader(file)));
	}

	public static Connection getConnection(String url, String user, String password, Class<? extends java.sql.Driver> driver) throws ClassNotFoundException, SQLException{
		Class.forName(driver.getName());
		return DriverManager.getConnection(url, user, password);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		runScript("jdbc:mysql://localhost:3306/mybatis", "root", "81656583", org.h2.Driver.class, "database/database.sql");
	}

}
