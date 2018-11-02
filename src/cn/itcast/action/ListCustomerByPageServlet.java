package cn.itcast.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;
import cn.itcast.vo.PageBean;

/**
 * 查询所有数据（分页的）
 * @author Administrator
 *
 */
public class ListCustomerByPageServlet extends HttpServlet {

	private static final long serialVersionUID = -9084209486253210123L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 	整体的目的：集齐变量的数据，存放到PageBean对象返回到JSP的页面上
		 * 	pageCode（已经搞定）
		 * 	totalPage（JavaBean半搞定）
		 * 	pageSize（已经搞定）
		 * 	totalCount	-- dao
		 *  beanList	-- dao
		 */
		// 从客户端获取  // 如果是第一次查询，默认是第一页
		int pageCode = getPageCode(request);
		// pageSize每页显示的记录数 10
		int pageSize = 10;
		
		// 调用业务层
		CustomerService cs = new CustomerService();
		// 所有的数据都封装在该对象中
		PageBean<Customer> page = cs.findAllByPage(pageCode, pageSize);
		// 转发到页面上
		request.setAttribute("page", page);
		// 转发
		request.getRequestDispatcher("/pages/listByPage.jsp").forward(request, response);
	}
	
	/**
	 * 判断，如果是第一次访问，让pc=1 如果不是第一次访问，解析pc
	 * @param request
	 * @return
	 */
	public int getPageCode(HttpServletRequest request){
		// 获取当前页
		String pc = request.getParameter("pc");
		// 如果当前页是空，就是第一次访问，返回1
		if(pc == null || pc.trim().isEmpty()){
			return 1;
		}else{
			// 返回解析后的当前页
			return Integer.parseInt(pc);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}






