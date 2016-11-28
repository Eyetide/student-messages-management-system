package com.Lauguobin.www.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * 把map中的数据传给一个对象最终得到一个封装的对象
 */
public class BeanUtil {	
	/**
	 * 
	 * @param bean 需要封装的vo
	 * @param map 需要转换的map
	 * @return 已经封装好数据的vo（object）
	 */
	public static  Object MapToBean(Object bean, Map<?, ?> map) {
		Map<String, Method> methods = new HashMap<String, Method>();
		Method m[] = bean.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			Method method = m[i];
			String methodName = method.getName().toUpperCase();
			methods.put(methodName, method);
		}

		Iterator<?> it = null;
		String key = "";
		it = map.keySet().iterator();
		while (it.hasNext()) 
		{
			key = (String) it.next();
			String name = "GET" + key.toUpperCase();
			if (methods.containsKey(name)) 
			{
				Method setMethod = (Method) methods.get("SET" + key.toUpperCase());
				try 
				{
					if(setMethod!=null)
					{
						Object[] obj=null;
						obj=new Object[1];
						obj[0]=map.get(key);
						setMethod.invoke(bean, obj);
					}
					else
					{
						continue;
					}
				} catch (IllegalAccessException e) 
				{
					e.printStackTrace();
				} catch (InvocationTargetException e) 
				{
					e.printStackTrace();
				}

			}
		}
		return bean;
	}
}
