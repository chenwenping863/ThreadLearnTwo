import java.util.concurrent.*;

/**
 * Created by chenwenping on 17/4/15.
 */
public class FutureTaskTest {

    public static void main(String[] args) {

        //线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //创建线程
        MyCallable callable = new MyCallable();

        //包装线程
        FutureTask<String> futureTask = new FutureTask<String>(callable);

        //执行线程
        executor.submit(futureTask);
        //获取结果
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "我是Callable返回值";
        }
    }
}
