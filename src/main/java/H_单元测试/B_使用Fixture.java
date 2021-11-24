package H_单元测试;

/*
在测试的时候，我们经常遇到一个对象需要初始化，测试完可能还需要清理的情况，对此
Junit提供了编写测试前准备（@Before）和测试后清理（@After）的固定代码，我们称其为Fixture

因此，我们总结出编写Fixture的套路如下：

对于实例变量，在@BeforeEach中初始化，在@AfterEach中清理，它们在各个@Test方法中互不影响，因为是不同的实例；

对于静态变量，在@BeforeAll中初始化，在@AfterAll中清理，它们在各个@Test方法中均是唯一实例，会影响各个@Test方法。

大多数情况下，使用@BeforeEach和@AfterEach就足够了。只有某些测试资源初始化耗费时间太长，以至于我们不得不尽量“复用”时才会用到@BeforeAll和@AfterAll。

最后，注意到每次运行一个@Test方法前，JUnit首先创建一个XxxTest实例，因此，每个@Test方法内部的成员变量都是独立的，
不能也无法把成员变量的状态从一个@Test方法带到另一个@Test方法。
 */
public class B_使用Fixture {
    private long n = 0;

    public long add(long x) {
        n += x;
        return n;
    }

    public long sub(long x) {
        n -= x;
        return n;
    }

    public static void main(String[] args) {

    }
}
