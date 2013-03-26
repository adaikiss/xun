/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

/**
 * @author hlw
 * 
 */
public interface NooMapper {
	/**
	 * includes null values
	 * @param entity
	 * @return
	 */
	Long insert(Noo entity);

	/**
	 * excludes null values
	 * @param entity
	 * @return
	 */
	Long insertSelective(Noo entity);

	Noo selectById(Long id);

	/**
	 * includes null values
	 * @param entity
	 */
	void update(Noo entity);

	/**
	 * excludes null values
	 * @param entity
	 */
	void updateByIdSelective(Noo entity);

	void delete(Long id);
}
