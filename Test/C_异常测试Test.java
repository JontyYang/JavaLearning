import H_单元测试.C_异常测试;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class C_异常测试Test {

    @BeforeEach
    void setUp() {
        System.out.println("Start");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End");
    }

    /*
    JUnit提供assertThrows()来期望捕获一个指定的异常。第二个参数Executable封装了我们要执行的会产生异常的代码。当我们执行Factorial.fact(-1)时，
    必定抛出IllegalArgumentException。assertThrows()在捕获到指定异常时表示通过测试，未捕获到异常，或者捕获到的异常类型不对，均表示测试失败。
     */
    @Test
    void fact() {
        assertThrows(IllegalAccessException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                C_异常测试.fact(-1);
            }
        });
    }
}