package A_Java快速入门.C_流程控制;

import java.util.Scanner;
public class Circulate {
    public static void main(String[] args) {
        // println表示输出并换行，
        // print仅输出， printf格式化输出(格式化使用%占位符)
        /*
        从控制台读取一个字符串和一个整数
        Scanner: 标准输入流处理类
        System.out:标准输出流
        System.in: 标准输入流
         */
        Scanner scan = new Scanner(System.in);
        System.out.println("输入姓名：");
        String name = scan.nextLine();
        System.out.println("输入年龄：");
        int age = scan.nextInt();
        System.out.printf("Hi, %s, you are %d.\n", name, age);

        //利用if语句判断引用类型，判断引用类型是==用于判断引用是否相等，equals用于判断内容是否相等
        String s1 = "hello";
        String s2 = "Hello".toLowerCase();
        if (s1 == s2) {
            System.out.println("引用相等");
        } else if (s1.equals(s2)) {
            System.out.println("内容相等");
        }

        //利用switch进行多重选择
        //从Java 14开始，switch语句正式升级为表达式，不再需要break，并且允许使用yield返回值
        int opt = 1;
        switch(opt) {
            case 1:
                System.out.println("select 1");
                break;
            case 2:
            case 3:
                System.out.println("select 2 3");
                break;
            default:
                System.out.println("select other");
                break;
        }

        //while, do while, for, for each循环
        int[] array = {1, 2, 3, 4, 5};
        for (int n : array) {
            System.out.println(n);
        }

    }
}
