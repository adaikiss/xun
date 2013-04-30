/**
 * 
 */
package org.adaikiss.xun.entity;

import javax.validation.constraints.NotNull;

import org.adaikiss.xun.validation.group.Update;





/**
 * @author hlw
 * 
 */
public abstract class IdEntity {
	@NotNull(groups = {Update.class})
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
