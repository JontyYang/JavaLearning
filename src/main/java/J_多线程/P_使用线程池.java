package J_多线程;

import java.util.concurrent.*;

/*
Java语言内置了多线程支持，启动一个新线程非常方便，但是创建线程需要操作系统资源(线程资源、栈空间等），频繁创建和销毁线程需要耗费大量时间；
如果可以复用一组线程：
┌─────┐ execute      ┌──────────────────┐
│   Task1 │───────>│             ThreadPool           │
├─────┤              │┌───────┐┌───────┐│
│   Task2 │              ││     Thread1  ││   Thread2   ││
├─────┤              │└───────┘└───────┘│
│   Task3 │              │┌───────┐┌───────┐│
├─────┤              ││    Thread3  ││   Thread4   ││
│   Task4 │              │└───────┘└───────┘│
├─────┤              └──────────────────┘
│   Task5 │
├─────┤
│   Task6 │
└─────┘
这样，我们就可以把很多小任务让一组线程来执行，而不是一个任务对应一个新线程。这种能接受大量小任务并进行分发处理的就是线程池。
也就是，线程池内部维护了若干个线程，没有任务的时候，这些线程处于等待状态。如果有新任务，就分配一个空闲线程来执行。
如果所有线程都处于忙碌状态，新任务要么放入队列等待，要么增加一个新线程进行处理。
 */
public class P_使用线程池 {
    public static void main(String[] args) {
//        创建固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(4);
        /*
        ExecutorService只是接口，Java标准库还提供了几个固定的常用实现类
        FixedThreadPool：线程数固定的线程池；
        CachedThreadPool：线程数根据任务动态调整的线程池；
        SingleThreadExecutor：仅单线程执行的线程池。
         */
        //提交任务
        for (int i = 0; i < 10; i++) {
            executor.submit(new P_Task("" + i));
        }
        /*
        线程池在程序结束的时候要关闭。使用shutdown()方法关闭线程池的时候，
        它会等待正在执行的任务**先完成，然后再关闭**。shutdownNow()会立刻停止正在执行的任务，
        awaitTermination()则会等待指定的时间让线程池关闭。
         */
        executor.shutdown();

//        创建动态变化线程池
        /*
        如果想把线程池改为动态调整的线程池，由于会动态调整池大小，所以上述任务会一次同时执行。
        但是我们想把其大小限制为4-10个怎么办？
        Executors.newCachedThreadPool()源码
        public static ExecutorService newCachedThreadPool() {
              return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                    60L, TimeUnit.SECONDS,
                                    new SynchronousQueue<Runnable>());
         }
         */
        //因此创建指定动态大小的线程池，可以这样：
        int min = 4;
        int max = 6;
        ExecutorService executorService = new ThreadPoolExecutor(4, 6,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 9; i++) {
            executorService.submit(new P_Task("" + i));
        }
        try {
            //任务结束指定等待时间后，在关闭线程池
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//任务需要实现线程的run方法
class P_Task implements Runnable {
    private final String name;   //因为是final，不用考考虑线程安全

    public P_Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task" + this.name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end task" + this.name);
    }
}
/*
JDK提供了ExecutorService实现了线程池功能：

线程池内部维护一组线程，可以高效执行大量小任务；

Executors提供了静态方法创建不同类型的ExecutorService；

必须调用shutdown()关闭ExecutorService；

ScheduledThreadPool可以定期调度多个任务
 */