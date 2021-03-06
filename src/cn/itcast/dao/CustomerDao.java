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
 * 客户的持久层
 * @author Administrator
 *
 */
public class CustomerDao {
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<Customer> findAll(){
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from t_customer", new BeanListHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有用户报错啦！！");
		}
	}
	
	
	/**
	 * 添加客户
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
			// 添加的SQL语句
			String sql  = "insert into t_customer values (?,?,?,?,?,?,?,?)";
			// 设置参数
			Object [] params = {c.getId(),c.getUsername(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getLove(),c.getType()};
			// 添加客户
			runner.update(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加客户报错啦！！");
		}
	}


	/**
	 * 通过id查询客户的信息
	 * @param id
	 * @return
	 */
	public Customer findById(String id) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from t_customer where id = ?", new BeanHandler<Customer>(Customer.class) ,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询单个用户报错啦！！");
		}
	}

	/**
	 * 修改用户的信息
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
			throw new RuntimeException("修改用户信息报错啦！！");
		}
	}
	
	/**
	 * 删除用户的信息
	 * @param id
	 */
	public void delete(String id) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			String sql = "delete from t_customer where id = ?";
			runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除用户信息报错啦！！");
		}
	}

	
	/**
	 * 通过用户名或者类型查询用户的列表
	 * @param username
	 * @param type
	 * @return
	 */
	public List<Customer> findAllByNameAndType(String username, String type) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			// SQL语句
			StringBuffer sb = new StringBuffer("select * from t_customer where 1=1 ");
			List<Object> list = new ArrayList<Object>();
			
			// 判断username是否为空
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
			throw new RuntimeException("查询所有用户报错啦！！");
		}
	}
	
	/**
	 * 查询所有的数据（总记录数，数据），
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByPage(int pageCode, int pageSize) {
		// 创建PageBean
		PageBean<Customer> page = new PageBean<Customer>();
		// 设置当前页和每页显示的记录数
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		// 查询总的记录数
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			// 查询的总的记录数
			long count = (Long) runner.query("select count(*) from t_customer", new ScalarHandler());
			// 设置总记录数
			page.setTotalCount((int)count);
			
			// 查询每页显示的数据beanList	limit关键字
			// limit a , b;	a 从哪条记录开始  b 每页查询的记录条数
			// a = （当前页 - 1—）* 每页查询的记录条数
			// a = (pageCode - 1)*pageSize, b = pageSize
			String selSql = "select * from t_customer limit ? , ?";
			List<Customer> beanList = runner.query(selSql, new BeanListHandler<Customer>(Customer.class), (pageCode - 1)*pageSize,pageSize);
			// 设置数据
			page.setBeanList(beanList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有数据（分页）报错啦！！");
		}
		
		return page;
	}


	/**
	 * 分页查询，带条件  拼接SQL语句
	 * @param username
	 * @param type
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Customer> findAllByConditionPage(String username,
			String type, int pageCode, int pageSize) {
		// 创建PageBean
		PageBean<Customer> page = new PageBean<Customer>();
		// 设置当前页和每页显示的记录数
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		/**
		 * 带条件查询，SQL语句的后面拼接条
		 * 拼接SQL语句后设置参数
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
		
		
		// 查询总的记录数
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			
			StringBuffer countSb = new StringBuffer("select count(*) from t_customer ");
			String countSql = countSb.append(whereSb).toString();
			
			// 查询的总的记录数
			long count = (Long) runner.query(countSql, new ScalarHandler(),params.toArray());
			// 设置总记录数
			page.setTotalCount((int)count);
			
			// 查询每页显示的数据beanList	limit关键字
			// limit a , b;	a 从哪条记录开始  b 每页查询的记录条数
			// a = （当前页 - 1—）* 每页查询的记录条数
			// a = (pageCode - 1)*pageSize, b = pageSize
			
			StringBuffer selSb = new StringBuffer("select * from t_customer ");
			StringBuffer limitSb = new StringBuffer(" limit ? , ?");
			String selSql = selSb.append(whereSb).append(limitSb).toString();
			
			// 给limit添加条件
			params.add((pageCode - 1)*pageSize);
			params.add(pageSize);
			
			// String selSql = "select * from t_customer limit ? , ?";
			
			List<Customer> beanList = runner.query(selSql, new BeanListHandler<Customer>(Customer.class), params.toArray());
			// 设置数据
			page.setBeanList(beanList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有数据（分页）报错啦！！");
		}
		return page;
	}

}













