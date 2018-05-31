package com.melo.unsafesingle;

/**
 * 第二种非线程安全的单例类
 * 两次判空叫做双重检测机制
 *
 * 针对JVM的指令重排
 * A instance = new A();这行代码会被编译器编译为如下指令
 * 1、memory = allocate();分配对象的内存空间
 * 2、ctorInstance(memory);初始化对象
 * 3、instance = memory；设置instance指向刚分配的内存地址
 * 但是这些指令并非一成不变，它会经过JVM和CPU的优化，指令重排下面的顺序：
 * 1 3 2
 * 当线程A执行完1,3,时，instance对象还未完成初始化，但已经不再指向null。
 * 此时如果线程B抢占到CPU资源，执行 if（instance == null）的结果会是false，
 * 从而返回一个没有初始化完成的instance对象。
 *
 * 那么如何避免这一情况呢？那就进入到safesingle包下，看如何放大招解决指令优化重排带来的安全问题。
 * Created by Ablert
 * on 2018/5/31.
 * @author Ablert
 */
public class UnsafeSingle2 {

    /**
     * 私有构造方法
     */
    private UnsafeSingle2() {
    }

    /**
     * 声明单例对象的变量
     */
    private static UnsafeSingle2 single = null;

    /**
     * 获取单例对象方法
     * 逻辑说明：多个线程在访问时，使用同步锁让线程有序执行
     * 缺陷：如果不了解底层的话，很难想到还会有缺陷造成线程不安全
     * 这就涉及到JVM编译器的指令重排1
     * @return
     */
    public static UnsafeSingle2 getSingle() {
        //双重检测机制
        if (single == null) {
            //多个线程同时在这里堵塞，只要一个线程拿到了锁，然后进行初始化。
            synchronized (UnsafeSingle2.class) {
                //双重检测机制 假设某一个线程已经完成了单例对象的初始化，
                // 其他线程仍然会进入synchronized区执行锁内的代码，所以仍需要进行非空条件判断
                if (single == null) {
                    single = new UnsafeSingle2();
                }
            }
        }
        return single;
    }
}
