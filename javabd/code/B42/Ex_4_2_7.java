package B42;

/**
 * Created by HuGuodong on 2019-10-10.
 */
public class Ex_4_2_7 {

  public static void main(String[] args) {
    Super s = new SubClass();
    System.out.println(s.f());
  }
}

class Super{
  public int f(){
    return 1;
  }
}

class SubClass extends Super{
  public int f(){
    return 2;
  }
}