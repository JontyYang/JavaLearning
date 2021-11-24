package J_多线程;

/*
Java语言内置了多线程支持。当Java程序启动的时候，实际上是启动了一个JVM进程，然后，JVM启动主线程来执行main()方法。在main()方法中，我们又可以启动其他线程。

Java用Thread对象表示一个线程，通过调用start()启动一个新线程；
一个线程对象只能调用一次start()方法；
线程的执行代码写在run()方法中；
线程调度由操作系统决定，程序本身无法决定调度顺序；
Thread.sleep()可以把当前线程暂停一段时间。
 */
public class B_创建新线程 {

    public static void main(String[] args) {
        //希望新线程能执行指定的代码，有以下几种方法：
        /*
        方法一：从Thread派生出一个自定义类，然后覆写run()方法
         */
        Thread thread1 = new B_MyThread();
        thread1.start();
        /*
        方法二：创建Thread实例时，传入一个Runnable实例
         */
        Thread thread2 = new Thread(new B_MyRunnable());
        thread2.start();
        //注意到start()方法会在内部自动调用实例的run()方法。

        /**
         * 使用线程的打印语句和直接在main（）方法中执行的区别
         *
         * 1.main线程肯定是先打印main start,再打印main end
         * 2.thread3线程肯定是先打印thread start，在打印thread end
         * 但是，除了先打印Main start外，之后main线程会创建thread3线程，该线程开始后和主线程竞争资源，
         * 所以main end打印在thread start、thread end之前，之间还是之后都不确定。
         * 因为从thread3线程开始运行之后，两个线程就开始同时并发运行了，且有操作系统调度，无法确定顺序。
         */
        System.out.println("Main start!!!");
        Thread thread3 = new Thread() {
            //thread的匿名类
            @Override
            public void run() {
                System.out.println("Thread start!!");
                System.out.println("Thread end!!");
            }
        };
        thread3.start();
        try {
            //sleep()传入的参数是毫秒，让所在线程暂停几毫秒。
            Thread.sleep(10);
        } catch (InterruptedException e) {}
        System.out.println("Main end!!!");

        /*
        线程的优先级
        Thread.setPriority(int n) // 1~10, 默认值5
        优先级高的线程被操作系统调度的优先级较高，操作系统对高优先级线程可能调度更频繁，但我们决不能通过设置优先级来确保高优先级的线程一定会先执行。
         */

    }



}

class B_MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Start B_MyThread !!!");
    }


}

class B_MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Start MyRunable ???");
    }
}