import H_单元测试.A_编写JUnit测试;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_编写JUnit测试Test {

    @Before
    public void setUp() throws Exception {
        System.out.println("Start");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("End");
    }

    /*
    核心测试方法testFact()加上了@Test注解，这是JUnit要求的，它会把带有@Test的方法识别为测试方法。
    在测试方法内部，我们用assertEquals(1, Factorial.fact(1))表示，期望Factorial.fact(1)返回1。assertEquals(expected, actual)是最常用的测试方法，它在Assertion类中定义。Assertion还定义了其他断言方法，例如：

    assertTrue(): 期待结果为true
    assertFalse(): 期待结果为false
    assertNotNull(): 期待结果为非null
    assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等

    使用浮点数时，由于浮点数无法精确地进行比较，因此，我们需要调用assertEquals(double expected, double actual, double delta)这个重载方法，指定一个误差值
     */
    @Test
    public void fact() {
        assertEquals(1, A_编写JUnit测试.fact(1));
        assertEquals(5, A_编写JUnit测试.fact(3));
    }
}