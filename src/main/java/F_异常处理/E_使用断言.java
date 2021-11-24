package F_异常处理;

public class E_使用断言 {
    public static void main(String[] args) {
        //Java断言的特点是：断言失败时会抛出AssertionError，导致程序结束退出。因此，断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。
        double x = -200;
        assert x > 0 : "x应大于0";
        System.out.println(x);

    }
}
