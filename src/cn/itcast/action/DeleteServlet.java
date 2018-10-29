package cn.itcast.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;

public class DeleteServlet extends HttpServlet {

	/**
	 * ����idɾ��һ���ͻ�����
	 */
	private static final long serialVersionUID = -2612699672600029880L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * ����idֵ
		 * �������� ����ҵ����
		 * ��ʾ����
		 */
		String id = request.getParameter("id");
		// ����ҵ����
		CustomerService cs = new CustomerService();
		cs.deleteById(id);
		// �ض��򵽲�ѯ�����û��б��Servlet����
		response.sendRedirect(request.getContextPath()+"/listCustomer");
		
		// ������ת��
//		 request.getRequestDispatcher("/listCustomer").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
