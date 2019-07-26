package com.qiaoxg.api.impl;

import com.qiaoxg.api.IUser;
import com.qiaoxg.callback.UserCallback;

public class UserImpl implements IUser {

	@Override
	public void showName(String name,UserCallback callback) {
		System.out.println("My name is " + name);
		
		callback.dispatch();
	}

}
