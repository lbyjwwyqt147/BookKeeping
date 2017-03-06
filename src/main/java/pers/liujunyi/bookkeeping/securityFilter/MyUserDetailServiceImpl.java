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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreModules;
import pers.liujunyi.bookkeeping.entity.TCoreUser;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.service.ICoreUserService;

/***
 * 文件名称: MyUserDetailServiceImpl.java  用户请求登录会进入此类的loadUserByUsername方法 验证用户
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
	private ICoreModulesService resourcesService;

	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
        System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername 用户登录 ----------- ");
		
    	System.out.println(" ================== 登录名:" + username);
        
    	System.out.println(SecurityContextHolder.getContext().getAuthentication());
    	
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        

		//String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String username = userDetails.getUsername();
		//System.out.println(" ===== username : " + username);
        
		//取得用户的权限
		TCoreUser users = userService.getSingleUserInfo(username, null);
		
	
		
		if (users == null ) {
			
			System.out.println(" ================== 用户名或密码不匹配！" );
			
			// 在界面输出自定义的信息
			BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配！");
			throw exception;
		}
		
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
		// 封装成spring security的user
	    User userdetail = new User(
				users.getLoginUser(), 
				users.getLoginPwd(),
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
       
       /** 根据用户ID获取用户所拥有的模块资源 */  
       CopyOnWriteArrayList<TCoreModules> resourcesList =  resourcesService.findModules(user.getId());
       if(resourcesList != null && resourcesList.size() > 0){
    	    // TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
    	    // 关联代码：applicationContext-security.xml
    	    // 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
    	    // resources.getModuleCode() :为 资源模块表中 模块编号
       	    Iterator<?> resourcesIterator = resourcesList.iterator();
        	while(resourcesIterator.hasNext()){
        		TCoreModules resources = (TCoreModules) resourcesIterator.next();
	       		System.out.println("模块编号：" + "ROLE_" + resources.getModuleCode());
	       		authSet.add( new SimpleGrantedAuthority("ROLE_"+resources.getModuleCode())); 
        	}
       }
       
       return authSet;  
   }

}
