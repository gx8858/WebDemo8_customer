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
 * ��ѯ�����û�����Ϣ
 * @author Administrator
 *
 */
public class ListCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = -825089887570284957L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����
		CustomerService cs = new CustomerService();
		// ��ѯ���е��û���Ϣ
		List<Customer> cList = cs.findAll();
		// ��request���д���ֵ
		request.setAttribute("cList", cList);
		// ת��
		request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
