/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author hlw
 *
 */
@CacheNamespace(implementation = org.adaikiss.xun.mybatis.cache.MemcachedCache.class)
public interface MooMapper {
	final String SELECT_BY_ID = "select * from xun_moo where id = #{id}";
	final String INSERT = "insert into xun_moo (name) values (#{name})";
	final String UPDATE = "update xun_moo set name = #{name} where id = #{id}";
	final String DELETE = "delete from xun_moo where id = #{id}";

	@Select(SELECT_BY_ID)
	@Options(useCache=true)
	Moo selectById(Long id);

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true)
	Long insert(Moo entity);

	@Update(UPDATE)
	@Options(flushCache = true, useCache = true)
	void update(Moo entity);

	@Delete(DELETE)
	@Options(flushCache = true)
	void delete(Long id);
}
