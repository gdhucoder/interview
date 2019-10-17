import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HuGuodong on 2019-10-17.
 */
public class B492_TestIterator {

  public static void main(String[] args) {
    List<String> l = new LinkedList<>();
    l.add("a");
    l.add("b");
    l.add("c");

    for(Iterator<String> iter = l.iterator(); iter.hasNext();){
      String str = iter.next();
      System.out.println(str);
      if(str.equals("b")){
        l.add("d");
      }
    }

//    a
//    b
//    Exception in thread "main" java.util.ConcurrentModificationException
//    at java.util.LinkedList$ListItr.checkForComodification(LinkedList.java:966)
//    at java.util.LinkedList$ListItr.next(LinkedList.java:888)
//    at B492_TestIterator.main(B492_TestIterator.java:18)

  }
}
