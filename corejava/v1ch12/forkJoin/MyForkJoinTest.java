package forkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

/**
 * Created by HuGuodong on 2019-10-26.
 */
public class MyForkJoinTest {
  public static class CustomRecursiveAction extends RecursiveAction{
    private static final int THRSEHOLD = 4;
    private static Logger logger = Logger.getAnonymousLogger();
    private String workload = "";
    public CustomRecursiveAction(String workload){
      this.workload = workload;
    }
    @Override
    protected void compute() {
      if(workload.length() > THRSEHOLD){
        ForkJoinTask.invokeAll(createTasks());
      }else{
        processing();
      }
    }
    private List<CustomRecursiveAction> createTasks(){
      List<CustomRecursiveAction> tasks = new ArrayList<>();
      String partOne = workload.substring(0, workload.length()/2);
      String partTow = workload.substring(workload.length()/2, workload.length());
      tasks.add(new CustomRecursiveAction(partOne));
      tasks.add(new CustomRecursiveAction(partTow));
      return tasks;
    }
    private void processing(){
      String result = workload.toUpperCase();
      logger.info("Info this " + result +" was processed by "+Thread.currentThread().getName());
    }
  }

  public static void main(String[] args) {
    String workload = "asdjfalsjdflajsdlkfnznz;lcvjkzcvasdfasdfuuu";
    CustomRecursiveAction a = new CustomRecursiveAction(workload );
    a.compute();
  }
}
