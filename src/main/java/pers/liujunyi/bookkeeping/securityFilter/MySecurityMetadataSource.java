package pers.liujunyi.bookkeeping.securityFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreModules;
import pers.liujunyi.bookkeeping.entity.TCoreRoleModule;
import pers.liujunyi.bookkeeping.entity.TCoreUserRole;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;
import pers.liujunyi.bookkeeping.service.ICoreRoleModuleService;
import pers.liujunyi.bookkeeping.service.ICoreUserRoleService;

/***
 * 文件名称: MySecurityMetadataSource.java
 * 文件描述: 加载资源与权限的对应关系
 * 
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
public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	@Autowired
	private ICoreUserRoleService userRoleService;
	@Autowired
	private ICoreModulesService  modulesService;
	@Autowired
	private ICoreRoleModuleService roleModuleService; 
	
	private static ConcurrentMap<String, Collection<ConfigAttribute>> resourceMap = null;
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/**
	 * 返回所请求资源所需要的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		System.err.println("-----------MySecurityMetadataSource getAttributes ----------- ");

		// 将参数转为url      
      /*  String requestUrl = ((FilterInvocation)object).getRequestUrl();
        
	    System.out.println("requestUrl is " + requestUrl);*/
		if(resourceMap == null) {
			loadResourceDefine();
		}
		/*System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
		if(requestUrl.indexOf("?")>-1){
			//requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}*/
		/*Collection<ConfigAttribute> configAttributes = resourceMap.get(object);
		if(configAttributes == null){
			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
			 returnCollection.add(new SecurityConfig("ROLE_NO_USER")); 
			return returnCollection;
		}
		return configAttributes;*/
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	/**
	 * @PostConstruct是Java EE 5引入的注解，
	 * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 * 
	 * //加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
//		System.err.println("-----------MySecurityMetadataSource loadResourceDefine ----------- ");
		if (resourceMap == null) {
			resourceMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
			ConcurrentMap<String, Object> params = new ConcurrentHashMap<String, Object>();
			CopyOnWriteArrayList<TCoreUserRole> userRoles = userRoleService.findList(params);
			for (TCoreUserRole userRole : userRoles) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// TODO:ZZQ 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
				// 关联代码：applicationContext-security.xml
				// 关联代码：com.huaxin.security.MyUserDetailServiceImpl#obtionGrantedAuthorities
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + userRole.getRoleCode());
				configAttributes.add(configAttribute);
				//resourceMap.put(userRole.getRoleCode(), configAttributes);
				//获取角色对应的资源
				params.put("roleCode", userRole.getRoleCode());
				CopyOnWriteArrayList<TCoreRoleModule> roleModules = roleModuleService.findList(params);
				System.out.println(userRole.getRoleCode() + " 菜单 大小 ： " + roleModules);
				for (TCoreRoleModule roleModule : roleModules) {
					TCoreModules modules = modulesService.getModuleInfo(null, roleModule.getModuleCode());
					System.out.println("角色："+"ROLE_" + userRole.getRoleCode() + "\t url：" +modules.getModuleUrl() );
					resourceMap.put(modules.getModuleUrl(), configAttributes);
				}
				//CopyOnWriteArrayList<TCoreModules> modulesList =  modulesService.
				
				
				
				
				//resourceMap.put(userRole.getResUrl(), configAttributes);
			}
		}
	}

}
