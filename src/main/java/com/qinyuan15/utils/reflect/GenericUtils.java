package com.qinyuan15.utils.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {
    private GenericUtils() {
    }

    /**
     * Get the real type of generic argument of certain class
     * <p>
     * For example, if we define a class:
     * <code>class TestClass implements TestInterface &lt;StringBuilder&gt; {...}</code>.
     * Then the read type fo generic argument of TestClass is StringBuilder
     * </p>
     *
     * @param clazz class to find the read type
     * @return read type of clazz
     */
    public static Class<?> getRealTypeOfGenericArgument(Class<?> clazz) {
        final ParameterizedType genType = getSuperParameterizedType(clazz);
        if (genType == null) {
            throw new IllegalArgumentException("class '" + clazz + "' has no generic argument!");
        }

        Type[] params = genType.getActualTypeArguments();
        Type firstParam = params[0];
        if (firstParam instanceof GenericArrayType) {
            final String elementClassName = firstParam.toString().replace("[]", "");
            try {
                return Class.forName("[L" + elementClassName + ";");
            } catch (java.lang.ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return (Class) firstParam;
        }
    }

    private static ParameterizedType getSuperParameterizedType(Class<?> clazz) {
        Type superClass = clazz.getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            return (ParameterizedType) superClass;
        }

        Type[] superInterfaces = clazz.getGenericInterfaces();
        for (Type type : superInterfaces) {
            if (type instanceof ParameterizedType) {
                return (ParameterizedType) type;
            }
        }
        return null;
    }
}
