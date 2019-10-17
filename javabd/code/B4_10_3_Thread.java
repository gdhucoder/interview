import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by HuGuodong on 2019/10/17.
 */

public class B4_10_3_Thread {

  public static class MyThread extends Thread{
    @Override
    public void run() {
      System.out.println("Thread body.");
    }
  }

  public static class MyRunnable implements Runnable{

    @Override
    public void run() {
      System.out.println("Runnable body.");
    }
  }

  public static class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
      return "Hello World!";
    }
  }

  public static void main(String[] args) {
    MyThread t = new MyThread();
    t.start();
    MyRunnable r = new MyRunnable();
    Thread td = new Thread(r);
    td.start();

    ExecutorService threadPool = Executors.newSingleThreadExecutor();
    Future<String> future = threadPool.submit(new MyCallable());
    try {
      System.out.println("Waiting thread to finish.");
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}

