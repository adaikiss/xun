/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author hlw
 *
 */
public interface CooMapper {
	String INSERT = "insert into xun_coo (name) values (#{name})";
	String SELECT_BY_FOO = "select * from xun_coo c right join xun_foo_coo fc on c.id = fc.coo_id where fc.foo_id = #{fooId, jdbcType=DECIMAL}";

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	Long insert(Coo coo);

	@Select(SELECT_BY_FOO)
	@Results({
		@Result(property = "id"),
		@Result(property = "name")
	})
	List<Coo> selectByFoo(Long fooId);
}
