package cn.itcast.service;

import java.util.List;

import cn.itcast.dao.CustomerDao;
import cn.itcast.utils.UUIDUtil;
import cn.itcast.vo.Customer;
import cn.itcast.vo.PageBean;

/**
 * 客户的业务层
 * @author Administrator
 *
 */
public class CustomerService {
	
	
	/**
	 * 分页查询（带条件）
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByConditionPage(String username,String type,int pageCode,int pageSize){
		CustomerDao dao = new CustomerDao();
		return dao.findAllByConditionPage(username,type,pageCode,pageSize);
	}
	
	/**
	 * 分页查询
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByPage(int pageCode,int pageSize){
		CustomerDao dao = new CustomerDao();
		return dao.findAllByPage(pageCode,pageSize);
	}
	
	/**
	 * 通过用户名或者类型查询所有的用户信息
	 * @param username
	 * @param type
	 * @return
	 */
	public List<Customer> findAllByNameAndType(String username,String type){
		CustomerDao dao = new CustomerDao();
		return dao.findAllByNameAndType(username,type);
	}
	
	
	/**
	 * 修改用户的信息
	 * @param c
	 */
	public void updateCustomer(Customer c){
		CustomerDao dao = new CustomerDao();
		dao.update(c);
	}
	
	
	/**
	 * 添加客户
	 * @param c
	 */
	public void addCustomer(Customer c){
		// 有一点业务
		// 自己持久主键的内容
		String id = UUIDUtil.getUUID();
		c.setId(id);
		
		CustomerDao dao = new CustomerDao();
		// 添加客户的方法
		dao.save(c);
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<Customer> findAll(){
		CustomerDao dao = new CustomerDao();
		return dao.findAll();
	}
	
	/**
	 * 通过id查询客户的信息
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













