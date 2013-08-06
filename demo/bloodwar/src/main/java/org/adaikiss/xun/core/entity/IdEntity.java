/**
 * 
 */
package org.adaikiss.xun.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.adaikiss.xun.core.validation.group.ValidationGroup.Save;
import org.adaikiss.xun.core.validation.group.ValidationGroup.Update;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hlw
 *
 */
@MappedSuperclass
public abstract class IdEntity {
	@Null(groups = Save.class)
	@NotNull(groups = Update.class)
	protected Long id;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
