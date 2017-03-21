package com.xzchaoo.mybatis.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/**
 * 因为不能使用高版本的java 所以只好用旧的方法
 * Created by Administrator on 2017/3/20.
 */
public class SkipWhenEmptyInvocationHandler implements InvocationHandler {
	/**
	 * source
	 */
	protected final Object source;
	protected final Class<?> interface0;

	/**
	 * 方法及其对应的需要检查的参数索引
	 */
	protected Map<Method, MethodEntry> methodEntryMap;

	/**
	 * 处理器
	 */
	protected EmptyHandler emptyHandler = new SimpleEmptyHandler();

	protected InterfaceHandler interfaceHandler = new SimpleInterfaceHandler();

	public SkipWhenEmptyInvocationHandler(Object source, Class<?> interface0) {
		this.source = source;
		this.interface0 = interface0;
	}

	public void init() {
		methodEntryMap = Collections.unmodifiableMap(interfaceHandler.handle(interface0));
	}

	/*@Override*/
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MethodEntry me = methodEntryMap.get(method);
		if (me != null) {
			for (int i : me.checkIndices) {
				if (emptyHandler.isEmpty(me, args, i))
					return emptyHandler.defaultValue(proxy, source, method, args, i);
			}
		}
		return method.invoke(source, args);
	}

	public Object getSource() {
		return source;
	}

	public Map<Method, MethodEntry> getMethodEntryMap() {
		return methodEntryMap;
	}

	public Class<?> getInterface0() {
		return interface0;
	}

	public InterfaceHandler getInterfaceHandler() {
		return interfaceHandler;
	}

	public void setInterfaceHandler(InterfaceHandler interfaceHandler) {
		this.interfaceHandler = interfaceHandler;
	}

	public EmptyHandler getEmptyHandler() {
		return emptyHandler;
	}

	public void setEmptyHandler(EmptyHandler emptyHandler) {
		this.emptyHandler = emptyHandler;
	}
}
