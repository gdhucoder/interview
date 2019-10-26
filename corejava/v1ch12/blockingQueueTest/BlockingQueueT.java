package blockingQueueTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HuGuodong on 2019-10-22.
 */
public class BlockingQueueT {

  private static final int FILE_QUEUE_SIZE = 10;
  private static final int SEARCH_THREAD = 100;
  private static final Path DUMMY = Path.of("");
  private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

  public static void main(String[] args) {
    try (var in = new Scanner(System.in)) {
      System.out.println("Enter base dir:");
      String directory = in.nextLine();
      System.out.println("Enter Keyword: (e.g. volatile)");
      String keyword = in.nextLine();
      Runnable enumerator = () -> {
        try {
          enumerate(Path.of(directory));
          queue.put(DUMMY);
        } catch (InterruptedException e) {
//          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      };

      new Thread(enumerator).start();
      for (int i = 1; i <= SEARCH_THREAD; i++) {
        try {
          var done = false;
          while (!done) {
            Path file = queue.take();
            if (file == DUMMY) {
              queue.put(file);
              done = true;
            } else
              search(file, keyword);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void enumerate(Path directory) throws IOException, InterruptedException {
    try (Stream<Path> children = Files.list(directory)) {
      for (Path child : children.collect(Collectors.toList())) {
        if (Files.isDirectory(child))
          enumerate(child);
        else
          queue.put(child);
      }
    }
  }

  public static void search(Path file, String keyword) throws IOException {
    try (var in = new Scanner(file, StandardCharsets.UTF_8)) {
      int lineNumber = 0;
      while (in.hasNextLine()) {
        lineNumber++;
        String line = in.nextLine();
        if (line.contains(keyword))
          System.out.printf("%s:%d:%s%n", file, lineNumber, line);
      }
    }
  }


}
