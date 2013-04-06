/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.UUID;

import org.adaikiss.xun.mybatis.typehandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author hlw
 *
 */
public interface IooMapper {
	final String INSERT = "insert into xun_ioo (id, name)values(#{id, typeHandler=org.adaikiss.xun.mybatis.typehandler.UUIDTypeHandler}, #{name})";
	final String SELECT_BY_ID = "select * from xun_ioo where id = #{id}";

	@Insert(INSERT)
	@Options(keyProperty = "id", useGeneratedKeys = true)
	Long insert(Ioo ioo);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id", typeHandler = UUIDTypeHandler.class),
		@Result(property = "name")
	})
	Ioo selectById(@Param("id")UUID id);
}
