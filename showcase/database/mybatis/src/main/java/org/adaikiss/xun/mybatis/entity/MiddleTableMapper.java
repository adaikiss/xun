/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author hlw
 *
 */
public interface MiddleTableMapper {
	final String INSERT_FOO_COO = "insert into xun_foo_coo (foo_id, coo_id) values (#{fooId, jdbcType=DECIMAL}, #{cooId, jdbcType=DECIMAL})";
	final String DELETE_FOO_COO = "delete from xun_foo_coo where foo_id = #{fooId, jdbcType=DECIMAL} and coo_id = #{cooId, jdbcType=DECIMAL}";

	@Insert(INSERT_FOO_COO)
	void insertFooCoo(@Param("fooId")Long fooId, @Param("cooId")Long cooId);

	@Delete(DELETE_FOO_COO)
	void deleteFooCoo(Long fooId, Long cooId);
}
