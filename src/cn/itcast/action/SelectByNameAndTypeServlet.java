package cn.itcast.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;

public class SelectByNameAndTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 6795411891797622045L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * 1.接收数据
		 * 2.处理数据
		 * 3.显示到页面上
		 */
		// 接收数据
		request.setCharacterEncoding("UTF-8");
		// 获取数据
		String username = request.getParameter("username");
		String type = request.getParameter("type");
		
		// 处理数据
		CustomerService cs = new CustomerService();
		List<Customer> cList = cs.findAllByNameAndType(username, type);
		
		// 存到域中
		request.setAttribute("cList", cList);
		
		// 转发
		request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
