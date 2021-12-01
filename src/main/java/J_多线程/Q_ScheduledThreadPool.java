package J_多线程;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
还有一种任务，需要定期反复执行，例如，每秒刷新证券价格。这种任务本身固定，需要反复执行，可使用ScheduledThreadPool。
放入该线程池的任务可以定期反复执行。
 */
public class Q_ScheduledThreadPool {
    public static void main(String[] args) {
//        创建一个ScheduledThreadPool
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        //提交一次性任务，会在指定延迟后执行一次
        scheduledExecutorService.schedule(new Q_Task(" one-time"), 1, TimeUnit.SECONDS);   //一秒后只执行一次任务
        //2秒后执行定时任务，每三秒执行
        scheduledExecutorService.scheduleAtFixedRate(new Q_Task("fixed-rate"), 2, 3, TimeUnit.SECONDS);
        //2秒后开始执行，以3秒为间隔执行（任务结束三秒后）
        scheduledExecutorService.scheduleWithFixedDelay(new Q_Task("fixed-delay"), 2, 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        /*
        注意FixedRate和FixedDelay的区别。FixedRate是指任务总是以固定时间间隔触发，不管任务执行多长时间：

│░░░░      │░░░░░░  │░░░       │░░░░░    │░░░
├───────┼───────┼───────┼───────┼────>
│<─────>  │<─────>  │<─────> │<─────>  │
而FixedDelay是指，上一次任务执行*完毕后*，等待固定的时间间隔，再执行下一次任务：

│░░░│             │░░░░░│             │░░│             │░
└───┼───────┼─────┼───────┼──┼───────┼──>
        │<─────>│           │<─────> │     │ <─────>│

        因此，使用ScheduledThreadPool时，我们要根据需要选择执行一次、FixedRate执行还是FixedDelay执行。
         */

    }
}

/**
 * 在FixedRate模式下，假设每秒触发，如果某次任务执行时间超过1秒，后续任务会不会并发执行？
 * 不会，如果此时任务执行超过时间间隔，则后续执行会被延迟，直至该任务执行完，不会发生和后续任务的并发。
 * <p>
 * 如果任务抛出了异常，后续任务是否继续执行？
 * 如果任务的任何执行遇到异常，则将禁止后续任务执行
 */
//任务需要实现线程的run方法
class Q_Task implements Runnable {
    private final String name;   //因为是final，不用考考虑线程安全

    public Q_Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start this task" + this.name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end this task" + this.name);
    }
}
/*
Java标准库还提供了一个java.util.Timer类，这个类也可以定期执行任务，但是，一个Timer会对应一个Thread，
所以，一个Timer只能定期执行一个任务，多个定时任务必须启动多个Timer，而一个ScheduledThreadPool就
可以调度多个定时任务，所以，我们完全可以用ScheduledThreadPool取代旧的Timer。
 */