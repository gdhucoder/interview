package threadlocalv;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HuGuodong on 2019-10-22.
 */
public class TestThreadLocal {
  public static final ThreadLocal<SimpleDateFormat> dateFormat =
      ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      Runnable r = new Runnable() {

        @Override
        public void run() {
          try {
            String dateStamp = dateFormat.get().format(new Date());
            System.out.println("dateStamp: " + dateStamp);
            Thread.sleep(100);
          }catch (InterruptedException e){
            e.printStackTrace();
          }

        }

      };
      var t = new Thread(r);
      t.start();
    }

  }
}
