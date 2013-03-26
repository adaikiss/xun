/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author hlw
 * 
 */
public interface BooMapper {
	final String INSERT = "insert into xun_boo (name, foo_id) values (#{name}, #{fooId, jdbcType=DECIMAL})";
	final String SELECT_BY_FOO = "select * from xun_boo where foo_id = #{fooId}";

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	Long insert(Boo boo);

	@Select(SELECT_BY_FOO)
	List<Boo> selectByFoo(Long fooId);
}
