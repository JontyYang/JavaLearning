package D_集合;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
队列是一种经常使用的集合，队列实际上是一个先进先出（FIFO）的有序表，他和List区别是，list可以再任意位置删除和添加元素，
但是队列只有两个操作： 1.把元素添加至队列末尾    2.把队列首部元素取出
 */
public class F_Queue {
    public static void main(String[] args) {
        /*
        对于具体的实现类，有的Queue有最大队列长度限制，有的Queue没有。注意到添加、删除和获取队列元素总是有两个方法，
        这是因为在添加或获取元素失败时，这两个方法的行为是不同的。我们用一个表格总结如下：

                        throw Exception	        返回false或null
        添加元素到队尾	    add(E e)	        boolean offer(E e)
        取队首元素并删除    	E remove()	        E poll()
        取队首元素但不删除	E element()     	E peek()
         */

        //从上面的代码中，我们还可以发现，LinkedList即实现了List接口，又实现了Queue接口，
        // 但是，在使用的时候，如果我们把它当作List，就获取List的引用，如果我们把它当作Queue，就获取Queue的引用
        List<String> list = new LinkedList<>();
        Queue<String> queue = new LinkedList<>();
        System.out.println(queue.add("Jonty"));
        System.out.println(queue.size());
        System.out.println(queue.element());
    }
}
