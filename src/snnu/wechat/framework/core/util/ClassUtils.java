/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {

	public static Object createInstance(Class cls) {
		if (cls == null) {
			throw new IllegalArgumentException("target class can't be null");
		}

		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			throw new CreateInstanceFailedException(e);
		} catch (IllegalAccessException e) {
			throw new CreateInstanceFailedException(e);
		}
	}

	public static Set resolveAllConstantValue(Class target) {
		if (target == null) {
			throw new IllegalArgumentException("target class can't be null");
		}

		Set set = new HashSet();
		Field[] fields = target.getDeclaredFields();
		for (Field f : fields) {
			int modify = f.getModifiers();
			// public final static
			if (Modifier.isPublic(modify) && Modifier.isFinal(modify)
					&& Modifier.isStatic(modify)) {
				try {
					set.add(f.get(null));
				} catch (IllegalArgumentException e) {

				} catch (IllegalAccessException e) {

				}
			}
		}
		return set;
	}

	public static boolean isTypeOf(Object obj, Class type) {
		return type.isAssignableFrom(obj.getClass());
	}
}
