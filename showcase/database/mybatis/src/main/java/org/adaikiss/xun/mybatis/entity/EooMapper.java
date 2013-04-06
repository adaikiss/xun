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
@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhcacheCache.class)
public interface EooMapper {
	final String SELECT_BY_ID = "select * from xun_eoo where id = #{id}";
	final String INSERT = "insert into xun_eoo (name) values (#{name})";
	final String UPDATE = "update xun_eoo set name = #{name} where id = #{id}";
	final String DELETE = "delete from xun_eoo where id = #{id}";

	@Select(SELECT_BY_ID)
	@Options(useCache=true)
	Eoo selectById(Long id);

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true)
	Long insert(Eoo eoo);

	@Update(UPDATE)
	@Options(flushCache = true, useCache = true)
	void update(Eoo eoo);

	@Delete(DELETE)
	@Options(flushCache = true)
	void delete(Long id);
}
