/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

/**
 * @author hlw
 * 
 */
public interface NooMapper {
	Long insert(Noo entity);

	Long insertSelective(Noo entity);

	Noo selectById(Long id);

	void update(Noo entity);

	void updateByIdSelective(Noo entity);

	void delete(Long id);
}
