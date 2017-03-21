package com.xzchaoo.mybatis.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */
public class SimpleInterfaceHandler implements InterfaceHandler {
	public Map<Method, MethodEntry> handle(Class<?> interface0) {
		Method[] methods = interface0.getMethods();
		Map<Method, MethodEntry> map = new HashMap<Method, MethodEntry>(methods.length * 3 / 2);
		for (Method method : methods) {
			map.put(method, createMethodEntry(method));
		}
		return map;
	}
	private static MethodEntry createMethodEntry(Method method) {
		final Class<?>[] parameterTypes = method.getParameterTypes();
		final Annotation[][] annotations = method.getParameterAnnotations();
		List<Integer> indexList = new ArrayList<Integer>(annotations.length);
		for (int i = 0; i < annotations.length; ++i) {
			for (Annotation a : annotations[i]) {
				if (a.annotationType() == SkipWhenEmpty.class) {
					indexList.add(i);
					break;
				}
			}
		}
		if (indexList.size() == 0) {
			return null;
		} else {
			return new MethodEntry(method, parameterTypes, annotations, toIntArray(indexList));
		}
	}
	private static int[] toIntArray(List<Integer> list) {
		int[] ints = new int[list.size()];
		for (int i = 0; i < ints.length; ++i) {
			ints[i] = list.get(i);
		}
		return ints;
	}
}
