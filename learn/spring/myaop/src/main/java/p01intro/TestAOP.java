package p01intro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by HuGuodong on 2020/7/5.
 */
public class TestAOP {
  @SuppressWarnings("resource")
  public static void main(String[] args) {
    //applicationContext.xml
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // AOP proxy
    EmployeeManager manager = context.getBean(EmployeeManager.class);
    manager.getEmployeeById(1);
  }
}
