package concurrentHashMap;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by HuGuodong on 2019-10-26.
 */
public class MyConcurrentHashMap {

  public static void main(String[] args) {
    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);

    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    list.add("A");
    list.add("B");
    list.add("C");

    list.forEach(key -> {
      map.merge(key, 1, Integer::sum);
    });
    map.forEach(100, (k, v) -> System.out.println(k + "->" + v));

    int[] a = {1, 10, 1, 2, 3, 4, 7, 5, 4, 3, 1, 2};
    Arrays.parallelSort(a);
    System.out.println(Arrays.toString(a));
    String[] strings = {"A", "Z", "C", "B"};
    Arrays.parallelSort(strings, 0, 3);
    System.out.println(Arrays.toString(strings));
//    [1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 7, 10]
//    [A, C, Z, B]

  }
}
