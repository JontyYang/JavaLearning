package H_单元测试;

/*
单元测试就是针对最小的功能单元编写测试代码。Java程序最小的功能单元是方法，因此，对Java程序进行单元测试就是针对单个Java方法的测试。

 */
public class A_编写JUnit测试 {
    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }
    public static void main(String[] args) {

    }
}

