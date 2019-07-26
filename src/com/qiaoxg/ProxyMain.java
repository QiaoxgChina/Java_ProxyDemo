package com.qiaoxg;

import com.qiaoxg.api.IMyLog;
import com.qiaoxg.api.IUser;
import com.qiaoxg.api.impl.MyLogImpl;
import com.qiaoxg.api.impl.UserImpl;
import com.qiaoxg.callback.LogCallback;
import com.qiaoxg.callback.UserCallback;
import com.qiaoxg.util.ProxyUtil;

public class ProxyMain {

	public static void main(String[] args) throws InterruptedException {

		ProxyUtil.registerService(IMyLog.class, new MyLogImpl());
		
		ProxyUtil.registerService(IUser.class, new UserImpl());
		
		for (int i = 0; i < 10; i++) {
			ProxyUtil.getService(IMyLog.class).debugLog("this is an debug log",new LogCallback() {
				
				@Override
				public void onOk() {
					
					System.err.println("================== ok =========================");
					
				}
			});
		}


		ProxyUtil.getService(IUser.class).showName("Qiaoxg",new UserCallback() {
			
			@Override
			public void onOk() {
				// TODO Auto-generated method stub
				System.err.println("================== 222222222 =========================");
			}
		});

	}

}
