package pers.liujunyi.bookkeeping.securityFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
 * 文件名称: MyUserDetailServiceImpl.java
 * 文件描述: 权限验证类
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private ICoreUserService userService ;
	

	@Autowired
	private ICoreUserRoleService usersRolesService;
	

	@Autowired
	private ICoreModulesService resourcesService;
	

	@Autowired
	private ICoreRoleModuleService rolesAuthoritiesService;
	

	@Autowired
	private ICoreRoleService  rolesService; 
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		
        System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername ----------- ");
		
		//取得用户的权限
		TCoreUser users = userService.getSingleUserInfo(arg0, "123456");
		if (users == null ) {
			// 在界面输出自定义的信息
			BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配！");
			throw exception;
		}
		
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
		// 封装成spring security的user
		/*User userdetail = new User(
				users.getLoginUser(), 
				users.getLoginPwd(),
				true, 
				true, 
				true,
				true, 
				grantedAuths	//用户的权限
			);*/
		User userdetail = new User(
				arg0, 
				"123456",
				true, 
				true, 
				true,
				true, 
				grantedAuths	//用户的权限
			);
		return userdetail;
	}
	
	
	 /**
	  *  取得用户的权限  
	  * @param user
	  * @return
	  */
	private Set<GrantedAuthority> obtionGrantedAuthorities(TCoreUser user) {  
       Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();  
       ConcurrentMap<String, Object> params = new ConcurrentHashMap<String, Object>();
       if(!user.getIsAdmin().trim().equals("1")){
    	   params.put("userId", user.getId());
       }
       
       /** 根据用户ID获取用户角色 */  
       CopyOnWriteArrayList<TCoreUserRole> userRolesList =  usersRolesService.findList(params);
       if(userRolesList != null && userRolesList.size() > 0){
       	    Iterator<?> userRoleIterator = userRolesList.iterator();
        	while(userRoleIterator.hasNext()){
	       		TCoreUserRole userRole = (TCoreUserRole) userRoleIterator.next();
	       		authSet.add( new SimpleGrantedAuthority("ROLE_"+userRole.getRoleCode())); 
	       		System.out.println("当前登录人的角色==：" + "ROLE_" + userRole.getRoleCode());
        	}
       }
       
       return authSet;  
   }

}
