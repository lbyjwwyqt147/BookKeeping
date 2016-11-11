package pers.liujunyi.bookkeeping.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;












import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreDictionary;

/***
 * 文件名称: ICoreDictionaryService.java
 * 文件描述: 业务字典service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月11日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreDictionaryService {

	/**
	 * 保存字典信息
	 * @param dict 对象
	 * @param task  add：新增  edit：修改编辑
	 * @return
	 */
	public ConcurrentMap<String, Object> saveDictInfo(TCoreDictionary dict,String task);
	
	/**
	 * 生成业务字典树
	 * @param parentCode 父级编号
	 * @param paramsMap  ztree 所需要的参数
	 * @return ztree需要的json数据
	 */
	public String zTreeJson(String parentCode,ConcurrentMap<String,Object> paramsMap,HttpServletRequest request,String str);
	
	/**
	 * 新增字典信息
	 * @param dict
	 * @return
	 */
    public int addDict(TCoreDictionary dict);
    
    /**
     * 编辑字典信息
     * @param dict
     * @return
     */
    public int editDict(TCoreDictionary dict);
    
    /**
     * 根据父级编号查询下级字典信息集合
     * @param parentCode 父级编号
     * @return 返回父级的下级业务字典集合
     */
    public CopyOnWriteArrayList<TCoreDictionary> findChlidsDictList(String parentCode,Integer offset,Integer limit);
    
    /**
     * 获取下拉框需要的json数据
     * @param entityName    实体名称
     * @param fieldName     字段名称
     * @param isEmpty       true:表示显示‘请选择’ false 表示不显示
     * @return json数据
     */
    public String dictChlidsJson(String entityName,String fieldName,String isEmpty);
    
    /**
     * 获取纪录条数
     * @return
     */
    public Long getInfoCount(ConcurrentMap<String,Object> paramsMap);
    
    /**
     * 根据字典编号获取字典详细信息
     * @param dictCode 字典编号
     * @return 返回业务字典信息
     */
    public TCoreDictionary getDictInfo(String dictCode);
    
    /**
     * 根据编号获取字典名称
     * @param dictCode 字典编号
     * @return 返回字典名称
     */
    public String getDictName(@Param("dictCode")String dictCode);
    
    /**
     * 根据主键ID获取字典详细信息
     * @param id  主键ID
     * @return 返回业务字典信息
     */
    public TCoreDictionary getDictInfoAndId(String id);
    
    /**
     * 根据字典父级编号查询下级最大编号值
     * @param parentCode 父级编号
     * @return 返回最大编号值
     */
    public String getMaxDictCodeValue(String parentCode);
    
    /**
     * 根据字典父级编号当前数据设置最新编号值
     * @param parentCode 父级编号
     * @return 返回最新编号值
     */
    public String settingNewDictCodeValue(String parentCode);
    
    
    /**
     * 根据字典父级编号查询下级字典代码值
     * @param parentCode   父级编号
     * @param dictWrod     字典代码值
     * @return 返回父级编号下级字典代码值
     */
    public String getDictWordValue(String parentCode,String dictWrod);
    
    /**
     * 查询全表中字典关联实体和字段是否存在
     * @param entityName  实体名称
     * @param fieldName   字段名称
     * @return 返回关联实体名称
     */
    public String getDictEntityAndFieldName(String entityName,String fieldName);
    
    /**
     * 根据字典关联实体名称、字段名称、字典代码 获取字典名称
     * @param entityName          实体名称
     * @param entityFieldName     字段名称
     * @param parentCode          当前字典父级编号 
     * @param dictWord            字典代码
     * @return 返回字典名称
     */
    public String getDictNameValue(String entityName,String entityFieldName,String parentCode,String dictWord);
    
    /**
     * 删除业务字典
     * @param ids 主键数组
     * @return
     */
    public int deleteDict(String[] ids);
    
    
    /**
     * 修改业务字典状态
     * @param id 主键ID
     * @param stateValue  状态值
     * @return
     */
    public int updateDictState(String id,String stateValue);
    
    /**
     * 更新激活状态
     * @param id 主键ID 
     * @param stateValue 状态值
     * @return
     */
    public int updateActivateState(String id,String stateValue);
    
    /**
     * 根据父编号更新是否为父级
     * @param parentCode 父编号
     * @param stateValue 值
     * @return
     */
    public int updateIsParent(String parentCode,String stateValue);
}
