package B46;

/**
 * Created by HuGuodong on 2019-10-15.
 */
public class TestFinallize {

  public void finalize(){
    System.out.println("finalize");
  }

  public static void main(String[] args) {
    TestFinallize t1 = new TestFinallize();
    TestFinallize t2 = new TestFinallize();
    t1 = null;
    t2 = null;
    System.gc();
  }
}
