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
 * ��ҳ��ѯ������������
 * @author Administrator
 *
 */
public class ListConditionByPageServlet extends HttpServlet {

	private static final long serialVersionUID = -4084927848246923604L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * 1.��ȡ����
		 * 2.����PageBeanװ����
		 * 	* pageCode	��ҳ���ȡ�� 
		 * 	* pageSize	�Լ��涨
		 */
		// ����post��������
//		 request.setCharacterEncoding("UTF-8");
		// ���get��ʽ�������������
		// ��������
		String username = request.getParameter("username");
		if(username != null && !username.trim().isEmpty()){
			// ���get��������
			username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(username);
		}
		String type = request.getParameter("type");
		if(type != null && !type.trim().isEmpty()){
			// ���get��������
			type = new String(type.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(type);
		}
		
		
		// ·��Ϊ��/customer/listConditionByPage?username=&type=%E9%92%BB%E7%9F%B3%E4%BC%9A%E5%91%98   -- �����ӵ�����
		// ·��Ϊ��/customer/listConditionByPage?username=&type=%E9%92%BB%E7%9F%B3%E4%BC%9A%E5%91%98  -- �����������
		String path = getPath(request);
		System.out.println("·��Ϊ��"+path);
		
		// ����ҵ���
		int pageCode = getPageCode(request);
		int pageSize = 10;
		
		CustomerService cs = new CustomerService();
		PageBean<Customer> page = cs.findAllByConditionPage(username, type, pageCode, pageSize);
		
		// ���ò�ѯ������url
		page.setUrl(path);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/listConditionByPage.jsp").forward(request, response);
		
	}
	
	/**
	 * Ŀ�ģ������ȡ�����������п��ܣ�/customer/listConditionByPage?username=����&type=�ƽ��Ա&pc=3
	 */
	public String getPath(HttpServletRequest request){
		// ��ȡ�ͻ�������Ϣ
		// ��ȡ��Ŀ����
		String cPath = request.getContextPath();
		// ��ȡservlet�ķ���·��
		String sPath = request.getServletPath();
		// ��ȡ�����б�ֻ�ܻ�ȡget��ʽ����ȡ�����б�����?��  �п��ܰ���&pc=4 Ŀ�ģ���&pc=4�ɵ�
		String qString = request.getQueryString();
		
		// Ŀ�ģ��ɵ�&pc=4
		if(qString != null && qString.contains("&pc=")){
			// ���д���
			int index = qString.lastIndexOf("&pc=");
			qString = qString.substring(0, index);
		}
		
		return cPath+sPath+"?"+qString;
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
