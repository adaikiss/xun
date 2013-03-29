/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author hlw
 *
 */
public interface TooMapper {
	final String INSERT = "insert into xun_too (name)values(#{name})";
	final String SELECT_BY_ID = "select * from xun_too where id = #{id}";

	@Insert(INSERT)
	@Options(keyProperty = "id", useGeneratedKeys = true)
	Long insert(Too entity);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id"),
		@Result(property = "name")
	})
	Too selectById(Long id);
}
