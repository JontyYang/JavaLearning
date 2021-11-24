package J_多线程;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
Condition可以替代wait和notify；

Condition对象必须从Lock对象获取。
 */
public class K_使用condition {
    public static void main(String[] args) {
        List<Thread> ts = new ArrayList<Thread>();
        K_TaskQueue taskQueue = new K_TaskQueue();

        for (int i =0; i < 5; i++) {
            Thread thread = new Thread() {
              public void run() {
                  while (true) {
                      try {
                          String s = taskQueue.getTask();
                          System.out.println("execute task" + this.toString() + s);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                          System.out.println("被中断");
                          break;
                      }
                  }
              }
            };
            thread.start();
            ts.add(thread);
        }

        Thread addThread = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String s = "t-" + Math.random();
                    System.out.println("add task" + s);
                    try {
                        taskQueue.addTask(s);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        addThread.start();
        System.out.println(addThread);
        try {
            addThread.join();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Thread thread : ts) {
            thread.interrupt();
        }

    }
}

/*
使用ReentrantLock比直接使用synchronized更安全，可以替代synchronized进行线程同步。
但是，synchronized可以配合wait和notify实现线程在条件不满足时等待，条件满足时唤醒，用ReentrantLock我们怎么编写wait和notify的功能呢？
答案是使用Condition对象来实现wait和notify的功能。
我们仍然以TaskQueue为例，把前面用synchronized实现的功能通过ReentrantLock和Condition来实现：
 */
class K_TaskQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> taskQueue = new LinkedList<>();
/*
可见，使用Condition时，引用的Condition对象必须从Lock实例的newCondition()返回，这样才能获得一个绑定了Lock实例的Condition实例。
Condition提供的await()、signal()、signalAll()原理和synchronized锁对象的wait()、notify()、notifyAll()是一致的，并且其行为也是一样的：
await()会释放当前锁，进入等待状态；
signal()会唤醒某个等待线程；
signalAll()会唤醒所有等待线程；
唤醒线程从await()返回后需要重新获得锁。
 */
    public void addTask(String s) throws InterruptedException {
        //lock.lock();
        //进行上锁时，可以使用lock()，也可以tryLock。但是tryLock一般用于死锁，此时使用是练习，且容易出错
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                taskQueue.add(s);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("添加任务获取锁失败");
        }
    }

    public String getTask() throws InterruptedException {
//        lock.lock();
        if (lock.tryLock(200, TimeUnit.SECONDS)) {
            //和tryLock()类似，await()可以在等待指定时间后，如果还没有被其他线程通过signal()或signalAll()唤醒，可以自己醒来：
            try {
                //容易出错
//                while (taskQueue.isEmpty()) {
//                    if (condition.await(10, TimeUnit.SECONDS)) {
//                       //被其他线程唤醒
//                        break;
//                    } else {}
//                }
//                return taskQueue.remove();
                while (taskQueue.isEmpty()) {
                    condition.await();
                }
                return taskQueue.remove();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("读取任务获取锁失败");
            return "get failure";
        }
    }
}

/*
和tryLock()类似，await()可以在等待指定时间后，如果还没有被其他线程通过signal()或signalAll()唤醒，可以自己醒来：
if (condition.await(1, TimeUnit.SECOND)) {
    // 被其他线程唤醒
} else {
    // 指定时间内没有被其他线程唤醒
}
可见，使用Condition配合Lock，我们可以实现更灵活的线程同步。
 */