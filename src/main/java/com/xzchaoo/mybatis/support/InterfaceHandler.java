package com.xzchaoo.mybatis.support;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface InterfaceHandler {
	Map<Method, MethodEntry> handle(Class<?> interface0);
}
