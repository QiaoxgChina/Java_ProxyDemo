package com.qiaoxg.util;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.qiaoxg.util.proxy.MyInvocationHandler;

public class ProxyUtil {

	/**
	 * key: interface class values: proxy class
	 */
	private static Map<Class<?>, Object> objMag = new HashMap<Class<?>, Object>();

	/**
	 * key: interface class values: interface Instances class
	 */
	private static Map<Class<?>, Object> interfaceObjMap = new HashMap<Class<?>, Object>();

	public ProxyUtil() {

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

			Object serviceObject = interfaceObjMap.get(interfaceClass);

			if (serviceObject == null) {
				throw new IllegalArgumentException("this service not register: " + interfaceClass);
			}

			synchronized (objMag) {

				Object result = objMag.get(interfaceClass);
				if (result == null) {
					result = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
							new MyInvocationHandler(serviceObject));
					objMag.put(interfaceClass, result);
				} else {
					System.out.println(interfaceClass + " not null ");
				}

				return (T) result;
			}

		}
	}

	/**
	 * 注册服务
	 * 
	 * @param inter
	 * @param obj
	 */
	public static void registerService(Class<?> inter, Object obj) {
		String interNameString = inter.getSimpleName();
		String objNameString = obj.getClass().getSimpleName();
		
		System.out.println(interNameString + " =========== " + objNameString);
		
		interfaceObjMap.put(inter, obj);
	}

}
