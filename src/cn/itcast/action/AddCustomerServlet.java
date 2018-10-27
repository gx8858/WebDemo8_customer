package cn.itcast.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;

public class AddCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = -2377796610597452155L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.��������
		 * 2.��װ����
		 * 3.��������
		 * 4.��ʾ����
		 */
		// ������������
		request.setCharacterEncoding("UTF-8");
		// ��������
		Map<String, String []> map = request.getParameterMap();
		// ��������
		Customer c = new Customer();
		try {
			BeanUtils.populate(c, map);
			// ����ҵ��㴦������
			CustomerService cs = new CustomerService();
			
			// �����ã�ֻ��һ��ֵ������ ��ţX ������	����,��ţX
			// �ȴ�mpaȡֵ
			String [] loves = map.get("love");
			String love = Arrays.toString(loves);	// [����,��ţX]
			love = love.substring(1, love.length() - 1);	// ����,��ţX
			// ���ð���
			c.setLove(love);
			
			// ��ӿͻ�
			cs.addCustomer(c);
			/*// �������
			response.setContentType("text/html;charset=UTF-8");
			// �������
			response.getWriter().print("<h3>��ӳɹ�</h3>");*/
			
			// �����ӳɹ�����ת���б�ҳ��
			response.sendRedirect(request.getContextPath()+"/listCustomer");
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
