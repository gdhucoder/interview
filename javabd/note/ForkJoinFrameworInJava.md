Fork/Join framework in Java

## Overview

In one word, **fork/join framework is an idea of divide and conquer approach.**, which provides tools to help speed up parallet processing by attempting to use all available processor cores.

In practice, this means that **the framework first “forks”**, recursively breaking the task into smaller independent subtasks until they are simple enough to be executed asynchronously.

After that, **the “join” part begins**, in which results of all subtasks are recursively joined into a single result, or in the case of a task which returns void, the program simply waits until every subtask is executed.

To provide effective parallel execution, the fork/join framework uses a pool of threads called the *ForkJoinPool*, which manages worker threads of type *ForkJoinWorkerThread*.

## ForkJoinPool

Worker thread executes one task at a time, but ForkJoinPool doesn't create a separate thread for every single subtask. Each thread in the pool has a DECK( uses **work-stealing** algorithm ).

Example

```java
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

```

```java
/Library/Java/JavaVirtualMachines/jdk-11.0.5.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=60356:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/gdhu/projects/interview/corejava/out/production/v1ch12 forkJoin.MyForkJoinTest
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this AS was processed by ForkJoinPool.commonPool-worker-9
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this DFA was processed by ForkJoinPool.commonPool-worker-9
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this SDF was processed by ForkJoinPool.commonPool-worker-9
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this AS was processed by main
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this VJK was processed by ForkJoinPool.commonPool-worker-11
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this FL was processed by ForkJoinPool.commonPool-worker-5
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this NZ was processed by ForkJoinPool.commonPool-worker-3
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this ;LC was processed by ForkJoinPool.commonPool-worker-15
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this ZCV was processed by ForkJoinPool.commonPool-worker-13
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this AL was processed by ForkJoinPool.commonPool-worker-7
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this DJF was processed by ForkJoinPool.commonPool-worker-13
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this SJD was processed by ForkJoinPool.commonPool-worker-11
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this DLK was processed by ForkJoinPool.commonPool-worker-15
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this AJS was processed by ForkJoinPool.commonPool-worker-5
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this UUU was processed by ForkJoinPool.commonPool-worker-9
10月 26, 2019 9:55:15 下午 forkJoin.MyForkJoinTest$CustomRecursiveAction processing
信息: Info this FNZ was processed by ForkJoinPool.commonPool-worker-13

Process finished with exit code 0

```

