package com.qiaoxg.api.impl;

import com.qiaoxg.api.IMyLog;
import com.qiaoxg.callback.LogCallback;

public class MyLogImpl implements IMyLog {
	
//	private MyLogImpl() {}

	@Override
	public void errorLog(String msg) {
		System.err.println("+++++++ ERROR ++++++ " + msg);

	}

	@Override
	public void infoLog(String msg) {
		System.out.println("+++++++ INFO ++++++ " + msg);
	}

	@Override
	public void debugLog(String msg, LogCallback callback) {
		System.out.println("+++++++ DEBUG ++++++ " + msg);
		
		callback.onOk();
	}

}
