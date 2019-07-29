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

		// ##################################第一种方式####################################
		//需要手动new实体类进行注册
		ProxyUtil.registerService(IMyLog.class, new MyLogImpl());
		ProxyUtil.registerService(IUser.class, new UserImpl());
		ProxyUtil.getService(IMyLog.class).errorLog("this is an debug log");
		ProxyUtil.getService(IUser.class).showAge("1000");
		
		// ##################################第2种方式####################################
		//不需要手动new实体类，但对包名和命名规则要求严格
		ProxyUtil2.getService(IMyLog.class).errorLog("ProxyUtil2");
		ProxyUtil2.getService(IUser.class).showAge("ProxyUtil2");
		
		// ##################################第3种方式####################################
		//不需要手动new，对包名和命名也没要求，但是目前仅支持单实现接口
		ProxyUtil3.getService(IMyLog.class).errorLog("===========ProxyUtil3");
		ProxyUtil3.getService(IUser.class).showAge("===========ProxyUtil3=============");
		
	}

}
