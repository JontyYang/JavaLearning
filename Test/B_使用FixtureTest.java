import H_单元测试.B_使用Fixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class B_使用FixtureTest {
    B_使用Fixture calculator;

    //通过@Before进行初始化
    @Before
    public void setUp() throws Exception {
        this.calculator = new B_使用Fixture();
        System.out.println("Start");
    }

    //通过@After进行资源清理
    @After
    public void tearDown() throws Exception {
        this.calculator = null;
        System.out.println("End");
    }

    @Test
    public void add() {
        assertEquals(100, this.calculator.add(100));
    }

    @Test
    public void sub() {
        assertEquals(-100, this.calculator.sub(100));
    }

}