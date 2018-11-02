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
 * 分页查询，带条件操作
 * @author Administrator
 *
 */
public class ListConditionByPageServlet extends HttpServlet {

	private static final long serialVersionUID = -4084927848246923604L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * 1.获取条件
		 * 2.创建PageBean装数据
		 * 	* pageCode	从页面获取的 
		 * 	* pageSize	自己规定
		 */
		// 设置post请求乱码
//		 request.setCharacterEncoding("UTF-8");
		// 解决get方式中文乱码的问题
		// 接收数据
		String username = request.getParameter("username");
		if(username != null && !username.trim().isEmpty()){
			// 解决get中文乱码
			username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(username);
		}
		String type = request.getParameter("type");
		if(type != null && !type.trim().isEmpty()){
			// 解决get中文乱码
			type = new String(type.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(type);
		}
		
		
		// 路径为：/customer/listConditionByPage?username=&type=%E9%92%BB%E7%9F%B3%E4%BC%9A%E5%91%98   -- 超链接的请求
		// 路径为：/customer/listConditionByPage?username=&type=%E9%92%BB%E7%9F%B3%E4%BC%9A%E5%91%98  -- 点击表单的请求
		String path = getPath(request);
		System.out.println("路径为："+path);
		
		// 调用业务层
		int pageCode = getPageCode(request);
		int pageSize = 10;
		
		CustomerService cs = new CustomerService();
		PageBean<Customer> page = cs.findAllByConditionPage(username, type, pageCode, pageSize);
		
		// 设置查询条件的url
		page.setUrl(path);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/listConditionByPage.jsp").forward(request, response);
		
	}
	
	/**
	 * 目的：是想获取完整的链接有可能：/customer/listConditionByPage?username=美美&type=黄金会员&pc=3
	 */
	public String getPath(HttpServletRequest request){
		// 获取客户机的信息
		// 获取项目名称
		String cPath = request.getContextPath();
		// 获取servlet的访问路径
		String sPath = request.getServletPath();
		// 获取参数列表（只能获取get方式，获取参数列表不包含?）  有可能包含&pc=4 目的：把&pc=4干掉
		String qString = request.getQueryString();
		
		// 目的：干掉&pc=4
		if(qString != null && qString.contains("&pc=")){
			// 进行处理
			int index = qString.lastIndexOf("&pc=");
			qString = qString.substring(0, index);
		}
		
		return cPath+sPath+"?"+qString;
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
