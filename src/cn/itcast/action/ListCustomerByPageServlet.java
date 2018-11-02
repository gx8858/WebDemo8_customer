package cn.itcast.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.CustomerService;
import cn.itcast.vo.Customer;
import cn.itcast.vo.PageBean;

/**
 * ��ѯ�������ݣ���ҳ�ģ�
 * @author Administrator
 *
 */
public class ListCustomerByPageServlet extends HttpServlet {

	private static final long serialVersionUID = -9084209486253210123L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 	�����Ŀ�ģ�������������ݣ���ŵ�PageBean���󷵻ص�JSP��ҳ����
		 * 	pageCode���Ѿ��㶨��
		 * 	totalPage��JavaBean��㶨��
		 * 	pageSize���Ѿ��㶨��
		 * 	totalCount	-- dao
		 *  beanList	-- dao
		 */
		// �ӿͻ��˻�ȡ  // ����ǵ�һ�β�ѯ��Ĭ���ǵ�һҳ
		int pageCode = getPageCode(request);
		// pageSizeÿҳ��ʾ�ļ�¼�� 10
		int pageSize = 10;
		
		// ����ҵ���
		CustomerService cs = new CustomerService();
		// ���е����ݶ���װ�ڸö�����
		PageBean<Customer> page = cs.findAllByPage(pageCode, pageSize);
		// ת����ҳ����
		request.setAttribute("page", page);
		// ת��
		request.getRequestDispatcher("/pages/listByPage.jsp").forward(request, response);
	}
	
	/**
	 * �жϣ�����ǵ�һ�η��ʣ���pc=1 ������ǵ�һ�η��ʣ�����pc
	 * @param request
	 * @return
	 */
	public int getPageCode(HttpServletRequest request){
		// ��ȡ��ǰҳ
		String pc = request.getParameter("pc");
		// �����ǰҳ�ǿգ����ǵ�һ�η��ʣ�����1
		if(pc == null || pc.trim().isEmpty()){
			return 1;
		}else{
			// ���ؽ�����ĵ�ǰҳ
			return Integer.parseInt(pc);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}






