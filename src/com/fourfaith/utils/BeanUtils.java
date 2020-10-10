package com.fourfaith.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * 自行封装的一个BeanUtils
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
	
	/**
	 * 获取当前类声明的private/protected变量
	 */
	static public Object getPrivateProperty(Object object, String propertyName)
			throws IllegalAccessException, NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		return field.get(object);
	}

	/**
	 * 设置当前类声明的private/protected变量
	 */
	static public void setPrivateProperty(Object object, String propertyName,
			Object newValue) throws IllegalAccessException,
			NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		field.set(object, newValue);

	}

	/**
	 * 调用当前类声明的private/protected函数
	 */
	@SuppressWarnings("rawtypes")
	static public Object invokePrivateMethod(Object object, String methodName,
			Object... params) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Method method = object.getClass().getDeclaredMethod(methodName, types);
		method.setAccessible(true);
		return method.invoke(object, params);
	}

	/**
	 * 调用当前类声明的private/protected函数
	 */
	@SuppressWarnings("rawtypes")
	static public Object invokeSuperPrivateMethod(Object object,
			String methodName, Object... params) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Method method = object.getClass().getSuperclass()
				.getDeclaredMethod(methodName, types);
		method.setAccessible(true);
		return method.invoke(object, params);
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array是否不为空
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isNotEmpty(Long o) {
		return !isEmpty(o);
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array,Long是否为空
	 * @param o java.lang.Object.
	 * @return boolean.
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (((Object[]) o).length == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else if (o instanceof Long) {
			if (((Long) o) == null) {
				return true;
			}
		} else if (o instanceof Short) {
			if (((Short) o) == null) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 判定类是否继承自父类
	 * @param cls 子类
	 * @param parentClass 父类
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isInherit(Class cls, Class parentClass) {
		return parentClass.isAssignableFrom(cls);
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			org.springframework.beans.BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	private static void handleReflectionException(Exception e) {
		ReflectionUtils.handleReflectionException(e);
	}

	/**
	 * 对象属性拷贝(将source对象属性拷贝至target对象) null不拷贝
	 * @param source 拷贝对象
	 * @param target 目标对象
	 * @param copyNull 是否拷贝空属性，当为false时，不拷贝
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyPropertiesExcludeNull(Object source, Object target)
			throws IllegalAccessException, InvocationTargetException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(
						source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass()
								.getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
						if (value != null) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod
									.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}
	
}