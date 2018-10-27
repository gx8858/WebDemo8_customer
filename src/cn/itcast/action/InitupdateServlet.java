package cn.itcast.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;

/**
 * ��ת���޸ĵ�ҳ��
 * @author Administrator
 */
public class InitupdateServlet extends HttpServlet {

	private static final long serialVersionUID = 253323913450676063L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.���ղ���  id
		 * 2.��������  ͨ��id��ѯһ���û�����Ϣ
		 * 3.��ʾ��JSPҳ����
		 */
		String id = request.getParameter("id");
		// ����ҵ���࣬ͨ��id��ѯ�ͻ�����Ϣ
		CustomerService cs = new CustomerService();
		Customer c = cs.findById(id);
		// ����request����
		request.setAttribute("c", c);
		// ת��
		request.getRequestDispatcher("/pages/update.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
