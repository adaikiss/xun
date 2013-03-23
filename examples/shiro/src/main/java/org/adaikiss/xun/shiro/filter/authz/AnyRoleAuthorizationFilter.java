/**
 * 
 */
package org.adaikiss.xun.shiro.filter.authz;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * Filter that allows access if the current user has any role specified by the mapped value, or denies access
 * if the user does not have any of the roles specified.
 * @author hlw
 *
 */
public class AnyRoleAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        return hasAnyRole(subject, roles);
	}

	private boolean hasAnyRole(Subject subject, Set<String> roles){
		for(String role : roles){
			if(subject.hasRole(role)){
				return true;
			}
		}
		return false;
	}
}
