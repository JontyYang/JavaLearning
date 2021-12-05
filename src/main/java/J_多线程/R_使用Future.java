package J_多线程;

import java.util.Random;
import java.util.concurrent.*;

/*
在执行多个任务的时候。提供的线程池非常方便，提交任务只需要实现Runnable接口，就可以让线程去执行。
但是，Runnable接口有个问题，它的方法没有返回值。这是任务如果需要一个返回结果，那么只能保存到变量，还要提供
额外的方法读取，非常不便。
因此，java标准库还提供了一个Callable接口，和Runnable接口相比，它多了一个返回值,并且Callable是一个泛型接口，
可以指定返回值类型。
 */
class R_Task implements Callable<String> {
    private String name;

    public R_Task() {
    }

    public R_Task(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {
        System.out.println("Start task " + this.name);
        Thread.sleep(1000);
        System.out.println("Start task " + this.name);
        String string = new Random().nextFloat() + "";
        return string;
    }
}

/**
 * 对线程池提交一个Callable任务，可以获得一个Future对象；
 * <p>
 * 可以用Future在将来某个时刻获取结果。
 */
public class R_使用Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        现在有一个问题，如何获得异步执行结果
         */
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //定义任务
        Callable<String> task = new R_Task("Jonty");
        //提交任务并获得Future
        Future<String> future = executorService.submit(task);
        //从Future获得异步执行的结果，当future还没返回值时，当执行下面语句时，下面语句会被阻塞
        String result = future.get();
        /*
        当我们提交一个Callable任务后，我们会同时获得一个Future对象，然后，我们在主线程某个时刻调用Future对象的get()方法，
        就可以获得异步执行的结果。在调用get()时，如果异步任务已经完成，我们就直接获得结果。如果异步任务还没有完成，
        那么get()会阻塞，直到任务完成后才返回结果。

        一个Future<V>接口表示一个未来可能会返回的结果，它定义的方法有：
        get()：获取结果（可能会等待）
        get(long timeout, TimeUnit unit)：获取结果，但只等待指定的时间；
        cancel(boolean mayInterruptIfRunning)：取消当前任务；
        isDone()：判断任务是否已完成
         */
    }
}

