package pers.liujunyi.bookkeeping.mapper;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import pers.liujunyi.bookkeeping.entity.TCoreDictionary;

/***
 * 文件名称: ICoreDictionaryMapper.java
 * 文件描述: 业务字典dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年10月11日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public interface ICoreDictionaryMapper {
	
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
    public CopyOnWriteArrayList<TCoreDictionary> findChlidsDictList(@Param("parentCode")String parentCode,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    
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
    public TCoreDictionary getDictInfo(@Param("dictCode")String dictCode);
    
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
    public TCoreDictionary getDictInfoAndId(@Param("id")String id);
    
    /**
     * 根据字典父级编号查询下级最大编号值
     * @param parentCode 父级编号
     * @return 返回最大编号值
     */
    public String getMaxDictCodeValue(@Param("parentCode")String parentCode);
    
    /**
     * 根据字典父级编号查询下级字典代码值
     * @param parentCode   父级编号
     * @param dictWrod     字典代码值
     * @return 返回父级编号下级字典代码值
     */
    public String getDictWordValue(@Param("parentCode")String parentCode,@Param("dictWrod")String dictWrod);
    
    /**
     * 查询全表中字典关联实体和字段是否存在
     * @param entityName  实体名称
     * @param fieldName   字段名称
     * @return 返回字典编号
     */
    public String getDictEntityAndFieldName(@Param("entityName")String entityName,@Param("fieldName")String fieldName);
    
   /**
    * 根据字典关联实体名称、字段名称、字典代码 获取字典名称
    * @param entityName          实体名称
    * @param entityFieldName     字段名称
    * @param parentCode          当前字典父级编号 
    * @param dictWord            字典代码
    * @return 返回字典名称
    */
    public String getDictNameValue(@Param("entityName")String entityName ,@Param("entityFieldName")String entityFieldName,@Param("parentCode")String parentCode,@Param("dictWord")String dictWord );
    
    
    
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
    public int updateDictState(@Param("id")String id,@Param("stateValue")String stateValue);
    
    /**
     * 根据父编号更新是否为父级
     * @param parentCode 父级编号
     * @param stateValue 值
     * @return
     */
    public int updateIsParent(@Param("parentCode")String parentCode,@Param("stateValue")String stateValue);
}