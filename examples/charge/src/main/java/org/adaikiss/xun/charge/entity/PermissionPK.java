/**
 * @date 2011-8-14
 */
package org.adaikiss.xun.charge.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author hlw
 * 
 */
@SuppressWarnings("serial")
public class PermissionPK implements Serializable {
	private String domain;
	private String actions;
	private String targets;

	public PermissionPK(){
		
	}

	public PermissionPK(Permission perm){
		this.domain = perm.getDomain();
		this.actions = perm.getActions();
		this.targets = perm.getTargets();
	}

	public PermissionPK(String domain, String actions, String targets) {
		this.domain = domain;
		this.actions = actions;
		this.targets = targets;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getTargets() {
		return targets;
	}

	public void setTargets(String targets) {
		this.targets = targets;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof PermissionPK))
			return false;
		PermissionPK pk = (PermissionPK) obj;
		return StringUtils.equalsIgnoreCase(domain, pk.getDomain())
				&& StringUtils.equalsIgnoreCase(actions, pk.getActions())
				&& StringUtils.equalsIgnoreCase(targets, pk.getTargets());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 37
				+ (null == domain ? "".hashCode() : domain.hashCode());
		result = result * 37 + actions.hashCode();
		result = result * 37 + targets.hashCode();
		return result;
	}
}
