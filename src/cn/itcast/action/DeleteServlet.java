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
		String id = request.getParameter("id");
		CustomerService cs = new CustomerService();
		cs.deleteById(id);
		request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
