package J_多线程;

import java.util.Objects;
/*
多线程同时读写共享变量时，会造成逻辑错误，因此需要通过synchronized同步；
同步的本质就是给指定对象加锁，加锁后才能继续执行后续代码；
注意加锁对象必须是同一个实例；
对JVM定义的单个原子操作不需要同步。


 */

public class F_线程同步 {
    public static void main(String[] args) throws InterruptedException {
        /*
        当多个线程同时运行时，线程的调度由操作系统决定，程序本身无法决定。因此，任何一个线程都有可能在任何"指令"处被操作系统暂停，
        然后在某个时间段继续执行。这时就会有一个问题：如果多个线程同时读写共享变量，会出现数据不一致问题。

        例如：对于语句  n = n + 1(看上去是一行语句，实际上对应了三条指令： Load、 Add、 Store)
        我们假设n的值是100，如果两个线程同时执行n = n + 1，得到的结果很可能不是102，而是101，原因在于：
        ┌───────┐    ┌───────┐
        │   E_Thread1   │    │    Thread2  │
        └───┬───┘    └───┬───┘
                │                   │
                │ILOAD (100)        │
                │                   │ILOAD (100)
                │                   │IADD
                │                   │ISTORE (101)
                │IADD               │
                │ISTORE (101)       │
                ▼                   ▼
         如果线程1在执行ILOAD后被操作系统中断，此刻如果线程2被调度执行，它执行ILOAD后获取的值仍然是100，
         最终结果被两个线程的ISTORE写入后变成了101，而不是期待的102。
         这说明多线程模型下，要保证逻辑正确，对共享变量进行读写时，必须保证一组指令以原子方式执行：即某一个线程执行时，其他线程必须等待：
        ┌───────┐    ┌───────┐
        │   E_Thread1   │    │    Thread2  │
        └───┬───┘    └───┬───┘
                │-- lock --         │
                │ILOAD (100)        │
                │IADD               │
                │IStore(101)        │
                │-- unlock --       │-- lock --
                │                   │ILoad(101)
                │                   │IADD,IStore(102)
                ▼                   ▼-- unlock --
     通过加锁和解锁的操作，就能保证3条指令总是在一个线程执行期间，不会有其他线程会进入此指令区间。
     即使在执行期线程被操作系统中断执行，其他线程也会因为无法获得锁导致无法进入此指令区间。
     只有执行线程将锁释放后，其他线程才有机会获得锁并执行。这种加锁和解锁之间的代码块我们称之为临界区（Critical Section），任何时候临界区最多只有一个线程能执行。
     可见，保证一段代码的原子性就是通过加锁和解锁实现的。Java程序使用synchronized关键字对一个对象进行加锁：synchronized保证了代码块在任意时刻最多只有一个线程能执行。
         */
        Thread myThread1 = new F_MyThread1();
        Thread myThread2 = new F_MyThread2();
        myThread1.start();
        myThread2.start();
        myThread1.join();
        myThread2.join();     //join方法阻塞了main线程，但是对其他线程不阻塞。（join只阻塞调用线程所在的线程）
        //对主线程进行阻塞，只有两个线程执行完才能打印出结果。如果没有上两行代码，主线程打印出结果为0，不是正确执行结果。（两个线程没执行完就打印出了结果）
        System.out.println(F_Counter.counter);
    }
}

class F_Counter {
    //对counter数据设置锁
    public static final Object lock = new Object();
    public static int counter = 0;
}

class F_MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            /*
            使用F_Counter.counter实例作为锁，两个线程在执行临界去代码时，必须先获取锁，执行结束后，Sycnhronized会释放锁，这样就不会发生数据的同时读写。
            sync保证了同一时刻锁只能被一个线程获取。
            使用synchronized解决了多线程同步访问共享变量的正确性问题。但是，它的缺点是带来了性能下降。因为synchronized代码块无法并发执行。
            此外，加锁和解锁需要消耗一定的时间，所以，synchronized会降低程序的执行效率。
            如何使用synchronized：
            找出修改共享变量的线程代码块；
            选择一个共享实例作为锁；
            使用synchronized(lockObject) { ... }。

            不需要synchronized的操作
            JVM规范定义了几种原子操作：
            基本类型（long和double除外）赋值，例如：int n = m；
            引用类型赋值，例如：List<String> list = anotherList。  但是如果是多条语句赋值，就需要同步。
            class Pair {
                 int first;
                 int last;
                 public void set(int first, int last) {
                 synchronized(this) {
                 this.first = first;
                 this.last = last;
                   }
                 }
            }
            有些时候，通过一些巧妙的转换，可以把非原子操作变为原子操作。例如，上述代码如果改造成
            class Pair {
                int[] pair;
                public void set(int first, int last) {
                int[] ps = new int[] { first, last };    //局部变量赋值，不需要同步
                this.pair = ps;     //单条赋值语句不需要同步（引用赋值的原子操作）
                 }
            }
             */
            synchronized (F_Counter.lock) {
                F_Counter.counter += 1;
            }
        }
    }
}

class F_MyThread2 extends Thread {
    public void run() {
        for (int i =0; i < 10000; i++) {
            synchronized (F_Counter.lock) {
                F_Counter.counter -= 1;
            }
        }
    }
}


/*
对于Volatile和同步的一些理解
Volatile只保证“数据的时效性，不能够保证数据的原子性”。
volatile只保证：
读主内存到本地副本；
操作本地副本；
回写主内存。
这3步多个线程可以同时进行，因此会被打断。

但是同步操作，保证数据的原子性，操作完之后会写入自己的工作内存。但是不一定会立即写入主内存，这是如果另一个线程执行会发生错误，但这个问题在X86结构中
可以忽略，在其他架构中需在数据前加volatile关键字，保证数据的时效性，使其立马写入主内存。
 */