package pers.liujunyi.bookkeeping.securityFilter;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreRole;
import pers.liujunyi.bookkeeping.entity.TCoreRoleModule;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.service.ICoreRoleModuleService;
import pers.liujunyi.bookkeeping.service.ICoreRoleService;
import pers.liujunyi.bookkeeping.service.impl.CoreRoleServiceImpl;



/***
 * 文件名称: MyInvocationSecurityMetadataSourceService.java
 * 文件描述: 加载资源与权限的对应关系    
 *         最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	
    /** 角色service*/ 
	@Autowired
    private ICoreRoleService rolesService; 
    /** 角色权限service */
    @Autowired
    private ICoreRoleModuleService rolesAuthoritiesService;
    /** 资源模块service */
    @Autowired
    private ICoreModulesService sysResourcesService;
    
    
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;    
    private RequestMatcher pathMatcher;  
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	/** 实现FilterInvocationSecurityMetadataSource接口中的方法 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>(); 
	}

	 /** 
	  * 构造函数由spring调用 
	  * */    
    @SuppressWarnings("resource")
    public MyInvocationSecurityMetadataSourceService(){   
     
      loadResourceDefine();    
    }  
    
	/**
	 * 
	 * 根据URL，找到相关的权限配置。
	 * @param object object 是一个URL，被用户请求的url
	 * 返回所请求资源所需要的权限 
	 */
	@SuppressWarnings("unused")
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		  
		 // object 是一个URL，被用户请求的url。
		/*  String url = ((FilterInvocation) object).getRequestUrl();
		  
		        int firstQuestionMarkIndex = url.indexOf("?");

		        if (firstQuestionMarkIndex != -1) {
		            url = url.substring(0, firstQuestionMarkIndex);
		        }

		  Iterator<String> ite = resourceMap.keySet().iterator();

		  while (ite.hasNext()) {
		   String resURL = ite.next();
		   
		   if (urlMatcher.pathMatchesUrl(url, resURL)) {

		    return resourceMap.get(resURL);
		   }
		  }

		  return null;*/
		  
       String requestUrl = ((FilterInvocation) object).getRequestUrl();  
        System.out.println("请求url ===： " + requestUrl);  
        if(resourceMap == null) {  
            loadResourceDefine();  
        }  
        System.out.println("请求拥有的角色： == "+resourceMap.get(requestUrl));
        return resourceMap.get(requestUrl);  
		
		/* Iterator<String> it = resourceMap.keySet().iterator();  
         while (it.hasNext()) {  
             String resURL = it.next();  
             Iterator<String> ite = resourceMap.keySet().iterator();  
             pathMatcher = new AntPathRequestMatcher(resURL);  
         if (pathMatcher.matches(((FilterInvocation) object).getRequest())) {  
                 Collection<ConfigAttribute> returnCollection =  
                                       resourceMap.get(resURL);  
                 return returnCollection;  
             }  
         }  
		return null;*/
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	/** 实现FilterInvocationSecurityMetadataSource接口中的方法  结束 */
	

	
	 //加载所有资源与权限的关系    
    private void loadResourceDefine(){    
        if (resourceMap == null) {  
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
                //参数map
                ConcurrentMap<String, Object> params = new ConcurrentHashMap<String, Object>();
            	/** 获取所有角色信息 */
                CopyOnWriteArrayList<TCoreRole> rolesList = rolesService.findRolesList(params);  
                if(rolesList != null && rolesList.size() > 0){
                	Iterator<?> roleIterator = rolesList.iterator();
                	while(roleIterator.hasNext()){
                		TCoreRole role = (TCoreRole) roleIterator.next();
                		Collection<ConfigAttribute> configAttributes =  new ArrayList<ConfigAttribute>();  
         	            // 以权限名封装为Spring的security Object    
         	            //resource.getRoleCode() 角色名称代码  可随意 role_admin 或者 admin  
         	            ConfigAttribute configAttribute =  new SecurityConfig(role.getRoleCode());  
         	            configAttributes.add(configAttribute);  
         	            
         	            /** 根据角色ID获取角色拥有的权限 */
         	            params.put("roleCode",role.getRoleCode());
         	            CopyOnWriteArrayList<TCoreRoleModule> rolesAuthoritiesList = rolesAuthoritiesService.findList(params);
         	            if(rolesAuthoritiesList != null && rolesAuthoritiesList.size() > 0){
         	            	Iterator<?> roleModulesIterator = rolesAuthoritiesList.iterator();
         	            	while(roleModulesIterator.hasNext()){
         	            		TCoreRoleModule  roleModule = (TCoreRoleModule) roleModulesIterator.next();
         	            		 resourceMap.put(roleModule.getModuleCode(), configAttributes); 
          	    	            System.out.println(" ===================== ");
          	    	            System.out.println("资源==："+roleModule.getModuleCode() +" ---- 角色=："+configAttributes);
          	    	            System.out.println(" ===================== ");
         	            	}
         	            }
         	          
                	}
                }
            
            }
        
        System.out.println(resourceMap.size());
              
          
                    
    }

    
    /**
     * 获取所有角色信息
     * @return
     */
	private List<TCoreRole> getRolesList(){
    	try {
			ArrayList<TCoreRole> resultList = new ArrayList<TCoreRole>();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from sys_roles where IS_ENABLED = '1' ");
			//resultList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysRoles>(SysRoles.class));
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * 根据角色ID获取角色拥有的权限
	 * @param roleId  角色ID
	 * @return
	 */
	private List<TCoreRoleModule> getAuthoritiesList(String roleId){
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from sys_roles_authorities where ROLE_ID = '"+roleId+"' and IS_ENABLED = '1' ");
			//List<SysRolesAuthorities> resultList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysRolesAuthorities>(SysRolesAuthorities.class));
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ICoreRoleService getRolesService() {
		return rolesService;
	}

	public void setRolesService(ICoreRoleService rolesService) {
		this.rolesService = rolesService;
	}

	public ICoreRoleModuleService getRolesAuthoritiesService() {
		return rolesAuthoritiesService;
	}

	public void setRolesAuthoritiesService(
			ICoreRoleModuleService rolesAuthoritiesService) {
		this.rolesAuthoritiesService = rolesAuthoritiesService;
	}

	public ICoreModulesService getSysResourcesService() {
		return sysResourcesService;
	}

	public void setSysResourcesService(ICoreModulesService sysResourcesService) {
		this.sysResourcesService = sysResourcesService;
	}
    
	/**
	 * 根据权限ID获取权限拥有的资源模块
	 * @param authorityId  权限ID
	 * @return
	 */
	/*private List<SysResources> getResourcesList(String authorityId){
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from sys_resources AS r  where r.RESOURCE_ID in (select RESOURCE_ID from sys_authorities_resources where AUTHORITY_ID = '"+authorityId+"' and IS_ENABLED = '1' ) and r.IS_ENABLED = '1' ");
			List<SysResources> resultList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysResources>(SysResources.class));
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/


	
	
	
	
	
    
    
}
