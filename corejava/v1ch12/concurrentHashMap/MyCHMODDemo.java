package concurrentHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by HuGuodong on 2019-10-26.
 */
public class MyCHMODDemo {

  public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

  public static void process(Path file) {
    try (var in = new Scanner(file)) {
      while (in.hasNext()) {
        String word = in.next();
        map.merge(word, 1L, Long::sum);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Set<Path> descendants(Path rootDir) throws IOException {
    try (var entries = Files.walk(rootDir)) {
      return entries.collect(Collectors.toSet());
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    int processors = Runtime.getRuntime().availableProcessors();
    ExecutorService executorService = Executors.newFixedThreadPool(processors);
    Path pathToRoot = Path.of(".");
    for (var p : descendants(pathToRoot)) {
      if (p.getFileName().toString().endsWith("java")) {
        executorService.execute(() -> process(p));
      }
    }
    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.MINUTES);
    map.forEach((k, v) -> {
      if (v >= 10)
        System.out.println(k + " occurs " + v + " times");
    });
  }

}
