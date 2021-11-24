package E_IO;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class F_PrintStream和PrintWriter {
    public static void main(String[] args) {
        //PrintStream是一种FilterOutputStream，它在OutputStream的接口上，额外提供了一些写入各种数据类型的方法
        /*
        写入int：print(int)
        写入boolean：print(boolean)
        写入String：print(String)
        写入Object：print(Object)，实际上相当于print(object.toString())
        以及对应的一组println()方法，它会自动加上换行符。
        我们经常使用的System.out.println()实际上就是使用PrintStream打印各种数据。其中，System.out是系统默认提供的PrintStream，表示标准输出

        System.err是系统默认提供的标准错误输出
         */

        //PrintStream最终输出的总是byte数据，而PrintWriter则是扩展了Writer接口，它的print()/println()方法最终输出的是char数据。两者的使用方法几乎是一模一样的：
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            printWriter.println(12);
            printWriter.println("fjkjk");
            printWriter.print(1.23);
        }
        System.out.println(stringWriter.toString());

    }
}
