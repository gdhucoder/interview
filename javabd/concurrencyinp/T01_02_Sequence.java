import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by HuGuodong on 2020/6/8.
 */
@ThreadSafe
public class T01_02_Sequence {
  @GuardedBy("this") private int val;
  public synchronized int getNext(){
    return val++;
  }
}
