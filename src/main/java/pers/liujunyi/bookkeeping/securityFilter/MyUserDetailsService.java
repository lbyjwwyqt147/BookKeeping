package pers.liujunyi.bookkeeping.securityFilter;



import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.entity.TCoreUserRole;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.service.ICoreRoleModuleService;
import pers.liujunyi.bookkeeping.service.ICoreRoleService;
import pers.liujunyi.bookkeeping.service.ICoreUserRoleService;
import pers.liujunyi.bookkeeping.service.ICoreUserService;



/***
 * 文件名称: MyUserDetailsService.java
 * 文件描述: 该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 *         该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	
	/** 用户信息 service */
	@Autowired
	private ICoreUserService userService ;
	
	/** 用户角色 service */
	@Autowired
	private ICoreUserRoleService usersRolesService;
	
	/** 资源模块 service */
	@Autowired
	private ICoreModulesService resourcesService;
	
	/** 角色权限 service */
	@Autowired
	private ICoreRoleModuleService rolesAuthoritiesService;
	
	/** 角色 service */
	@Autowired
	private ICoreRoleService  rolesService; 
	
	@Autowired
	private DataSource dataSource;
	
	public MyUserDetailsService(){
		 
	}
	
	
	/**
	 * 登入默认会调整到这里
	 * @param username 登录账户
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		/** 用户对象 */
		TCoreUser user = null;
		try {
			 System.out.println("登录名：== " + username); 
			 /** 通过登录名得到当前登录人信息 */
		
			 user = userService.getSingleUserInfo(username, "123456");
			 
			 /** 得到登录人所属权限组 */
			 Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
			 
			 boolean enables = true;  
		     boolean accountNonExpired = true;  
		     boolean credentialsNonExpired = true;  
		     boolean accountNonLocked = true;  
		     User  userdetail = new User(username, "",  
		                enables, accountNonExpired, credentialsNonExpired,  
		                accountNonLocked, grantedAuths);  
		     System.out.println("验证登录账户：=" +userdetail.getAuthorities().size());
		     return userdetail; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	 /**
	  *  取得用户的权限  
	  * @param user
	  * @return
	  */
	private Set<GrantedAuthority> obtionGrantedAuthorities(TCoreUser user) {  
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();  
        ConcurrentMap<String, Object> params = new ConcurrentHashMap<String, Object>();
        params.put("userId", user.getId());
        /** 根据用户ID获取用户角色 */  
        CopyOnWriteArrayList<TCoreUserRole> userRolesList =  usersRolesService.findList(params);
        if(userRolesList != null && userRolesList.size() > 0){
        	Iterator<?> userRoleIterator = userRolesList.iterator();
        	while(userRoleIterator.hasNext()){
        		TCoreUserRole userRole = (TCoreUserRole) userRoleIterator.next();
        		authSet.add( new SimpleGrantedAuthority(userRole.getRoleCode())); 
        		System.out.println("当前登录人的角色==：" + userRole.getRoleCode());
        	}
        }
        
        return authSet;  
    }

    
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	
	
    
    
}
