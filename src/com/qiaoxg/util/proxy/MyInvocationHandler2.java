package com.qiaoxg.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.qiaoxg.util.ClassUtil;

public class MyInvocationHandler2 implements InvocationHandler {

	private Object originalObject;

	private Map<Class<?>, Object> interObjMap = new HashMap<Class<?>, Object>();

	public MyInvocationHandler2(Class<?> clazz) {
		try {
			this.originalObject = interObjMap.get(clazz);

			if (this.originalObject == null) {
				this.originalObject = ClassUtil.getClassByInterface(clazz);
				
				interObjMap.put(clazz, originalObject);
				
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

}