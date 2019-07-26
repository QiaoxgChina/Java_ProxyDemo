package com.qiaoxg.api;

import com.qiaoxg.callback.UserCallback;

public interface IUser {
	
	void showName(String name,UserCallback callback);
	
	void showAge(String age);

}
