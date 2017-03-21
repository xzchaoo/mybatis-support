package com.xzchaoo.mybatis.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 虽然数组设置成了 final 但用户应该自己保证不要修改数组的内容
 * Created by Administrator on 2017/3/21.
 */
public class MethodEntry {
	public final Method method;
	public final Class<?>[] parameterTypes;
	public final int[] checkIndices;
	public final Annotation[][] annotations;

	public MethodEntry(Method method, Class<?>[] parameterTypes, Annotation[][] annotations, int[] checkIndices) {
		this.method = method;
		this.parameterTypes = parameterTypes;
		this.annotations = annotations;
		this.checkIndices = checkIndices;
	}
}
