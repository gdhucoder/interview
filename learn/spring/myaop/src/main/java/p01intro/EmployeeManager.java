package p01intro;

import org.springframework.stereotype.Component;

/**
 * Created by HuGuodong on 2020/7/5.
 * Target object
 */
@Component
public class EmployeeManager {
  // method: join point
  public EmployeeDTO getEmployeeById(Integer employeeId) {
    System.out.println("Method getEmployeeById() called");
    return new EmployeeDTO();
  }
}
