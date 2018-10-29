package cn.itcast.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;

public class DeleteServlet extends HttpServlet {

	/**
	 * 根据id删除一条客户数据
	 */
	private static final long serialVersionUID = -2612699672600029880L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * 接收id值
		 * 处理数据 调用业务类
		 * 显示数据
		 */
		String id = request.getParameter("id");
		// 调用业务类
		CustomerService cs = new CustomerService();
		cs.deleteById(id);
		// 重定向到查询所有用户列表的Servlet程序
		response.sendRedirect(request.getContextPath()+"/listCustomer");
		
		// 或者用转发
//		 request.getRequestDispatcher("/listCustomer").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
