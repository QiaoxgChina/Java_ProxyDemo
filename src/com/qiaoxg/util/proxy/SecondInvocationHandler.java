package com.qiaoxg.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SecondInvocationHandler implements InvocationHandler {

	private Object originalObject;

	private Map<Class<?>, Object> interObjMap = new HashMap<Class<?>, Object>();

	public SecondInvocationHandler(Class<?> obj) {
		try {
			this.originalObject = interObjMap.get(obj);

			if (this.originalObject == null) {
				this.originalObject = getOriginalObj(obj);
				
				interObjMap.put(obj, originalObject);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {

		Object invoke = arg1.invoke(originalObject, arg2);
		return invoke;
	}

	private Object getOriginalObj(Class<?> interClass) throws Exception {
		String name = interClass.getName();
		String simpleNameString = interClass.getSimpleName();

		String originalClassName = name.substring(0, name.lastIndexOf(".")) + ".impl."
				+ simpleNameString.substring(1, simpleNameString.length()) + "Impl";
		System.out.println(originalClassName);
//		System.exit(0);

		Class<?> cls = Class.forName(originalClassName);
		return cls.newInstance();
	}
}