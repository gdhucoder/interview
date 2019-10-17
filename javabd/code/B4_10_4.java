/**
 * Created by HuGuodong on 2019/10/17.
 */

public class B4_10_4 {

  public static void main(String[] args) {
    test1();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    test2();
//
//    test1: begin.
//    test1: end.
//    Thread Demo: begin.
//    Thread Demo: end.
//    test2: begin.
//    Thread Demo: begin.
//    Thread Demo: end.
//    test2: end.

  }



  public static class ThreadDemo extends Thread{

    @Override
    public void run() {
      System.out.println("Thread Demo: begin.");
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread Demo: end.");
    }
  }

  public static void test1(){
    System.out.println("test1: begin.");
    Thread t1 = new ThreadDemo();
    t1.start();
    System.out.println("test1: end.");
  }

  public static void test2(){
    System.out.println("test2: begin.");
    Thread t2 = new ThreadDemo();
    t2.run();
    System.out.println("test2: end.");
  }




}
