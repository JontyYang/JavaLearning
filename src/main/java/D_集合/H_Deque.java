package D_集合;

import java.util.Deque;
import java.util.LinkedList;

/*
Queue是队列，只能一头进，另一头出。
如果把条件放松一下，允许两头都进，两头都出，这种队列叫双端队列（Double Ended Queue），学名Deque。
Java集合提供了接口Deque来实现一个双端队列，它的功能是：
既可以添加到队尾，也可以添加到队首；
既可以从队首获取，又可以从队尾获取。

                       	Queue	                        Deque
添加元素到队尾     	add(E e) / offer(E e)   	addLast(E e) / offerLast(E e)
取队首元素并删除	E    remove() / E poll()	    E removeFirst() / E pollFirst()
取队首元素但不删除	E element() / E peek()	    E getFirst() / E peekFirst()
添加元素到队首	            无	                addFirst(E e) / offerFirst(E e)
取队尾元素并删除	            无	                E removeLast() / E pollLast()
取队尾元素但不删除       	无	                E getLast() / E peekLast()

注意到Deque接口实际上扩展自Queue：
public interface Deque<E> extends Queue<E>
因此，Queue提供的add()/offer()方法在Deque中也可以使用，但是，使用Deque，最好不要调用offer()，而是调用offerLast()：
 */
public class H_Deque {
    public static void main(String[] args) {
        /*
        Deque是一个接口，它的实现类有ArrayDeque和LinkedList。
        我们发现LinkedList真是一个全能选手，它即是List，又是Queue，还是Deque。但是我们在使用的时候，
        总是用特定的接口来引用它，这是因为持有接口说明代码的抽象层次更高，而且接口本身定义的方法代表了特定的用途。
         */
        // 不推荐的写法:
        LinkedList<String> d1 = new LinkedList<String>();
        d1.offerLast("z");
        // 推荐的写法：
        Deque<String> d2 = new LinkedList<>();
        d2.offerLast("z");
        //可见面向抽象编程的一个原则就是：尽量持有接口，而不是具体的实现类。

    }
}
