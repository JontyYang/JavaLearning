package J_多线程;

import java.time.LocalTime;

/*
在Java中有两类线程：守护线程和用户线程（守护线程就是JVM中所有非守护线程的保姆）
只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，JVM一同结束工作。因为没有了用户
线程，守护线程也就没有了运行的必要，也就结束了。但是，注意：守护进程的存在与否不影响JVM的结束。

Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)，它就是一个很称职的守护者。

守护线程是为其他线程服务的线程；

所有非守护线程都执行完毕后，虚拟机退出；

守护线程不能持有需要关闭的资源（如打开文件等）。
 */
public class E_守护线程 {
    public static void main(String[] args) {
        /*
        Java程序入口就是由JVM启动main线程，main线程又可以启动其他线程。当所有线程结束后，JVM退出，进程结束。
        如果有程序不能结束，JVM就不会退出。
        但是，有一种线程的目的就是无限循环不需要退出，因此要使用守护线程。
        守护线程是指为其他线程服务的线程。在JVM中，所有非守护线程都执行完毕后，无论有没有守护线程，虚拟机都会自动退出。
        JVM结束时，不关心守护进程的结束与否，但一般守护线程也会结束。

        实际上守护线程就是为所有非守护线程服务的，当所有非守护线程结束时，JVM退出。
         */
        Thread thread1 = new DaemonThread();
        //在守护线程中，编写代码要注意：守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，
        // 守护线程没有任何机会来关闭文件，这会导致数据丢失。
        thread1.setDaemon(true);
        thread1.start();

//        Thread thread2 = new E_Thread1();
//        thread2.start();
    }
}

class DaemonThread extends Thread {
    public void run() {
        while (true) {
            System.out.println(LocalTime.now());
        }
    }
}

class E_Thread1 extends Thread {
    public void run() {
        int n = 50;
        while (n > 0) {
            System.out.println("E_Thread1" + n);
            n--;
        }
        System.out.println("E_Thread1 end!!!");
    }
}