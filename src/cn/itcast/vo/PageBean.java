package cn.itcast.vo;

import java.util.List;

/**
 * 分页的JavaBean
 * 
 * 1.pageCode	  当前页			-- 从页面获取
	2.totalPage	   总页数			-- 计算出来
	3.totalCount 总记录数			-- 查询数据库
	4.pageSize	   每页显示的记录数	-- 用户规定的（10）
	5.beanList	   显示的数据			-- 查询数据库的
	
	6.把上述这些变量封装到JavaBean中，PageBean。在各层之间传递PageBean
 *
 */
public class PageBean<T> {
	
	// 当前页
	private int pageCode;
	// 总页数 = 总记录数 / 每页显示的记录数 
//	private int totalPage;
	// 总记录数
	private int totalCount;
	// 每页显示的记录数
	private int pageSize;
	// 显示的数据
	private List<T> beanList;
	
	// 带条件查询的路径
	private String url;
	
	
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	
	/*
	 * 获取总页数
	 */
	public int getTotalPage() {
		// 总页数 = 总记录数(totalCount)  /  每页显示记录数(pageSize)
		int totalPage = totalCount / pageSize;
		if(totalCount % pageSize == 0){
			return totalPage;
		}else{
			return totalPage + 1;
		}
	}
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
