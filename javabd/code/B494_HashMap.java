import java.util.Map;
import java.util.TreeMap;

/**
 * Created by HuGuodong on 2019-10-17.
 */
public class B494_HashMap {


  public static void main(String[] args) {
    TreeMap<String, Integer> mp = new TreeMap<>();
    mp.put("z",1);
    mp.put("a", 2);
    for(Map.Entry<String, Integer> entry:mp.entrySet()){
      System.out.println(entry.getKey()+": "+ entry.getValue());
    }
//    a: 2
//    z: 1
  }
}
