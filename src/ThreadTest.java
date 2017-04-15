import java.util.concurrent.*;

/**
 * Created by chenwenping on 17/4/15.
 */
public class ThreadTest {

    public static void main(String[] args) {
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //1、创建线程
        MyRunnable myRunnable = new MyRunnable();
        MyCallable myCallable = new MyCallable();

        //执行线程带有返回值

        Future<Integer> CallableFuture = executorService.submit(myCallable);

        //get() 方法会使线程阻塞

        try {
            System.out.print("CallableFuture获取的结果" + CallableFuture.get() + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.print("CallableFuture isCancelled （是否已经取消）" + CallableFuture.isCancelled() + "\n");
        System.out.print("CallableFuture isDone （是否已经完成）" + CallableFuture.isDone() + "\n");

        //执行线程没有返回值
        Future<?> RunnaleFuture = executorService.submit(myRunnable);
        try {
            System.out.print("RunnableFuture获取的结果：" + RunnaleFuture.get() + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.print("RunnableFuture isCancelled （是否已经取消）" + CallableFuture.isCancelled() + "\n");
        System.out.print("RunnableFuture isDone （是否已经完成）" + CallableFuture.isDone() + "\n");
    }


    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.print("Runnable\n");
        }
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.print("Callable\n");
            return 10;
        }
    }
}
