package D_集合;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    优先队列：PriorityQueue
    PriorityQueue和Queue的区别在于，它的出队顺序与元素的优先级有关，对PriorityQueue调用remove()或poll()方法，返回的总是优先级最高的元素。
    要使用PriorityQueue，我们就必须给每个元素定义“优先级”。

    PriorityQueue实现了一个优先队列：从队首获取元素时，总是获取优先级最高的元素。
    PriorityQueue默认按元素比较的顺序排序（必须实现Comparable接口），也可以通过Comparator自定义排序算法（元素就不必实现Comparable接口）。
 */
public class G_PriorityQueue {
    public static void main(String[] args) {
        Queue<String> q = new PriorityQueue<String>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        // apple
        System.out.println(q.remove());
        // banana
        System.out.println(q.poll());
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null,因为队列为空
        /*
        我们放入的顺序是"apple"、"pear"、"banana"，但是取出的顺序却是"apple"、"banana"、"pear"，这是因为从字符串的排序看，"apple"排在最前面，"pear"排在最后面。
        因此，放入PriorityQueue的元素，必须实现Comparable接口，PriorityQueue会根据元素的排序顺序决定出队的优先级。
         */

        //通过Comparable接口来进行排序,相比Comparator推荐使用Comparable接口
        Queue<User> q1 = new PriorityQueue<User>();
        // 添加3个元素到队列:
        q1.offer(new User("Bob", "A2"));
        q1.offer(new User("Alice", "A10"));
        q1.offer(new User("Boss", "V1"));
        System.out.println(q1.poll()); // Boss/V1
        System.out.println(q1.poll()); // Bob/A1
        System.out.println(q1.poll()); // Alice/A2
        System.out.println(q1.poll()); // null,因为队列为空

        //如果我们要放入的元素并没有实现Comparable接口怎么办？PriorityQueue允许我们提供一个Comparator对象来判断两个元素的顺序。
        // 我们以银行排队业务为例，实现一个PriorityQueue：
        Queue<User> q2 = new PriorityQueue<>(new UserComparator());

    }
}
class UserComparator implements Comparator<User> {
    public int compare(User u1, User u2) {
        if (u1.number.charAt(0) == u2.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return u1.number.compareTo(u2.number);
        }
        if (u1.number.charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }
    }
}

class User implements Comparable<User>{
    public String name;
    public String number;

    public User() {
    }

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    //定义自己的方法实现元素的排序
    @Override
    public int compareTo(User o) {
        if (this.number.charAt(0) == o.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小，较小的在前:
            //不能直接去对比字符串，这样A10比A2小
            Integer number1 = Integer.valueOf(this.number.substring(1));
            Integer number2 = Integer.valueOf(o.number.substring(1));
            return number1.compareTo(number2);
        }
        if (this.number.charAt(0) == 'V') {
            // this的号码是V开头,优先级高，排序在前（返回负值）
            return -1;
        } else {
            return 1;
        }
    }
}
