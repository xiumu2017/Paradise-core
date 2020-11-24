package com.paradise.core.common.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Paradise
 */
public class ParadiseBeanUtils {

    public static <T> T copy(Object obj, Class<T> clazz) {
        T o = null;
        try {
            o = clazz.getConstructor().newInstance();
            BeanUtils.copyProperties(obj, o);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return o;
    }
}
