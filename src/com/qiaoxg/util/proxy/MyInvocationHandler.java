package com.qiaoxg.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object originalObject;

	private long startTime;

	public MyInvocationHandler(Object obj) {
		this.originalObject = obj;
	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		before(arg1);
		Object invoke = arg1.invoke(originalObject, arg2);
		after(arg1);
		return invoke;
	}

	private void before(Method method) {
		startTime = System.currentTimeMillis();
		System.out.println(
				"\n++++++++" + originalObject.getClass().getSimpleName() + "/" + method.getName() + "++++++++��ʼִ�У� ");
	}

	private void after(Method method) {
		System.out.println("++++++++" + method.getName() + "++++++++����ִ�У� ");
		System.out.println("ִ�У�" + method.getName() + " ��ʱ��ms���� " + (System.currentTimeMillis() - startTime) + "\n");
	}

}
