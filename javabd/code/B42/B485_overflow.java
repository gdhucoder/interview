package B42;

import java.util.Vector;

/**
 * Created by HuGuodong on 2019-10-17.
 */
public class B485_overflow {


  public static void main(String[] args) {
    Vector v = new Vector(10);
    for (int i = 0; i < 10; i++) {
      Object o = new Object();
      v.add(o);
    }

  }
}
