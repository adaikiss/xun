/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author hlw
 *
 */
public interface FooMapper {
	final String INSERT = "insert into xun_foo (name, aoo_id)values(#{name}, #{aoo.id, jdbcType=DECIMAL})";
	final String SELECT_BY_ID = "select * from xun_foo where id = #{id}";

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	Long insert(Foo foo);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id"),
		@Result(property = "name")
	})
	Foo selectById(Long id);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id"),
		@Result(property = "name"),
		@Result(property = "aoo", column = "aoo_id", javaType = Aoo.class, one = @One(select = "org.adaikiss.xun.mybatis.entity.AooMapper.selectById"))
	})
	Foo SelectByIdWithAoo(Long id);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id"),
		@Result(property = "name"),
		@Result(property = "boos", column = "id", javaType = List.class, many = @Many(select = "org.adaikiss.xun.mybatis.entity.BooMapper.selectByFoo"))
	})
	Foo SelectByIdWithBoos(Long id);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id"),
		@Result(property = "name"),
		@Result(property = "coos", column = "id", javaType = List.class, many = @Many(select = "org.adaikiss.xun.mybatis.entity.CooMapper.selectByFoo"))
	})
	Foo SelectByIdWithCoos(Long id);

	@Select(SELECT_BY_ID)
	@Results({
		@Result(property = "id"),
		@Result(property = "name"),
		@Result(property = "aoo", column = "aoo_id", javaType = Aoo.class, one = @One(select = "org.adaikiss.xun.mybatis.entity.AooMapper.selectById")),
		@Result(property = "boos", column = "id", javaType = List.class, many = @Many(select = "org.adaikiss.xun.mybatis.entity.BooMapper.selectByFoo")),
		@Result(property = "coos", column = "id", javaType = List.class, many = @Many(select = "org.adaikiss.xun.mybatis.entity.CooMapper.selectByFoo"))
	})
	Foo selectByIdWithAllAssociations(Long id);
}
