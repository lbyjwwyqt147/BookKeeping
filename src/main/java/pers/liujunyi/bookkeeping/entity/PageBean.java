package pers.liujunyi.bookkeeping.entity;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



/***
 * 文件名称: PageBean.java
 * 文件描述: 分页bean
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2016年09月20日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class PageBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 总纪录条数 */
	private AtomicInteger totalCount;
	/** 总页码数 */
	private AtomicInteger totalPage;
	/** start表示当前页开始的记录数,start=每页行数*(当前页数-1) */
	private AtomicInteger start;
	/** 当前页结束的记录行数 */
	private AtomicInteger end;
	/** 每页显示纪录行数 默认10条 */
	private AtomicInteger rowsPageSize;
	/** 当前页数 */
	private AtomicInteger currentPage;
	/** 是否是第一页 */
	private AtomicBoolean isFirstPage;
	/** 是否为最后一页 */
	private AtomicBoolean isLastPage;
	/** 返回结果集 */
	private CopyOnWriteArrayList<T> resultList;
	/** 查询参数 */
	private ConcurrentMap<String, Object> paramsMap;
	/** 排序字段 */
	private String sort;
	/** 排序方式  asc 、desc*/
	private String order;
	
	/**
	 * 初始化变量
	 */
	public PageBean(){
		start = new AtomicInteger(0);  
        end = new AtomicInteger(0);   
        rowsPageSize = new AtomicInteger(10);
        totalPage = new AtomicInteger(1);
        currentPage = new AtomicInteger(1);   
        totalCount = new AtomicInteger(0);
        isFirstPage = new AtomicBoolean(false);
        isLastPage = new AtomicBoolean(false);
        resultList =  new CopyOnWriteArrayList<T>();
        paramsMap =  new ConcurrentHashMap<String, Object>();
	}
	
	/**
	 * 
	 * @param totalCount  总纪录条数
	 * @param currentPage 当前页码
	 * @param rowsPageSize 每页显示的纪录条数
	 */
	public PageBean(AtomicInteger totalCount,AtomicInteger totalPage,AtomicInteger start,AtomicInteger end,
			AtomicInteger rowsPageSize,AtomicInteger currentPage,AtomicBoolean isFirstPage,AtomicBoolean isLastPage,
			CopyOnWriteArrayList<T> resultList,ConcurrentMap<String, Object> paramsMap,String sort,String order){
		
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.start = start;
		this.end = end;
		this.rowsPageSize = rowsPageSize;
		this.currentPage = currentPage;
		this.isFirstPage = isFirstPage;
		this.isLastPage = isLastPage;
		this.resultList = resultList;
		this.paramsMap = paramsMap;
		this.sort = sort;
		this.order = order;
		
	}

	public AtomicInteger getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(AtomicInteger totalCount) {
		this.totalCount = totalCount;
	}

	public AtomicInteger getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(AtomicInteger totalPage) {
		this.totalPage = totalPage;
	}

	public AtomicInteger getStart() {
		return start;
	}

	public void setStart(AtomicInteger start) {
		this.start = start;
	}

	public AtomicInteger getEnd() {
		return end;
	}

	public void setEnd(AtomicInteger end) {
		this.end = end;
	}

	public AtomicInteger getRowsPageSize() {
		return rowsPageSize;
	}

	public void setRowsPageSize(AtomicInteger rowsPageSize) {
		this.rowsPageSize = rowsPageSize;
	}

	public AtomicInteger getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(AtomicInteger currentPage) {
		this.currentPage = currentPage;
	}

	public AtomicBoolean getIsFirstPage() {
		return isFirstPage;
	}

	public void setIsFirstPage(AtomicBoolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public AtomicBoolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(AtomicBoolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public CopyOnWriteArrayList<T> getResultList() {
		return resultList;
	}

	public void setResultList(CopyOnWriteArrayList<T> resultList) {
		this.resultList = resultList;
	}

	public ConcurrentMap<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(ConcurrentMap<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
	

}
