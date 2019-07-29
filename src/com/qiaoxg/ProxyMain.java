package com.qiaoxg;

import com.qiaoxg.api.IMyLog;
import com.qiaoxg.api.IUser;
import com.qiaoxg.api.impl.MyLogImpl;
import com.qiaoxg.api.impl.UserImpl;
import com.qiaoxg.callback.LogCallback;
import com.qiaoxg.callback.UserCallback;
import com.qiaoxg.util.ProxyUtil;
import com.qiaoxg.util.ProxyUtil2;
import com.qiaoxg.util.ProxyUtil3;

public class ProxyMain {

	public static void main(String[] args) throws InterruptedException {

		// ##################################��һ�ַ�ʽ####################################
		//��Ҫ�ֶ�newʵ�������ע��
		ProxyUtil.registerService(IMyLog.class, new MyLogImpl());
		ProxyUtil.registerService(IUser.class, new UserImpl());
		ProxyUtil.getService(IMyLog.class).errorLog("this is an debug log");
		ProxyUtil.getService(IUser.class).showAge("1000");
		
		// ##################################��2�ַ�ʽ####################################
		//����Ҫ�ֶ�newʵ���࣬���԰�������������Ҫ���ϸ�
		ProxyUtil2.getService(IMyLog.class).errorLog("ProxyUtil2");
		ProxyUtil2.getService(IUser.class).showAge("ProxyUtil2");
		
		// ##################################��3�ַ�ʽ####################################
		//����Ҫ�ֶ�new���԰���������ҲûҪ�󣬵���Ŀǰ��֧�ֵ�ʵ�ֽӿ�
		ProxyUtil3.getService(IMyLog.class).errorLog("===========ProxyUtil3");
		ProxyUtil3.getService(IUser.class).showAge("===========ProxyUtil3=============");
		
	}

}
