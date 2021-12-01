package J_多线程;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock提供了乐观读锁，可取代ReadWriteLock以进一步提升并发性能；
 * <p>
 * StampedLock是不可重入锁
 * <p>
 * 因此不建议使用stampedLock
 */
public class M_使用StampedLock {
    public static void main(String[] args) {

    }
}

/*
前面介绍的ReadWriteLock可以解决多线程同时读，但只有一个线程能写的问题。
但是，有个潜在的问题：如果有线程正在读，写线程需要读线程释放锁后才能获取写锁，即读的过程不允许写，这是一种悲观读锁。
要想进一步提升并发执行效率，java8引入了新的读写锁：StampedLock

StampedLock和ReadWriteLock相比，改进在于：读的过程中也允许获取写锁后写入。
那么，这样一来，我们读的数据就会不一致，所以需要额外的代码来判断读的过程中是否有写入，这种读锁是一种乐观锁。
乐观锁的意思就是乐观地估计读的过程中大概率不会有写入，因此被称为乐观锁。反过来，悲观锁则是读的过程中拒绝写入，写入必须等待。
显然乐观锁并发效率更高，但一旦有小概率写入导致读数据不一致，需要能检测出来，再读一遍就行。(这种确保是同一线程内数据的一致性，不能保证多个读线程读取结果一致，
只能保证单个读线程数据是一致的，符合线程读取初心)
 */
class M_Point {
    //stampedLock
    private final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();     //获取写锁
        try {
            this.x += deltaX;
            this.y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);  //释放写锁，并将写锁的stamp传入StampedLock
        }
    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();   //获取一个乐观读锁，乐观锁不用释放锁,其作用就是获取stamp
        //注意下面两行代码不是原子操作
        //假设x,y = (100, 200)
        double currentX = this.x;
        //此处已读取到X=100， 但x，y可能被写线程改为（300,400）
        double currentY = this.y;
        //此处读取到y，如果没有写入，应是（100，200)
        //如果有写入，读取错误的（100,400）
        if (!stampedLock.validate(stamp)) {   //通过stamp检查乐观读锁后是否有其他写锁发生，stamp不一致说明发生写操作
            stamp = stampedLock.readLock();     //获取一个悲观读锁
            try {
                currentX = this.x;
                currentY = this.y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
/*
和ReadWriteLock相比，写入的加锁是完全一样的，不同的是读取。注意到首先我们通过tryOptimisticRead()获取一个乐观读锁，
并返回版本号。接着进行读取，读取完成后，我们通过validate()去验证版本号，如果在读取过程中没有写入，版本号不变，验证成功，
我们就可以放心地继续后续操作。如果在读取过程中有写入，版本号会发生变化，验证将失败。在失败的时候，我们再通过获取悲观读锁再次读取。

由于写入的概率不高，程序在绝大部分情况下可以通过乐观读锁获取数据，极少数情况下使用悲观读锁获取数据。

可见，StampedLock把读锁细分为乐观读和悲观读，能进一步提升并发效率。但这也是有代价的：一是代码更加复杂，二是StampedLock是不可重入锁，不能在一个线程中反复获取同一个锁。
StampedLock还提供了更复杂的将悲观读锁升级为写锁的功能，它主要使用在if-then-update的场景：即先读，如果读的数据满足条件，就返回，如果读的数据不满足条件，再尝试写。
 */