package utils.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/*
* 反射工具类
* */
public class ReflectUtils {

    /**
     * 根据类反射出对象
     * @param clazz 要创建的对象的类
     * @param <T>  要创建对象的类型
     * @return
     */
    public static  <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个类的所有public方法
     * @param clazz 需要调用方法的对象
     * @return
     */

    public static Method[] getPublicMethod(Class clazz){
        Method[] methods=clazz.getDeclaredMethods();
        List<Method> pMethod=new ArrayList<>();
        for(Method m:methods){
            if (Modifier.isPublic(m.getModifiers())){
                pMethod.add(m);
            }
        }
        return pMethod.toArray(new Method[0]);
    }


    /**
     *
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param parameter 被调用方法的参数
     * @return 返回值
     */
    public static Object invoke(Object obj,Method method,Object[] parameter) {
        try {
            return method.invoke(obj,parameter);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
