package p01intro;

import org.springframework.stereotype.Component;

/**
 * Created by HuGuodong on 2020/7/5.
 */
@Component
public class EmployeeManager {
  public EmployeeDTO getEmployeeById(Integer employeeId) {
    System.out.println("Method getEmployeeById() called");
    return new EmployeeDTO();
  }
}
