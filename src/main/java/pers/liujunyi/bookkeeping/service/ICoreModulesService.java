package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;





import javax.servlet.http.HttpServletRequest;

import pers.liujunyi.bookkeeping.entity.TCoreModules;

/***
 * 文件名称: ICoreModulesService.java
 * 文件描述: 资源模块service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年11月16日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreModulesService {

	/**
	 * 新增资源模块信息
	 * @param modules
	 * @return
	 */
	public int addModules(TCoreModules modules);
	
	/**
	 * 编辑资源模块信息
	 * @param modules
	 * @return
	 */
	public int editModules(TCoreModules modules);
	
	/**
	 * 保存信息
	 * @param modules
	 * @param task    add:新增   edit：编辑
	 * @param userId  当前登录人ID
	 * @param strings
	 * @return
	 */
	public String saveModulesInfo(TCoreModules modules,String task,String userId,String... strings);
	
	/**
	 * 删除资源模块
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);
	
	/**
	 * 删除资源模块同时删除关联表中数据
	 * @param ids
	 * @return
	 */
	public String deletesAndRelevance(String[] ids);
	
	/**
	 * 更新模块状态
	 * @param isActivate  1001:激活  1002:锁定
	 * @param ids  主键id
	 * @param map
	 * @return
	 */
	public int updateStatus(ConcurrentMap<String,Object> map);
	
	/**
	 * 根据父级编号 获取下级信息
	 * @param modulePid    父级编号
	 * @param isActivate   1001:激活  1002:锁定 
	 * @param map
	 * @return
	 */
	public CopyOnWriteArrayList<TCoreModules> findModulesList(ConcurrentMap<String,Object> map);
	
	/**
	 * 获取父级编号下的最大编号值
	 * @param modulePid  父级编号
	 * @return 返回最大编号值
	 */
	public String findMaxModulesCode(String modulePid);
	
	/**
	 * 生成业务字典树
	 * @param modulePid 父级编号
	 * @param paramsMap  ztree 所需要的参数
	 * @return ztree需要的json数据
	 */
	public String zTreeJson(String modulePid,ConcurrentMap<String,Object> paramsMap,HttpServletRequest request,String str);
	
	/**
     * 根据父级编号当前数据设置最新编号值
     * @param modulePid 父级编号
     * @return 返回最新编号值
     */
    public String getNewCodeValue(String modulePid);
    
    /**
	 * 获取表中纪录条数
	 * @return
	 */
	public Long getModulesCount();
}
