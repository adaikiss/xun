/**
 * 
 */
package org.adaikiss.xun.core.repository;

import org.adaikiss.xun.core.entity.Permission;
import org.adaikiss.xun.core.entity.PermissionPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlw
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, PermissionPK> {

}
