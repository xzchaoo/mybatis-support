package com.xzchaoo.mybatis.support;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface EmptyHandler {
	boolean isEmpty(MethodEntry me, Object[] args, int index);

	Object defaultValue(Object proxy, Object source, Method method, Object[] args, int causeParamIndex);
}
