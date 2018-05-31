package com.melo.safesingle;

/**
 * 利用枚举类实现单例模式
 * 但是它并非使用懒加载，单例对象是在枚举类加载的时候进行初始化的。
 *
 * enum有语法糖，JVM会阻止反射获取枚举类的私有构造方法
 * Created by Ablert
 * on 2018/5/31.
 * @author Ablert
 */
public enum SafeSingle3 {
    INSTANCE;
}
