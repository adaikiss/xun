/**
 * 
 */
package org.adaikiss.xun.core.util;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.adaikiss.xun.core.entity.Employee;
import org.adaikiss.xun.core.entity.Member;
import org.adaikiss.xun.core.entity.Permission;
import org.adaikiss.xun.core.entity.PermissionPK;
import org.adaikiss.xun.core.entity.Preference;
import org.adaikiss.xun.core.entity.Role;
import org.adaikiss.xun.core.enumeration.UserStatus;
import org.adaikiss.xun.core.repository.EmployeeRepository;
import org.adaikiss.xun.core.repository.PermissionRepository;
import org.adaikiss.xun.core.security.shiro.Realm;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * @author hlw
 *
 */
@Component
@Lazy(false)
public class SystemInitializer {

	private static final Logger logger = LoggerFactory.getLogger(SystemInitializer.class);
	@Autowired
	private PermissionRepository permRepo;
//	@Autowired
//	private RoleRepository roleRepo;
	@Autowired
	private EmployeeRepository employeeRepo;

	public SystemInitializer(){
		System.out.println("__________________________________________________________________________");
	}
	@PostConstruct
	public void initUser() {
		Preference p = PreferenceHelper.getPreference();
		if(p.isInitialized()){
			//已经初始化
			return;
		}

		if(null != permRepo.findOne(new PermissionPK(Permission.ALL, Permission.ALL, Permission.ALL))){
			//超级权限存在与否来判断是否已经初始化
			p.setInitialized(true);
			PreferenceHelper.flush();
			return;
		}
		logger.info("初始化用户权限数据开始......");
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(resourceLoader.getResource(
					"classpath:\\bootstrap\\security-init.xml").getFile());
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			Set<Permission> perms = new HashSet<Permission>();
			Member member = null;
			Role role = null;
			Permission superPerm = null;
			//对DOM所有节点进行处理
			for(Element el : elements){
				String type = el.getName();
				if(member == null && "user".equalsIgnoreCase(type)){
					//处理用户数据
					member = new Member();
					member.setCreateTime(new Date());
					member.setEmployee(true);
					String loginName = el.attributeValue("loginName");
					if(StringUtils.isNotBlank(loginName)){
						member.setLoginName(loginName.trim());
					}
					String name = el.attributeValue("displayName");
					if(StringUtils.isNotBlank(name)){
						member.setDisplayName(name.trim());
					}
					String password = el.attributeValue("password");
					if(StringUtils.isNotBlank("password")){
						member.setPassword(Realm.encodePassword(password, member.getLoginName()));
					}
					member.setStatus(UserStatus.Normal);
					logger.info("准备用户[loginName : {}, displayName : {}]", loginName, name);
					continue;
				}
				if(role == null && "role".equalsIgnoreCase(type)){
					//处理角色数据
					role = new Role();
					String name = el.attributeValue("name");
					if(StringUtils.isNotBlank("name")){
						role.setName(name.trim());
					}
					String description = el.attributeValue("description");
					if(StringUtils.isNotBlank(description)){
						role.setDescription(description.trim());
					}
					logger.info("准备角色[name : {}, description : {}]", name, description);
					continue;
				}
				//剩下的都是权限数据
				String text = el.getText();
				if(StringUtils.isBlank(text)){
					logger.warn("权限不能为空!");
					continue;
				}
				Permission perm = new Permission(text.trim());
				String description = el.attributeValue("description");
				if(StringUtils.isNotBlank(description)){					
					perm.setDescription(description.trim());
				}
				if("*:*:*".equals(perm.getText())){
					superPerm = perm;
				}
				perms.add(perm);
				logger.info("准备权限[perm = {}, description = {}]", text, description);
			}
			if(null == member || role == null || superPerm == null){
				logger.error("用户/角色/超级权限必须指定!");
				throw new ServiceException("用户/角色/超级权限必须指定!");
			}
//			for(Permission perm : perms){
//				permRepo.save(perm);
//			}
			role.addPermissions(superPerm);
//			roleRepo.save(role);
			Employee employee = new Employee();
			employee.addRoles(role);
			employee.setMember(member);
			employeeRepo.save(employee);
			p.setInitialized(true);
			PreferenceHelper.flush();
		} catch (Exception e) {
			logger.error("初始化用户权限数据时出现异常!", e);
			throw new ServiceException("初始化用户权限数据时出现异常!", e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println(ctx.getBean(SystemInitializer.class));
	}

}
