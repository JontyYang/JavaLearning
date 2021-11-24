package F_异常处理;

/*
调用printStackTrace()可以打印异常的传播栈，对于调试非常有用；
捕获异常并再次抛出新的异常时，应该持有原始异常信息；
通常不要在finally中抛出异常。如果在finally中抛出异常，应该原始异常加入到原有异常中。

在catch中抛出异常，不会影响finally的执行。JVM会先执行finally，然后抛出异常。

throws是方法的声明检查型异常，一般用于告诉方法使用者该方法会发生什么异常。
 */
public class B_抛出异常 {
    public static void main(String[] args) {
        try {
            Integer.parseInt("abc");
        } catch (Exception e) {
            //抛出新的异常时，同时加载捕获异常的信息
            System.out.println("catched");
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }
    }
}
