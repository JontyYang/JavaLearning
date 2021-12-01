package J_多线程;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
使用java.util.concurrent包提供的线程安全的并发集合可以大大简化多线程编程：

多线程同时读写并发集合是安全的；

尽量使用Java标准库提供的并发集合，避免自己编写同步代码。
 */

/**
 * 我们在前面已经通过ReentrantLock和Condition实现了一个BlockingQueue(阻塞队列)
 * 阻塞队列的意思是，当一个线程调用TaskQueue的getTask方法时，该方法会让线程编程等待状态，直到队列条件满足不为空时，
 * 线程被唤醒，getTask()方法才会返回。
 * 因为阻塞队列非常有用，可以使用java.util.concurrent包提供的线程安全的集合：ArrayBlockingQueue。
 * 除了BlockingQueue外，针对List、map、set、Deque等，该包都提供了相应并发集合：
 * <p>
 * interface	     non-thread-safe	         thread-safe
 * List	           ArrayList	          CopyOnWriteArrayList
 * Map	            HashMap	               ConcurrentHashMap
 * Set	     HashSet / TreeSet	           CopyOnWriteArraySet
 * Queue	      ArrayDeque / LinkedList	  ArrayBlockingQueue / LinkedBlockingQueue
 * Deque	      ArrayDeque / LinkedList	    LinkedBlockingDeque
 */
public class N_使用Concurrent集合 {
    public static void main(String[] args) {
        //使用这些并发集合和使用非线程安全的集合类完全相同，我们以ConcurrentHashMap为例：
        Map<String, String> map = new ConcurrentHashMap<>();
        //在不同的线程读写,下面三条语句是三个线程，不需要我们自己写三个线程
        //因为所有的同步和加锁的逻辑都在集合内部实现，对外部调用者来说，只需要正常按接口引用，其他代码和原来的非线程安全代码完全一样。
        map.put("1", "Q");
        map.put("2", "E");
        System.out.println(map.get("1"));

        /*
        java.util.Collections工具类还提供了一个旧的线程安全集合转换器，可以这么用：

        Map unsafeMap = new HashMap();
        Map threadSafeMap = Collections.synchronizedMap(unsafeMap);
        但是它实际上是用一个包装类包装了非线程安全的Map，然后对所有读写方法都用synchronized加锁，
        这样获得的线程安全集合的性能比java.util.concurrent集合要低很多，所以不推荐使用
         */

    }
}
