package J_多线程;
/*
Java的synchronized锁是可重入锁；

死锁产生的条件是多线程各自持有不同的锁，并互相试图获取对方已持有的锁，导致无限等待；

避免死锁的方法是多线程获取锁的顺序要一致。
 */
public class H_死锁 {
    public static void main(String[] args) {

    }
}
//可重入锁
class H_Counter1 {
    private int count = 0;

    //同步方法，被锁对象是this实例
    public synchronized void add(int n) {
        if (n < 0) {
            dec(-n);
        } else {
            count += n;
        }
    }

    public synchronized void dec(int n) {
        count += n;
    }

    /*
    观察synchronized修饰的add()方法，一旦线程执行到add()方法内部，说明它已经获取了当前实例的this锁。
    如果传入的n < 0，将在add()方法内部调用dec()方法。由于dec()方法也需要获取this锁，现在问题来了：

    对同一个线程，能否在获取到锁以后继续获取同一个锁？

    答案是肯定的。JVM允许同一个线程重复获取同一个锁，这种能被同一个线程反复获取的锁，就叫做可重入锁。
    由于Java的线程锁是可重入锁，所以，获取锁的时候，不但要判断是否是第一次获取，还要记录这是第几次获取。
    每获取一次锁，记录+1，每退出synchronized块，记录-1，减到0的时候，才会真正释放锁。
     */
}

//死锁
class H_Counter2 {
    private int count1 = 0;
    private int count2 = 0;

    //同步方法，被锁对象是this实例
    public void add(int n) {
        synchronized(this) {
            this.count1 += n;
            synchronized(H_Counter2.class) {
                this.count2 += n;
            }
        }
    }

    public synchronized void dec(int n) {
        synchronized(H_Counter2.class) {
            this.count2 += n;
            synchronized(this) {
                this.count1 += n;
            }
        }
    }

    /*
    在获取多个锁的时候，不同线程获取多个不同对象的锁可能导致死锁。对于上述代码，线程1和线程2如果分别执行add()和dec()方法时：

    线程1：进入add()，获得lockA；
    线程2：进入dec()，获得lockB。
    随后：

    线程1：准备获得lockB，失败，等待中；
    线程2：准备获得lockA，失败，等待中。
    此时，两个线程各自持有不同的锁，然后各自试图获取对方手里的锁，造成了双方无限等待下去，这就是死锁。

    死锁发生后，没有任何机制能解除死锁，只能强制结束JVM进程。

    因此，在编写多线程应用时，要特别注意防止死锁。因为死锁一旦形成，就只能强制结束进程。

    那么我们应该如何避免死锁呢？答案是：线程获取锁的顺序要一致。即严格按照先获取lockA，再获取lockB的顺序，改写dec()方法如下：

    public void dec(int m) {
         synchronized(lockA) { // 获得lockA的锁
            this.value -= m;
            synchronized(lockB) { // 获得lockB的锁
              this.another -= m;
          } // 释放lockB的锁
       } // 释放lockA的锁
    }
     */
}