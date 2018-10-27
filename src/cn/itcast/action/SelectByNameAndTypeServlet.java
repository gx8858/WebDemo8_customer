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
		 * 1.��������
		 * 2.��������
		 * 3.��ʾ��ҳ����
		 */
		// ��������
		request.setCharacterEncoding("UTF-8");
		// ��ȡ����
		String username = request.getParameter("username");
		String type = request.getParameter("type");
		
		// ��������
		CustomerService cs = new CustomerService();
		List<Customer> cList = cs.findAllByNameAndType(username, type);
		
		// �浽����
		request.setAttribute("cList", cList);
		
		// ת��
		request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
