package com.viajeros.pe.firebase.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GenericMapper {
    private static final String TAG = GenericMapper.class.getSimpleName();

    public static <O> Map<String, Object> convertMapToObject(O object) throws IllegalAccessException {
        Map<String, Object> objectMap = new HashMap<>();
        Field[] allFields = object.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            Object value = field.get(object);
            objectMap.put(field.getName(), value);
        }
        Log.e(TAG, objectMap.toString());
        return objectMap;
    }
}
