package D_集合;

import java.util.ArrayDeque;
import java.util.Deque;

/*
栈（Stack）是一种后进先出（LIFO：Last In First Out）的数据结构。
Stack是这样一种数据结构：只能不断地往Stack中压入（push）元素，最后进去的必须最早弹出（pop）来：

Stack只有入栈和出栈的操作：
把元素压栈：push(E)；
把栈顶的元素“弹出”：pop()；
取栈顶元素但不弹出：peek()。

Stack的作用
Stack在计算机中使用非常广泛，JVM在处理Java方法调用的时候就会通过栈这种数据结构维护方法调用的层次。

JVM会创建方法调用栈，每调用一个方法时，先将参数压栈，然后执行对应的方法；当方法返回时，返回值压栈，调用方法通过出栈操作获得方法返回值。
因为方法调用栈有容量限制，嵌套调用过多会造成栈溢出，即引发StackOverflowError：（递归就容易出现栈溢出现象)
 */
public class I_stack {
    public static void main(String[] args) {
        /*
        在Java中，我们用Deque可以实现Stack的功能，注意只调用push()/pop()/peek()方法，避免调用Deque的其他方法。
        最后，不要使用遗留类Stack。
         */
        Deque<String> stack = new ArrayDeque<String>();
        stack.push("jonty");
        System.out.println(stack.pop());
    }
}
