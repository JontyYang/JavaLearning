package J_多线程;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
在Java程序中，synchronized解决了多线程之间竞争的问题，例如，多个线程对同一共享变量的写操作。
但是，synchronized并没有解决多线程之间协调的问题。
多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务。
要始终在while循环中wait()，并且每次被唤醒后拿到this锁就必须再次判断：
当线程在wait时出现异常（比如执行interrupt），则执行catch，结束线程。

wait和notify用于多线程协调运行：

在synchronized内部可以调用wait()使线程进入等待状态；

必须在已获得的锁对象上调用wait()方法；

在synchronized内部可以调用notify()或notifyAll()唤醒其他等待线程；

必须在已获得的锁对象上调用notify()或notifyAll()方法；

已唤醒的线程还需要重新获得锁后才能"继续"执行
 */
public class I_使用wait和notify {
    public static void main(String[] args) throws InterruptedException {
        I_TaskQueue2 taskQueue2 = new I_TaskQueue2();
        List<Thread> ts = new ArrayList<Thread>();
        for (int i =0; i < 5; i++) {
            Thread thread = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            //如果任务队里为空,此时由于wait使线程处于等待状态。
                            //当线程在wait时出现异常（比如执行interrupt），则执行catch，结束线程。
                            String s = taskQueue2.getTask();
                            System.out.println("execute task" + s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //结束线程
                            return;
                        }
                    }
                }
            };
            thread.start();
            ts.add(thread);
        }

        for (Thread thread : ts) {
            System.out.println(thread);
            //[线程名称、线程优先级、线程所属组]
        }
        Thread addTaskThread = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    //放入任务
                    String s = "t-" + Math.random();
                    System.out.println("add task" + s);
                    taskQueue2.addTask(s);
                    try {
                        //让当前线程阻塞0.1s
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        };

        addTaskThread.start();
        System.out.println(addTaskThread);
        addTaskThread.join();    //主线程等待
        Thread.sleep(100);  //主线程等待
        //中断getTask线程，使wait出现异常
        for (Thread t : ts) {
            t.interrupt();
        }

    }
}

/*
该代码块当getTask先判断队列是否为空时，如果为空，就循环等待，直到另一个线程往队列中放入任务，while退出，返回元素。
但实际上while永不会退出，因为线程在执行while时，getTask以获得this锁，由于addTask执行条件也是获得this锁，因此其他线程无法调用addTask，
因此执行上述代码，线程会因为while循环百分百占用cpu
 */
class I_TaskQueue1 {
    Queue<String> queue = new LinkedList<String>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
    }

    public synchronized String getTask() {
        while (this.queue.isEmpty()) {}
        return this.queue.remove();
    }
}
/*
如果深入思考一下，我们想要的执行效果是：

线程1可以调用addTask()不断往队列中添加任务；
线程2可以调用getTask()从队列中获取任务。如果队列为空，则getTask()应该等待，直到队列中至少有一个任务时再返回。
因此，多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务。
对于上述TaskQueue，我们先改造getTask()方法，在条件不满足时，线程进入等待状态：
 */
class I_TaskQueue2 {
    Queue<String> queue = new LinkedList<String>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
        /*
        notify这个方法会唤醒"一个"正在this锁等待的线程（就是在getTask()中位于this.wait()的线程），从而使得等待线程从this.wait()方法返回。
        内部调用了this.notifyAll()而不是this.notify()，使用notifyAll()将唤醒所有当前正在this锁等待的线程，
        而notify()只会唤醒其中一个（具体哪个依赖操作系统，有一定的随机性）。这是因为可能有多个线程正在getTask()方法内部的wait()中等待，使用notifyAll()将一次性全部唤醒。
        通常来说，notifyAll()更安全。有些时候，如果我们的代码逻辑考虑不周，用notify()会导致只唤醒了一个线程，而其他线程可能永远等待下去醒不过来了。
         */
    }

    public synchronized String getTask() throws InterruptedException {
        /*
        注意到我们在while()循环中调用wait()，而不是if语句：
        因为线程被唤醒时，需要再次获取this锁。多个线程被唤醒后，只有一个线程能获取this锁，此刻，该线程执行queue.remove()可以获取到队列的元素，
        然而，剩下的线程如果获取this锁后执行queue.remove()，此刻队列可能已经没有任何元素了，
        所以，要始终在while循环中wait()，并且每次被唤醒后拿到this锁就必须再次判断：
         */
        while (this.queue.isEmpty()) {
                /*
                wait方法必须在当前获取的锁对象上调用,这里获取的是this锁，因此使用this.wait()。
                当队列为空时，调用wait，线程进入等待状态，wait()方法不会返回，直到某个时刻，线程从等待状态被其他线程唤醒后，
                wait()方法才会返回，然后执行下一条语句。
                wait()方法的执行机制非常复杂。首先，它不是一个普通的Java方法，而是定义在Object类的一个native方法，也就是由JVM的C代码实现的。
                其次，必须在synchronized块中才能调用wait()方法，因为wait()方法调用时，会释放线程获得的锁，wait()方法返回后，
                线程又会重新“试图”获得锁（如果有多个线程，会竞争该锁），并从暂停处执行。
                当一个线程在this.wait()等待时，它就会释放this锁，从而使得其他线程能够在addTask()方法获得this锁。

                但是，注意到wait()方法返回时需要重新获得this锁。假设当前有3个线程被唤醒，唤醒后，首先要等待执行addTask()的线程结束此方法后，
                才能释放this锁，随后，这3个线程中只能有一个获取到this锁，剩下两个将继续等待
                 */
                this.wait();
        }
        return this.queue.remove();
    }
}