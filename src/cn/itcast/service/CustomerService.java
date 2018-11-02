package cn.itcast.service;

import java.util.List;

import cn.itcast.dao.CustomerDao;
import cn.itcast.utils.UUIDUtil;
import cn.itcast.vo.Customer;
import cn.itcast.vo.PageBean;

/**
 * �ͻ���ҵ���
 * @author Administrator
 *
 */
public class CustomerService {
	
	
	/**
	 * ��ҳ��ѯ����������
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByConditionPage(String username,String type,int pageCode,int pageSize){
		CustomerDao dao = new CustomerDao();
		return dao.findAllByConditionPage(username,type,pageCode,pageSize);
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByPage(int pageCode,int pageSize){
		CustomerDao dao = new CustomerDao();
		return dao.findAllByPage(pageCode,pageSize);
	}
	
	/**
	 * ͨ���û����������Ͳ�ѯ���е��û���Ϣ
	 * @param username
	 * @param type
	 * @return
	 */
	public List<Customer> findAllByNameAndType(String username,String type){
		CustomerDao dao = new CustomerDao();
		return dao.findAllByNameAndType(username,type);
	}
	
	
	/**
	 * �޸��û�����Ϣ
	 * @param c
	 */
	public void updateCustomer(Customer c){
		CustomerDao dao = new CustomerDao();
		dao.update(c);
	}
	
	
	/**
	 * ��ӿͻ�
	 * @param c
	 */
	public void addCustomer(Customer c){
		// ��һ��ҵ��
		// �Լ��־�����������
		String id = UUIDUtil.getUUID();
		c.setId(id);
		
		CustomerDao dao = new CustomerDao();
		// ��ӿͻ��ķ���
		dao.save(c);
	}
	
	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	public List<Customer> findAll(){
		CustomerDao dao = new CustomerDao();
		return dao.findAll();
	}
	
	/**
	 * ͨ��id��ѯ�ͻ�����Ϣ
	 * @param id
	 * @return
	 */
	public Customer findById(String id){
		CustomerDao dao = new CustomerDao();
		return dao.findById(id);
	}
	
	public void deleteById(String id) {
		CustomerDao dao = new CustomerDao();
		dao.delete(id);
	}

}













