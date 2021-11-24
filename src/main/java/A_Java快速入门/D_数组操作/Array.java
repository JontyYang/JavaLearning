package A_Java快速入门.D_数组操作;

import java.util.Arrays;
public class Array {
    public static void main(String[] args) {
        //数组在内存中是顺序存储的，访问整个数组需要遍历
        /*
        直接打印数组变量，得到的是数组在JVM中的引用地址
         */
        int[] ns = {2, 3, 1, 4, 5};
        System.out.println(ns);

        //Arrays.toString()可以快速打印数组内容,返回一个字符串
        System.out.println(Arrays.toString(ns));

        /**
         * 常见的对数组排序的算法有冒泡排序，插入排序，快速排序
         * 对数组排序后，数组的引用不变，但是数组内容发生改变
         * 对于字符串数组，它的内容是每个字符串的引用（字符串本身也是一个数组），
         * 所以发生改变的内容还是该数组内容，即每个字符串引用顺序发生改变
         */
        System.out.println("-------------");
        Arrays.sort(ns);
        System.out.println(ns);
        System.out.println(Arrays.toString(ns));
        System.out.println(ns.toString());
        System.out.println("        ooooooo     ");

        String[] string = {"banana", "apple", "orange"};
        System.out.println(string);
        Arrays.sort(string);
        System.out.println(string);
        System.out.println(Arrays.toString(string));
    }
}
