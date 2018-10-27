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
		 * 1.接收数据
		 * 2.封装数据
		 * 3.处理数据
		 * 4.显示数据
		 */
		// 设置中文乱码
		request.setCharacterEncoding("UTF-8");
		// 接收数据
		Map<String, String []> map = request.getParameterMap();
		// 创建对象
		Customer c = new Customer();
		try {
			BeanUtils.populate(c, map);
			// 调用业务层处理数据
			CustomerService cs = new CustomerService();
			
			// 处理爱好，只有一个值，骑马 吹牛X ，想变成	骑马,吹牛X
			// 先从mpa取值
			String [] loves = map.get("love");
			String love = Arrays.toString(loves);	// [骑马,吹牛X]
			love = love.substring(1, love.length() - 1);	// 骑马,吹牛X
			// 设置爱好
			c.setLove(love);
			
			// 添加客户
			cs.addCustomer(c);
			/*// 输出中文
			response.setContentType("text/html;charset=UTF-8");
			// 输出中文
			response.getWriter().print("<h3>添加成功</h3>");*/
			
			// 如果添加成功，跳转到列表页面
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
