package F_异常处理;

/**
 * //从目前的趋势来看，越来越多的开源项目从Commons Logging加Log4j转向了SLF4J加Logback
 */

public class F_Log4j {
    public static void main(String[] args) {

        /*
        和Java标准库提供的日志JDK Logging不同，Commons Logging是一个第三方日志库，它是由Apache创建的日志模块。
        Commons Logging的特色是，它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。默认情况下，
        Commons Loggin自动搜索并使用Log4j（Log4j是另一个流行的日志系统），如果没有找到Log4j，再使用JDK Logging。

        使用Commons Logging只需要和两个类打交道，并且只有两步：
        第一步，通过LogFactory获取Log类的实例； 第二步，使用Log实例的方法打日志。


        前面介绍了Commons Logging，可以作为“日志接口”来使用。而真正的“日志实现”可以使用Log4j。
        Log4j是一种非常流行的日志框架，最新版本是2.x。
        Log4j是一个组件化设计的日志系统，它的架构大致如下：
         log.info("User signed in.");
         │
         │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
         ├──>│ Appender         │───>   │  Filter    │───>│          Layout  │───>│ Console  │
         │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
         │
         │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
         ├──>│ Appender     │───>│          Filter  │───>│  Layout           │───>│   File   │
         │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
         │
         │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
         └──>│       Appender │───>│      Filter  │───>│  Layout             │───>│  Socket  │
              └──────────┘    └──────────┘    └──────────┘    └──────────┘
        当我们使用Log4j输出一条日志时，Log4j自动通过不同的Appender把同一条日志输出到不同的目的地。例如：
        console：输出到屏幕；
        file：输出到文件；
        socket：通过网络输出到远程计算机；
        jdbc：输出到数据库
        在输出日志的过程中，通过Filter来过滤哪些log需要被输出，哪些log不需要被输出。例如，仅输出ERROR级别的日志。
        最后，通过Layout来格式化日志信息，例如，自动添加日期、时间、方法名称等信息。
         */
    }
}
