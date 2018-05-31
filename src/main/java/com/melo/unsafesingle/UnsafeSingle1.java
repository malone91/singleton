package com.melo.unsafesingle;

/**
 * 第一种非线程安全的单例类
 * Created by Ablert
 * on 2018/5/31.
 * @author Ablert
 */
public class UnsafeSingle1 {

    /**
     * 私有单例类构造方法，防止其他类初始化
     */
    private UnsafeSingle1() {
    }

    /**
     * 声明单例对象变量
     */
    private static UnsafeSingle1 single = null;

    /**
     * 获取单例对象
     * 逻辑说明：只是对对象变量是否为空做了判断
     * 缺点：没有考虑当多个线程同时进入到第31行判断的时候，多个线程同时通过了判断条件，都会执行第32行代码，
     * 这样线程获取到的不是同一个对象
     * 所以该单例模式不安全。
     * @return
     */
    public static UnsafeSingle1 getSingle() {
        if (single == null) {
            single = new UnsafeSingle1();
        }
        return single;
    }
}
