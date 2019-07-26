package com.qiaoxg;

import com.qiaoxg.api.IMyLog;
import com.qiaoxg.api.IUser;
import com.qiaoxg.api.impl.MyLogImpl;
import com.qiaoxg.api.impl.UserImpl;
import com.qiaoxg.callback.LogCallback;
import com.qiaoxg.callback.UserCallback;
import com.qiaoxg.util.ProxyUtil;
import com.qiaoxg.util.ProxyUtil2;

public class ProxyMain {

	public static void main(String[] args) throws InterruptedException {

		// ##################################第一种方式####################################

		ProxyUtil.registerService(IMyLog.class, new MyLogImpl());
		ProxyUtil.registerService(IUser.class, new UserImpl());
		ProxyUtil.getService(IMyLog.class).errorLog("this is an debug log");
		ProxyUtil.getService(IUser.class).showAge("1000");
		
		
		ProxyUtil2.getService(IMyLog.class).errorLog("ProxyUtil2");
		ProxyUtil2.getService(IUser.class).showAge("ProxyUtil2");
		
	}

}
