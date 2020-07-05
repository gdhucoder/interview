package p01intro;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by HuGuodong on 2020/7/5.
 */
@Aspect
public class EmployeeCRUDAspect {

  //point-cut expression
  @Before("execution(* p01intro.EmployeeManager.getEmployeeById(..))")
  public void logBeforeV1(JoinPoint joinPoint) {
    System.out.println("p01intro.EmployeeCRUDAspect.logBeforeV1(): "
        + joinPoint.getSignature().getName());
  }
}
