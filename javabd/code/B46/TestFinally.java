package B46;

/**
 * Created by HuGuodong on 2019-10-15.
 */
public class TestFinally {

  public static void main(String[] args) {
    System.out.println(test1());
    System.out.println(test2());
//    finally 3
//    2
//    test 2
//    HelloWorld
  }

  public static int test1(){
    int r = 1;
    try {
      r = 2; // 复制了一份
      return r;
    }finally {
      r = 3;
      System.out.println("finally 3");
    }
  }

  public static StringBuilder test2(){
    StringBuilder sb =  new StringBuilder("Hello");
    try {
      return sb; // 复制了地址！！！！
    }finally {
      sb.append("World");
      System.out.println("test 2");
    }
  }


}
