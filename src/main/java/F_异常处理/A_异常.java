package F_异常处理;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*\
Java使用异常来表示错误，并通过try ... catch捕获异常；

Java的异常是class，并且从Throwable继承；

Error是无需捕获的严重错误，Exception是应该捕获的可处理的错误；

RuntimeException无需强制捕获，非RuntimeException（Checked Exception）需强制捕获，或者用throws声明；

不推荐捕获了异常但不进行任何处理
 */
public class A_异常 {
    public static void main(String[] args) {
        /*
        使用try ... catch ... finally时：
        多个catch语句的匹配顺序非常重要，子类必须放在前面；
        finally语句保证了有无异常都会执行，它是可选的；
        一个catch语句也可以匹配多个非继承关系的异常
         */
        File file = new File("D:\\SS\\Idea\\workplace\\JavaLearning\\Z_Common\\IO.txt");
        try {
             file.createNewFile();
             //当上面语句出现异常时，try中后续语句不会执行。
            System.out.println("未发生异常时该语句才执行");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("进行异常处理");
        } finally {
            System.out.println("不管发没发生异常，该finally语句执行");
        }
        //即使发生异常，该语句还是执行。
        System.out.println("即使发生异常，该语句还是执行");
    }
}
