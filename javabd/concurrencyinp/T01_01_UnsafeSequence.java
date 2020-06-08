import net.jcip.annotations.NotThreadSafe;

/**
 * Created by HuGuodong on 2020/6/8.
 */
@NotThreadSafe
public class T01_01_UnsafeSequence {

  private int val;

  public int getNext() {
    return val++;
  }
}
