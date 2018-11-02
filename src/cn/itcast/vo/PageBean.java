package cn.itcast.vo;

import java.util.List;

/**
 * ��ҳ��JavaBean
 * 
 * 1.pageCode	  ��ǰҳ			-- ��ҳ���ȡ
	2.totalPage	   ��ҳ��			-- �������
	3.totalCount �ܼ�¼��			-- ��ѯ���ݿ�
	4.pageSize	   ÿҳ��ʾ�ļ�¼��	-- �û��涨�ģ�10��
	5.beanList	   ��ʾ������			-- ��ѯ���ݿ��
	
	6.��������Щ������װ��JavaBean�У�PageBean���ڸ���֮�䴫��PageBean
 *
 */
public class PageBean<T> {
	
	// ��ǰҳ
	private int pageCode;
	// ��ҳ�� = �ܼ�¼�� / ÿҳ��ʾ�ļ�¼�� 
//	private int totalPage;
	// �ܼ�¼��
	private int totalCount;
	// ÿҳ��ʾ�ļ�¼��
	private int pageSize;
	// ��ʾ������
	private List<T> beanList;
	
	// ��������ѯ��·��
	private String url;
	
	
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	
	/*
	 * ��ȡ��ҳ��
	 */
	public int getTotalPage() {
		// ��ҳ�� = �ܼ�¼��(totalCount)  /  ÿҳ��ʾ��¼��(pageSize)
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
