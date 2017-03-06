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
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import pers.liujunyi.bookkeeping.entity.TCoreModules;
import pers.liujunyi.bookkeeping.service.ICoreModulesService;


/***
 * 文件名称: MySecurityMetadataSource.java (系统启动就会加载)
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
	private ICoreModulesService  modulesService;

	
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
		
		System.err.println("------返回所请求资源所需要的权限-----MySecurityMetadataSource getAttributes ----------- ");
		
		FilterInvocation filterInvocation = (FilterInvocation) object;
		// 将参数转为url      
        String requestUrl = filterInvocation.getRequestUrl();
        
	    System.out.println("requestUrl ： " + requestUrl);
	    
		if(resourceMap == null) {
			loadResourceDefine();
		}
		System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		/*if(configAttributes == null){
			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
			 returnCollection.add(new SecurityConfig("ROLE_NO_USER")); 
			return returnCollection;
		}*/
		return configAttributes;

	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	/**
	 * 
	 * 加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
//		System.err.println("-----------MySecurityMetadataSource loadResourceDefine ----------- ");
		if (resourceMap == null) {
			resourceMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
			ConcurrentMap<String, Object> params = new ConcurrentHashMap<String, Object>();
			//获取全部资源模块
			CopyOnWriteArrayList<TCoreModules> resourcesList = modulesService.findModulesList(params);
			for (TCoreModules resource : resourcesList) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// TODO:ZZQ 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
				// 关联代码：spring-security.xml
				// 关联代码：pers.liujunyi.bookkeeping.securityFilter.MyUserDetailServiceImpl#obtionGrantedAuthorities 方法中的 resources.getModuleCode()一样
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getModuleCode());
				configAttributes.add(configAttribute);
                //resource.getModuleUrl(): 资源模块对应的url 地址
				resourceMap.put(resource.getModuleUrl(), configAttributes);
			}
		}
	}

}
