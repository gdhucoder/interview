package B43;

/**
 * Created by HuGuodong on 2019-10-10.
 */
public class Ex_4_3_2 {

  /**
   * java label 为了方便跳出循环使用
   * @param args
   */
  public static void main(String[] args) {
    out:
//    System.out.println("begin loop");
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (j >= 2) {
          //break out; // 结束循环，跳到label处，执行后面的语句
          continue out;
        }
        System.out.println(j);
      }

    }
    System.out.println("break");
  }
}
