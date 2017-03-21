package com.xzchaoo.mybatis.support;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */
public class SimpleEmptyHandler implements EmptyHandler {
	public boolean isEmpty(MethodEntry me, Object[] args, int index) {
		Object arg = args[index];
		if (arg == null) {
			return true;
		}
		if (arg instanceof Collection && ((Collection) arg).size() == 0) {
			return true;
		}
		if (me.parameterTypes[index].isArray() && Array.getLength(arg) == 0) {
			return true;
		}
		return false;
	}

	/*@Override*/
	public Object defaultValue(Object proxy, Object source, Method method, Object[] args, int causeParamIndex) {
		Class<?> returnType = method.getReturnType();
		if (returnType == void.class) {
			return null;
		} else if (returnType == int.class || returnType == Integer.class) {
			return 0;
		} else if (returnType == long.class || returnType == Long.class) {
			return 0L;
		} else if (returnType == List.class) {
			return Collections.EMPTY_LIST;
		} else if (returnType == Map.class) {
			return Collections.EMPTY_MAP;
		}
		throw new IllegalArgumentException("不支持的类型 " + returnType);
	}
}
