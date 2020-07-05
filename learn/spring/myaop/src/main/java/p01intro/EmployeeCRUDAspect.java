package p01intro;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by HuGuodong on 2020/7/5.
 */
@Aspect
public class EmployeeCRUDAspect {

  //point-cut expression
  // () matches method takes no parameters
  // (*) takes one parameter
  // (..) any number of parameters, 0 or more
  @Before("execution(* p01intro.EmployeeManager.getEmployeeById(..))")
  public void logBeforeV1(JoinPoint joinPoint) {
    System.out.println("p01intro.EmployeeCRUDAspect.logBeforeV1(): "
        + joinPoint.getSignature().getName());
  }

//  @Around("execution(* p01intro.EmployeeManager.getEmployeeById(..))")
//  public void logAround(ProceedingJoinPoint joinPoint) throws Throwable{
//    System.out.println("=================write code for before advise.");
//    Object o = joinPoint.proceed();
//    System.out.println(o);
//    System.out.println(o.getClass().getName());
//    System.out.println("=================write code for after advise.");
//  }

  @AfterReturning(pointcut = "execution(* p01intro.EmployeeManager.getEmployeeById(..))",
  returning = "ret")
  public void logAfterReturn(Object ret) throws Throwable{
    System.out.println("=================logAfterReturn: " + ret);
  }
}
