/**
 * 
 */
package org.adaikiss.xun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author hlw
 * 
 */
@Entity
public class Credit extends IdEntity {
	private User user;
	private Long credit1;
	private Long credit2;
	private Long credit3;
	private Long credit4;
	private Long credit5;
	private Long credit6;
	private Long credit7;
	private Long credit8;

	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit1() {
		return credit1;
	}

	public void setCredit1(Long credit1) {
		this.credit1 = credit1;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit2() {
		return credit2;
	}

	public void setCredit2(Long credit2) {
		this.credit2 = credit2;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit3() {
		return credit3;
	}

	public void setCredit3(Long credit3) {
		this.credit3 = credit3;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit4() {
		return credit4;
	}

	public void setCredit4(Long credit4) {
		this.credit4 = credit4;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit5() {
		return credit5;
	}

	public void setCredit5(Long credit5) {
		this.credit5 = credit5;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit6() {
		return credit6;
	}

	public void setCredit6(Long credit6) {
		this.credit6 = credit6;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit7() {
		return credit7;
	}

	public void setCredit7(Long credit7) {
		this.credit7 = credit7;
	}

	@Column(columnDefinition = "bigint default 0")
	public Long getCredit8() {
		return credit8;
	}

	public void setCredit8(Long credit8) {
		this.credit8 = credit8;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o, new String[] { "user" });
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder
				.reflectionHashCode(this, new String[] { "user" });
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
