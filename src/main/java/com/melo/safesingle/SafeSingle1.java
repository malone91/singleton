package com.melo.safesingle;

/**
 * 使用volatile关键字防止指令重排
 *
 * 如此在线程B看来，instance对象的引用要么指向null，
 * 要么指向一个初始化完毕的Instance，而不会出现某个中间态，保证了安全。
 *
 * Created by Ablert
 * on 2018/5/31.
 * @author Ablert
 */
public class SafeSingle1 {

    /**
     * 私有化构造方法，防止被其他类任意初始化
     */
    private SafeSingle1() {

    }

    /**
     * 定义单例对象变量
     * volatile关键字阻止了变量访问前后的指令重排，保证了指令执行顺序。
     */
    private static volatile SafeSingle1 single = null;

    /***
     * 获取单例对象
     * @return
     */
    public static SafeSingle1 getInstance() {
        if (single == null) {
            synchronized (SafeSingle1.class) {
                if (single == null) {
                    single = new SafeSingle1();
                }
            }
        }
        return single;
    }
}
