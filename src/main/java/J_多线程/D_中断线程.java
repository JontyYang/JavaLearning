package J_多线程;
/*
小结
对目标线程调用interrupt()方法可以请求中断一个线程，目标线程通过检测isInterrupted()标志获取自身是否已中断。
如果目标线程处于等待状态，该线程会捕获到InterruptedException；

目标线程检测到isInterrupted()为true或者捕获了InterruptedException都应该立刻结束自身线程；

通过标志位判断需要正确使用volatile关键字；

volatile关键字解决了共享变量在线程间的可见性问题。
 */
public class D_中断线程 {
    public static void main(String[] args) throws InterruptedException {
        //中断线程方法一：
        /*
        main线程通过调用t.interrupt()方法中断t线程，但是要注意，interrupt()方法仅仅向t线程发出了“中断请求”，
        至于t线程是否能立刻响应，要看具体代码。而t线程的while循环会检测isInterrupted()，
        所以上述代码能正确响应interrupt()请求，使得自身立刻结束运行run()方法
         */
//        Thread thread1 = new D_MyThread1();
//        thread1.start();
//        Thread.sleep(18);   //让主线程暂停3毫秒，使thread1打印一部分
//        thread1.interrupt();   //中断thread1线程
//        thread1.join();   //等待thread1结束（也是在确认thread1结束）
//        System.out.println("end");

        //中断线程方法二;
        /*
        当目标线程因为另一个线程.join从而陷入等待状态时，如果对该目标线程进行中断请求，join方法会抛出InterruptedException异常，一般
        我们应该立即结束该线程
         */
//        Thread thread2 = new D_MyThread2();
//        thread2.start();
//        Thread.sleep(1000);
//        thread2.interrupt();        //此时Thread2还在因为Hello.Join陷入等待状态，当对其进行中断时，会抛出异常，终止该进程。
//        thread2.join();
//        System.out.println("end");

        //中断线程方法三：
        /*
        另一个常用的中断线程的方法是设置标志位。我们通常会用一个running标志位来标识线程是否应该继续运行，
        在外部线程中，通过把HelloThread.running置为false，就可以让线程结束
         */
        Thread thread3 = new D_MyThread3();
        thread3.start();
        Thread.sleep(1);
        ((D_MyThread3) thread3).running = false;  //标志位设置为false



    }
}
//方法一
class D_MyThread1 extends Thread {
    public void run() {
        int n = 0;
        while (! isInterrupted()) {
            n ++;
            System.out.println(n + "E_Thread1");
        }
    }
}

//方法二
class D_MyThread2 extends Thread {
    public void run() {
        Thread threadHello = new D_HelloThread();
        threadHello.start();
        try {
            System.out.println("D_MyThread2 is running!!!");
            threadHello.join();    //等待hello线程结束，该线程处于等待状态，当该线程没被终止时，会一直在等待Hello线程
        } catch (InterruptedException e) {
            System.out.println("D_MyThread2 is Interupted!!!");
        }
        //在该线程结束前，对hello线程也应进行中断，否则，hello线程会一直继续运行，且JVM不会退出
        threadHello.interrupt();
    }
}

class D_HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (! isInterrupted()) {
            n ++;
            System.out.println(n + "D_HelloThread");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("D_HelloThread end!!!");
    }
}


//方法三
class D_MyThread3 extends Thread {
    //设置是否终止线程标志位,该标志位为线程间volatile共享变量
    /*
    为什么要对线程间共享的变量用关键字volatile声明？这涉及到Java的内存模型。在Java虚拟机中，
    变量的值保存在主内存中，但是，当线程访问变量时，它会先获取一个副本，并保存在自己的工作内存中。
    如果线程修改了变量的值，虚拟机会在某个时刻把修改后的值回写到主内存，但是，这个时间是不确定的！
                            ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
                                              Main Memory
                                    │                               │
                           ┌───────┐┌───────┐┌───────┐
                           │  │ var A   ││    var B_TCP编程_Client    ││     var C   │
                           └───────┘└───────┘└───────┘
                           │      │ ▲                          │ ▲     │
                           ─ ─ ─│─│─ ─ ─ ─ ─ ─--- ─ ─│─│─ ─ ─
                                   │ │                          │ │
                          ┌ ─ ─ ┼ ┼ ─ ─ ┐         ┌ ─ ─ ┼ ┼ ─ ─ ┐
                                   ▼ │                          ▼ │
                          │  ┌───────┐  │   │  ┌───────┐  │
                                  │ var A │             │    var C   │
                          │  └───────┘  │   │  └───────┘  │
                                  Thread 1                     Thread 2
                           └ ─ ─ ─ ─ ─ ─ ┘      └ ─ ─ ─ ─ ─ ─ ┘
这会导致如果一个线程更新了某个变量，另一个线程读取的值可能还是更新前的。例如，主内存的变量a = true，线程1执行a = false时，
它在此刻仅仅是把变量a的副本变成了false，主内存的变量a还是true，在JVM把修改后的a回写到主内存之前，其他线程读取到的a的值仍然是true，
这就造成了多线程之间共享的变量不一致。

因此，volatile关键字的目的是告诉虚拟机：
每次访问变量时，总是获取主内存的最新值；
每次修改变量后，立刻回写到主内存。
volatile关键字解决的是可见性问题：当一个线程修改了某个共享变量的值，其他线程能够立刻看到修改后的值。

如果我们去掉volatile关键字，运行上述程序，发现效果和带volatile差不多，这是因为在x86的架构下，
JVM回写主内存的速度非常快，但是，换成ARM的架构，就会有显著的延迟。
     */
    public volatile boolean running = true;

    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + "D_MyThread3");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("D_HelloThread end!!!");
    }
}
