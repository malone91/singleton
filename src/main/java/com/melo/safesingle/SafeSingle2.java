package com.melo.safesingle;

/**
 * 利用静态内部类实现单例模式
 * 所以先类中写一个静态内部类
 *
 * 从外部无法访问静态内部类LazyHolder，
 * 只有当调用Singleton.getInstance方法的时候，才能得到单例对象INSTANCE。
 *
 * INSTANCE对象初始化的时机并不是在单例类Singleton被加载的时候，
 * 而是在调用getInstance方法，使得静态内部类LazyHolder被加载的时候。
 * 因此这种实现方式是利用classloader的加载机制来实现懒加载，并保证构建单例的线程安全。
 * Created by Ablert
 * on 2018/5/31.
 * @author Ablert
 */
public class SafeSingle2 {

    /**
     * 私有化构造方法
     */
    private SafeSingle2() {

    }

    /**
     * 定义静态内部类
     */
    private static class LazyHolder {
        private static final SafeSingle2 INSTANCE = new SafeSingle2();
    }

    /**
     * 获取单例模式
     *
     * 缺点：无法防止反射实例化对象
     * @return
     */
    public static SafeSingle2 getInstance() {
        return LazyHolder.INSTANCE;
    }
}
