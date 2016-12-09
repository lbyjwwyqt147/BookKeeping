package pers.liujunyi.bookkeeping.securityFilter;


import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * AccessdecisionManager在Spring security中是很重要的。  
 * 在验证部分简略提过了，所有的Authentication实现需要保存在一个GrantedAuthority对象数组中。
 * 这就是赋予给主体的权限。 GrantedAuthority对象通过AuthenticationManager  
 * 保存到 Authentication对象里，然后从AccessDecisionManager读出来，进行授权判断。 
 * 
 * Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。
 * 一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。 
 * 这个 AccessDecisionManager 被AbstractSecurityInterceptor调用， 
 * 它用来作最终访问控制的决定。 这个AccessDecisionManager接口包含三个方法：
 * void decide(Authentication authentication, Object secureObject,  
 *   List<ConfigAttributeDefinition> config) throws AccessDeniedException;  
 *   boolean supports(ConfigAttribute attribute);  
 *   boolean supports(Class clazz);  
 * 从第一个方法可以看出来，AccessDecisionManager使用方法参数传递所有信息，这好像在认证评估时进行决定。   
 * 特别是，在真实的安全方法期望调用的时候，传递安全Object启用那些参数。   
 * 比如，让我们假设安全对象是一个MethodInvocation。   
 * 很容易为任何Customer参数查询MethodInvocation，  
 * 然后在AccessDecisionManager里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。   
 * 如果访问被拒绝，实现将抛出一个AccessDeniedException异常。  
 * 这个 supports(ConfigAttribute) 方法在启动的时候被  
 * AbstractSecurityInterceptor调用，来决定AccessDecisionManager  
 * 是否可以执行传递ConfigAttribute。   
 * supports(Class)方法被安全拦截器实现调用，  
 * 包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型。    
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	
	/** 实现 AccessDecisionManager 接口中方法 */
	//这个方法在url请求时才会调用，服务器启动时不会执行这个方法，前提是需要在<http>标签内设置  <custom-filter>标签  
    /* 
     * 参数说明： 
     * 1、configAttributes 装载了请求的url允许的角色数组 。这里是从MySecurityMetadataSource里的loadResourceDefine方法里的atts对象取出的角色数据赋予给了configAttributes对象 
     * 2、authentication 装载了从数据库读出来的角色 数据。这里是从MyUserDetailsService里的loadUserByUsername方法里的auths对象的值传过来给 authentication 对象 
     *   
     * */     
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		
		System.out.println(" ============ MyAccessDecisionManager decide Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes  ====================");

		
		/* 
         * authentication装载了用户的信息数据，其中有角色。是MyUserDetailsService里的loadUserByUsername方法的userdetail对象传过来的 
         * userdetail一共有7个参数（下面打印出来的数据可对应一下security的User类，这个类可以看到有寻7个参数）， 
         * 最后一个是用来保存角色数据的，如果角色为空，将无权访问页面。 
         * 看到下面的打印数据，  Granted Authorities: ROLE_ADMIN，ROLE_ADMIN 就是角色了。 
         * 如果显示Not granted any authorities，则说明userdetail的最后一个参数为空，没有传送角色的值过来
         * */
		if(configAttributes == null) {    
            return;    
        }   
        
        /** 所请求的资源拥有的权限(一个资源对多个权限) */    
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();    
        while(iterator.hasNext()) {    
            ConfigAttribute configAttribute = iterator.next();    
            /** 访问所请求资源所需要的权限 */    
            String needPermission = configAttribute.getAttribute();    
            System.out.println("needPermission is " + needPermission);   
             /** 用户所拥有的权限authentication    ga 为用户所被赋予的权限 */    
            for(GrantedAuthority ga : authentication.getAuthorities()) {   
            	System.out.println(ga.getAuthority());
            	/** 判断两个请求的url页面的权限和用户的权限是否相同，如相同，允许访问 */
                if(needPermission.equals(ga.getAuthority())) {    
                	/* <intercept-url pattern="/**" access="ROLE_ADMIN" /> 
                     * 如果applicationContext-security.xml的http标签里面有这种配置， 
                     * 在needRole为ROLE_USER时，即使needRole和ga.getAuthority权限匹配了，但权限是ROLE_USER，即使执行了return， 
                     * 还是会无法访问请求的url页面，因为最终都是以http标签里的权限来拦截，即只能在权限为 ROLE_ADMIN才可访问                                           
                     */ 
                    return;    
                }    
            }    
        }    
        /** 如果上面的needRole和ga.getAuthority两个权限没有匹配，将不允许访问 */
        /** 没有权限    会跳转到login.jsp页面 */  
        throw new AccessDeniedException(" 没有权限访问"); 
		
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		System.out.println(" ============ MyAccessDecisionManager supports ConfigAttribute arg0  ====================");
		 return true; 
	}

	@Override
	public boolean supports(Class<?> arg0) {
		System.out.println(" =========== MyAccessDecisionManager supports Class<?> arg0 =========== ");
		 return true; 
	}
	/** 实现 AccessDecisionManager 接口中方法  结束 */
	
	

}
