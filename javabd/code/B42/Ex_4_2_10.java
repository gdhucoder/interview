package B42;

/**
 * Created by HuGuodong on 2019-10-10.
 */
public class Ex_4_2_10 {

  public static void main(String[] args) {
    T t = new T();
    t.test();
    new T().test();
  }
}

class A{}

class T extends A{
  public void test(){
    // getClass() 返回此Object的运行类，为Test
//    System.out.println(super.getClass().getName());
    System.out.println(this.getClass().getSuperclass().getName());
  }
}