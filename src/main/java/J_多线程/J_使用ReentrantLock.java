package J_多线程;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
ReentrantLock可以替代synchronized进行同步；

ReentrantLock获取锁更安全；

必须先获取到锁，再进入try {...}代码块，最后使用finally保证释放锁；

可以使用tryLock()尝试获取锁。

使用ReentrantLock比直接使用synchronized更安全，线程在"tryLock()"失败的时候不会导致死锁.
 */
public class J_使用ReentrantLock {
    public static void main(String[] args) {


    }
}

/*
    从Java 5开始，引入了一个高级的处理并发的java.util.concurrent包，它提供了大量更高级的并发功能，能大大简化多线程程序的编写。
    我们知道Java语言直接提供了synchronized关键字用于加锁，但这种锁一是很重(重量锁即线程阻塞操作)，二是获取时必须一直等待，没有额外的尝试机制。
    java.util.concurrent.locks包提供的ReentrantLock（可重入锁）用于替代synchronized加锁
*/
class J_Counter {
    private int count;
    private final Lock lock = new ReentrantLock();

    /*
    因为synchronized是Java语言层面提供的语法，所以我们不需要考虑异常，而ReentrantLock是Java代码实现的锁，我们就必须先获取锁，然后在finally中正确释放锁。
    顾名思义，ReentrantLock是可重入锁，它和synchronized一样，一个线程可以多次获取同一个锁。
     */
    public void add(int n) throws InterruptedException {
        lock.lock();
        try {
            this.count += n;
        } finally {
            lock.unlock();
        }

        /*
        和synchronized不同的是，ReentrantLock可以尝试获取锁：
        上述代码在尝试获取锁的时候，最多等待1秒。如果1秒后仍未获取到锁，tryLock()返回false，程序就可以做一些额外处理，而不是无限等待下去。
        所以，使用ReentrantLock比直接使用synchronized更安全，线程在tryLock()失败的时候不会导致死锁。
         */
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {

            } finally {
                lock.unlock();
            }
        } else {}
    }
}