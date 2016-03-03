package com.cuit.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 反射工具
 * @author Work
 */
public class ReflectUtil {
    private static final Log logger = LogFactory.getLog(ReflectUtil.class);

    /**
     * 利用反射获取指定对象的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    /**
     * 反射设置field的值
     * @param target，被设置的对象
     * @param fname，对象field的名称
     * @param ftype，field的类型
     * @param fvalue，field被设置的value值
     */
    public static void setFieldValue(Object target, String fname, Class ftype, Object fvalue) {
        if (target == null || fname == null || "".equals(fname) || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
            return;
        }
        Class classInfo = target.getClass();
        try {
            Method method = classInfo.getDeclaredMethod("set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1), ftype);
            if (!Modifier.isPublic(method.getModifiers())) {
                method.setAccessible(true);
            }
            method.invoke(target, fvalue);

        } catch (Exception me) {
            // me.printStackTrace() ;
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
            try {
                Field field = classInfo.getDeclaredField(fname);
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                field.set(target, fvalue);
            } catch (Exception fe) {
                if (logger.isDebugEnabled()) {
                    logger.debug(fe);
                }
            }
        }
    }
}
