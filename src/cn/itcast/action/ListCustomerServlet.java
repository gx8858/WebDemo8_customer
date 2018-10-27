package cn.itcast.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;

/**
 * 查询所有用户的信息
 * @author Administrator
 *
 */
public class ListCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = -825089887570284957L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用
		CustomerService cs = new CustomerService();
		// 查询所有的用户信息
		List<Customer> cList = cs.findAll();
		// 向request域中存入值
		request.setAttribute("cList", cList);
		// 转发
		request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
