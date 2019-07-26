package com.qiaoxg.util;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.qiaoxg.util.proxy.SecondInvocationHandler;

public class ProxyUtil2 {

	/**
	 * key: interface class values: proxy class
	 */
	private static Map<Class<?>, Object> objMag = new HashMap<Class<?>, Object>();

	public ProxyUtil2() {

	}

	/**
	 * 获取服务
	 * 
	 * @param interfaceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> interfaceClass) {
		if (!interfaceClass.isInterface()) {
			throw new IllegalArgumentException("only accept interface: " + interfaceClass);
		} else {

			synchronized (objMag) {

				Object result = objMag.get(interfaceClass);
				if (result == null) {
					result = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
							new SecondInvocationHandler(interfaceClass));
					objMag.put(interfaceClass, result);
				} else {
					System.out.println(interfaceClass + " not null ");
				}

				return (T) result;
			}

		}
	}

}
