package J_多线程;
/*
用synchronized修饰方法可以把整个方法变为同步代码块，synchronized方法加锁对象是this；
通过合理的设计和数据封装可以让一个类变为“线程安全”；
一个类没有特殊说明，默认不是thread-safe；
多线程能否安全访问某个非线程安全的实例，需要具体问题具体分析。

一个实例在创建的时候肯定是一个线程所有，只有该线程主动把这个实例共享出去才能被多线程访问。

构造方法永远不需要同步；

其他方法是否同步，看逻辑
 */
public class G_同步方法 {
    public static G_Counter counter1 = new G_Counter();
    public static G_Counter counter2 = new G_Counter();

    public static void main(String[] args) throws InterruptedException {
        /*
        java程序依靠Synchronized对线程进行同步，但是让线程自己选择锁住对象往往会使代码逻辑混乱，也不利于封装。
        更好的方法是把同步逻辑封装起来。
        这样，线程调用时，不用关心具体的逻辑，因为同步方法在实例方法内部。并且synchronized锁住的是this，即当前实例，
        使得创建多个Counter实例时，他们之间互不影响，可以并发执行。
         */
        Thread thread1 = new G_Thread1();
        Thread thread2 = new G_Thread2();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(G_同步方法.counter1.get());

        //thread1、2同步，thread3、4同步，但这两组之间并发。
        Thread thread3 = new G_Thread3();
        Thread thread4 = new G_Thread4();
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        System.out.println(G_同步方法.counter2.get());

    }
}

/*
对于Counter类，多线程可以正确调用。
如果一个类被设计为允许多线程正确访问，那这个类就是线程安全的。（Counter就是线程安全的）
还有些不变类，例如String、Integer、LocalDate，他们所有成员变量都是final，多线程同时访问时只能读不能写，这些不变类也是线程安全的。
最后，类似Math这些只提供静态方法，没有成员变量的类，也是线程安全的。
除了上述几种少数情况，大部分类，例如ArrayList，都是非线程安全的类，我们不能在多线程中修改它们。
但是，如果所有线程都只读取，不写入，那么ArrayList是可以安全地在线程间共享的。

没有特殊说明时，一个类默认是非线程安全的。
 */
class G_Counter {
    public int counter = 0;

    public void add() {
        //将实例作为锁进行加锁
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                this.counter += 1;
            }
        }
    }

    public void dec() {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                this.counter -= 1;
             }
        }
    }

    public int get() {
        return this.counter;
    }
}

class G_Thread1 extends Thread{
    public void run() {
        G_同步方法.counter1.add();
    }
}

class G_Thread2 extends Thread{
    public void run() {
        G_同步方法.counter1.dec();
    }
}

class G_Thread3 extends Thread{
    public void run() {
        G_同步方法.counter2.add();
    }
}

class G_Thread4 extends Thread{
    public void run() {
        G_同步方法.counter2.dec();
    }
}

/*
当我们锁住的是this实例时，实际上可以用synchronized修饰这个方法。下面两种写法是等价的：
public void add(int n) {
    synchronized(this) { // 锁住this
        count += n;
    } // 解锁
}
public synchronized void add(int n) { // 锁住this
    count += n;
} // 解锁
因此，用synchronized修饰的方法就是同步方法，它表示整个方法都必须用this实例加锁。

对于static方法，是没有this实例的，因为static方法是针对类而不是实例。但是我们注意到任何一个类都有一个由JVM自动创建的Class实例
，因此，对static方法添加synchronized，锁住的是该类的Class实例。上述synchronized static方法实际上相当于：
public class Counter {
    public static void test(int n) {
        synchronized(Counter.class) {
            ...
        }
    }
}

我们再考察Counter的get()方法：

public class Counter {
    private int count;

    public int get() {
        return count;
    }
    ...
}
它没有同步，因为读一个int变量不需要同步。

然而，如果我们把代码稍微改一下，返回一个包含两个int的对象：

public class Counter {
    private int first;
    private int last;

    public Pair get() {
        Pair p = new Pair();
        p.first = first;
        p.last = last;
        return p;
    }
    ...
}
就必须要同步了
 */
