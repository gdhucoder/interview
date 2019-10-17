/**
 * Created by HuGuodong on 2019-10-17.
 */
public class B483_TestLoader {

  public static void main(String[] args) {
    ClassLoader clApp = B483_TestLoader.class.getClassLoader(); //负责加载应用类
    System.out.println(clApp);
    ClassLoader clExt = clApp.getParent();// 负责加载扩招类
    System.out.println(clExt);
    ClassLoader clBoot = clExt.getParent();// 负责加载系统类
    System.out.println(clBoot);
  }
}
