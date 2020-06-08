import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by HuGuodong on 2020/6/8.
 */
@ThreadSafe
public class T03_03_SynchronizedInteger {

  @GuardedBy("this")
  private int val;

  public synchronized int get() {
    return val;
  }

  public synchronized void set(int val) {
    this.val = val;
  }

}
