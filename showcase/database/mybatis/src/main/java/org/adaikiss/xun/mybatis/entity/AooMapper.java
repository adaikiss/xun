/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

/**
 * @author hlw
 * 
 */
public interface AooMapper {

	final String INSERT = "insert into xun_aoo(name) values (#{name})";
	final String SELECT_BY_ID = "select * from xun_aoo where id=#{id}";
	final String SELECT_ALL = "select * from xun_aoo";
	final String UPDATE = "update xun_aoo set name = #{name} where id = #{id}";
	final String DELETE = "delete from xun_aoo where id=#{id}";

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	Long insert(Aoo entity);

	@Select(SELECT_BY_ID)
	@Results({ @Result(property = "createTime", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
	Aoo selectById(Long id);

	@Select(SELECT_ALL)
	@Results({ @Result(property = "createTime", column = "create_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
	List<Aoo> selectAll();

	@Update(UPDATE)
	void update(Aoo entity);

	@Delete(DELETE)
	void delete(Long id);
}
