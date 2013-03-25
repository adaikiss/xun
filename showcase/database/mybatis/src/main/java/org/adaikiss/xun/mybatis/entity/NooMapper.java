/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

/**
 * @author hlw
 * 
 */
public interface NooMapper {
	Long add(Noo entity);

	Noo get(Long id);

	void update(Noo entity);

	void remove(Long id);
}
