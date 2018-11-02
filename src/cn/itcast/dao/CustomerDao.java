package cn.itcast.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.utils.MyJdbcUtil;
import cn.itcast.vo.Customer;
import cn.itcast.vo.PageBean;

/**
 * �ͻ��ĳ־ò�
 * @author Administrator
 *
 */
public class CustomerDao {
	
	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	public List<Customer> findAll(){
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from t_customer", new BeanListHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�����û�����������");
		}
	}
	
	
	/**
	 * ��ӿͻ�
	 * @param c
	 */
	public void save(Customer c){
		try {
			/**
			 * id varchar(40) primary key,
			username varchar(20),
			gender varchar(10),
			birthday varchar(20),
			cellphone varchar(20),
			email varchar(40),
			love varchar(100),
			type varchar(40)
			 */
			QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
			// ��ӵ�SQL���
			String sql  = "insert into t_customer values (?,?,?,?,?,?,?,?)";
			// ���ò���
			Object [] params = {c.getId(),c.getUsername(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getLove(),c.getType()};
			// ��ӿͻ�
			runner.update(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ӿͻ�����������");
		}
	}


	/**
	 * ͨ��id��ѯ�ͻ�����Ϣ
	 * @param id
	 * @return
	 */
	public Customer findById(String id) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from t_customer where id = ?", new BeanHandler<Customer>(Customer.class) ,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�����û�����������");
		}
	}

	/**
	 * �޸��û�����Ϣ
	 * @param c
	 */
	public void update(Customer c) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			String sql = "update t_customer set username = ? , gender = ? , birthday = ? , cellphone=? ,email = ?, love = ?, type=? where id = ?";
			Object [] params = {c.getUsername(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getLove(),c.getType(),c.getId()};
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�޸��û���Ϣ����������");
		}
	}
	
	/**
	 * ɾ���û�����Ϣ
	 * @param id
	 */
	public void delete(String id) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			String sql = "delete from t_customer where id = ?";
			runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ɾ���û���Ϣ����������");
		}
	}

	
	/**
	 * ͨ���û����������Ͳ�ѯ�û����б�
	 * @param username
	 * @param type
	 * @return
	 */
	public List<Customer> findAllByNameAndType(String username, String type) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			// SQL���
			StringBuffer sb = new StringBuffer("select * from t_customer where 1=1 ");
			List<Object> list = new ArrayList<Object>();
			
			// �ж�username�Ƿ�Ϊ��
			if(username != null && !username.trim().isEmpty()){
				sb.append(" and username = ? ");
				list.add(username);
			}
			
			if(type != null && !type.trim().isEmpty()){
				sb.append(" and type = ? ");
				list.add(type);
			}
			
			return runner.query(sb.toString(), new BeanListHandler<Customer>(Customer.class),list.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�����û�����������");
		}
	}
	
	/**
	 * ��ѯ���е����ݣ��ܼ�¼�������ݣ���
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByPage(int pageCode, int pageSize) {
		// ����PageBean
		PageBean<Customer> page = new PageBean<Customer>();
		// ���õ�ǰҳ��ÿҳ��ʾ�ļ�¼��
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		// ��ѯ�ܵļ�¼��
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			// ��ѯ���ܵļ�¼��
			long count = (Long) runner.query("select count(*) from t_customer", new ScalarHandler());
			// �����ܼ�¼��
			page.setTotalCount((int)count);
			
			// ��ѯÿҳ��ʾ������beanList	limit�ؼ���
			// limit a , b;	a ��������¼��ʼ  b ÿҳ��ѯ�ļ�¼����
			// a = ����ǰҳ - 1����* ÿҳ��ѯ�ļ�¼����
			// a = (pageCode - 1)*pageSize, b = pageSize
			String selSql = "select * from t_customer limit ? , ?";
			List<Customer> beanList = runner.query(selSql, new BeanListHandler<Customer>(Customer.class), (pageCode - 1)*pageSize,pageSize);
			// ��������
			page.setBeanList(beanList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�������ݣ���ҳ������������");
		}
		
		return page;
	}


	/**
	 * ��ҳ��ѯ��������  ƴ��SQL���
	 * @param username
	 * @param type
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByConditionPage(String username,
			String type, int pageCode, int pageSize) {
		// ����PageBean
		PageBean<Customer> page = new PageBean<Customer>();
		// ���õ�ǰҳ��ÿҳ��ʾ�ļ�¼��
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		/**
		 * ��������ѯ��SQL���ĺ���ƴ����
		 * ƴ��SQL�������ò���
		 */
		StringBuffer whereSb = new StringBuffer(" where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(username != null && !username.trim().isEmpty()){
			whereSb.append(" and username = ?");
			params.add(username);
		}
		
		if(type != null && !type.trim().isEmpty()){
			whereSb.append(" and type = ?");
			params.add(type);
		}
		
		
		// ��ѯ�ܵļ�¼��
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			
			StringBuffer countSb = new StringBuffer("select count(*) from t_customer ");
			String countSql = countSb.append(whereSb).toString();
			
			// ��ѯ���ܵļ�¼��
			long count = (Long) runner.query(countSql, new ScalarHandler(),params.toArray());
			// �����ܼ�¼��
			page.setTotalCount((int)count);
			
			// ��ѯÿҳ��ʾ������beanList	limit�ؼ���
			// limit a , b;	a ��������¼��ʼ  b ÿҳ��ѯ�ļ�¼����
			// a = ����ǰҳ - 1����* ÿҳ��ѯ�ļ�¼����
			// a = (pageCode - 1)*pageSize, b = pageSize
			
			StringBuffer selSb = new StringBuffer("select * from t_customer ");
			StringBuffer limitSb = new StringBuffer(" limit ? , ?");
			String selSql = selSb.append(whereSb).append(limitSb).toString();
			
			// ��limit�������
			params.add((pageCode - 1)*pageSize);
			params.add(pageSize);
			
			// String selSql = "select * from t_customer limit ? , ?";
			
			List<Customer> beanList = runner.query(selSql, new BeanListHandler<Customer>(Customer.class), params.toArray());
			// ��������
			page.setBeanList(beanList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�������ݣ���ҳ������������");
		}
		return page;
	}

}













