package B_面向对象编程.B_Java核心类;

import java.util.StringJoiner;

public class C_StrJoiner {
    public static void main(String[] args) {
        //拼接字符串
        String[] names = {"Jonty", "Bob", "Jannie"};
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            //StringBuider可以进行链式操作，一次追加多个append
            stringBuilder.append(name).append(", ");
        }
        System.out.println(stringBuilder.length());
        System.out.println(stringBuilder.toString());
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        //去除末尾的空格和逗号
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        //在首部插入Hello，
        stringBuilder.insert(0, "Hello, ");
        //尾部插入！
        stringBuilder.append("!");
        //输出
        System.out.println(stringBuilder.toString());

        //使用StringJoiner可以指定分隔符，开头和结尾或者只指定分隔符
        StringJoiner stringJoiner = new StringJoiner(", ", "Hello, ", "!");
        for (String name : names) {
            stringJoiner.add(name);
        }
        System.out.println(stringJoiner);

        //相比于StringJoiner方法，String.Join方法更加方便，但是不能指定前缀和后缀
        System.out.println(String.join("* ", names).toString());
    }
}
