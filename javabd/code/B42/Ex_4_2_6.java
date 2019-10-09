package B42;

/**
 * Created by HuGuodong on 2019/10/9.
 */

public class Ex_4_2_6 {

  public static void main(String[] args) {
    Base b = new Derived();
    b.f();
    b.g();
  }
}

class Base{
  public Base(){
    f();
  }
  public void f(){
    System.out.println("Base f()");
  }
  public void g(){
    System.out.println("Base g()");
  }
}

class Derived extends Base{
  public void f(){
    System.out.println("D f()");
  }

  public void g() {
    System.out.println("D g()");
  }
}