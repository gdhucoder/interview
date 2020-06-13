package day01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuGuodong on 2020/6/3.
 */
public class _01_Generics {

  public static void main(String[] args)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    List<Integer> list = new ArrayList<>();
    list.add(2);
    Class<? extends List> claz = list.getClass();
    Method add = claz.getDeclaredMethod("add", Object.class);
    add.invoke(list, "kl"); // add success!
    System.out.println(list);
  }
}
