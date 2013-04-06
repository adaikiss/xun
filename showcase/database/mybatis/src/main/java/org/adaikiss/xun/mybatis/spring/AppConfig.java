/**
 * 
 */
package org.adaikiss.xun.mybatis.spring;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author hlw
 * 
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "org.adaikiss.xun.mybatis.service" })
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("org.adaikiss.xun.mybatis.entity")
@ImportResource("classpath:applicationContext.xml")
public class AppConfig implements TransactionManagementConfigurer{
	@Value("${jdbc.driver}")
	String driver;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String username;
	@Value("${jdbc.password}")
	String password;

	@Bean
	public ResourceLoader resourceLoader() {
		return new DefaultResourceLoader();
	}

//	@Bean
//	public PropertyPlaceholderConfigurer placeholder() throws IOException{
//		PropertyPlaceholderConfigurer placeholder = new PropertyPlaceholderConfigurer();
//		placeholder.setProperties(PropertiesLoaderUtils.loadAllProperties("classpath:application.properties"));
//		return placeholder;
//	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("database/database.sql").build();
		//return new PooledDataSource(driver, url, username, password);
	}

	@Bean
	public AnnotationAwareAspectJAutoProxyCreator autoProxyCreator() {
		AnnotationAwareAspectJAutoProxyCreator autoProxyCreator = new AnnotationAwareAspectJAutoProxyCreator();
		autoProxyCreator.setProxyTargetClass(true);
		return autoProxyCreator;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());
//		sqlSessionFactory.setConfigLocation(resourceLoader().getResource(
//				"classpath:mybatis-config.xml"));
		return sqlSessionFactory.getObject();
	}

	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
}
