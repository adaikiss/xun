/**
 * 
 */
package org.adaikiss.xun.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author hlw
 * 
 */
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCredit1() {
		return credit1;
	}

	public void setCredit1(Long credit1) {
		this.credit1 = credit1;
	}

	public Long getCredit2() {
		return credit2;
	}

	public void setCredit2(Long credit2) {
		this.credit2 = credit2;
	}

	public Long getCredit3() {
		return credit3;
	}

	public void setCredit3(Long credit3) {
		this.credit3 = credit3;
	}

	public Long getCredit4() {
		return credit4;
	}

	public void setCredit4(Long credit4) {
		this.credit4 = credit4;
	}

	public Long getCredit5() {
		return credit5;
	}

	public void setCredit5(Long credit5) {
		this.credit5 = credit5;
	}

	public Long getCredit6() {
		return credit6;
	}

	public void setCredit6(Long credit6) {
		this.credit6 = credit6;
	}

	public Long getCredit7() {
		return credit7;
	}

	public void setCredit7(Long credit7) {
		this.credit7 = credit7;
	}

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
