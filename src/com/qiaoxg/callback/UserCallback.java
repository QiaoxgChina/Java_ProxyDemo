package com.qiaoxg.callback;

public abstract class UserCallback {
	public abstract void onOk();
	
	public void dispatch() {
		onOk();
	}
}
