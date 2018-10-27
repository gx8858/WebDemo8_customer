package cn.itcast.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;

/**
 * 跳转到修改的页面
 * @author Administrator
 */
public class InitupdateServlet extends HttpServlet {

	private static final long serialVersionUID = 253323913450676063L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.接收参数  id
		 * 2.处理数据  通过id查询一个用户的信息
		 * 3.显示到JSP页面上
		 */
		String id = request.getParameter("id");
		// 调用业务类，通过id查询客户的信息
		CustomerService cs = new CustomerService();
		Customer c = cs.findById(id);
		// 放入request域中
		request.setAttribute("c", c);
		// 转发
		request.getRequestDispatcher("/pages/update.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
