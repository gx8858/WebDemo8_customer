package cn.itcast.utils;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtil {
	
	/**
	 * ��ȡΨһ���ַ���
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	@Test
	public void run(){
		String id = UUID.randomUUID().toString().replace("-", "");
		System.out.println(id);
	}

}
