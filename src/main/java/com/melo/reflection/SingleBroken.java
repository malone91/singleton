package com.melo.reflection;

import com.melo.safesingle.SafeSingle2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 利用反射打破单例
 * Created by Ablert
 * on 2018/5/31.
 * @author Ablert
 */
public class SingleBroken {
    public static void main(String[] args) {
        try {
            //获取构造器
            Constructor<SafeSingle2> constructor = SafeSingle2.class.getDeclaredConstructor();
            //设置为可访问
            constructor.setAccessible(true);
            //构造两个不同的对象
            SafeSingle2 instance1 = constructor.newInstance();
            SafeSingle2 instance2 = constructor.newInstance();
            //false 或者使用equals方法比较
            System.out.println(instance1 == instance2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
