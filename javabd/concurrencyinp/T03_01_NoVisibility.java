import net.jcip.annotations.ThreadSafe;

/**
 * Created by HuGuodong on 2020/6/8.
 */
public class T03_01_NoVisibility {
  private static boolean ready;
  private static int number;
  private static class ReaderThread extends Thread{
    public void run(){
      while (!ready){
        Thread.yield();
      }
      System.out.println(number);
    }
  }

  public static void main(String[] args) throws InterruptedException {

    new ReaderThread().start();
//    Thread.sleep(10);
    number = 42;
    ready = true;
  }
}
