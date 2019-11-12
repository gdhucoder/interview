package chp10.simpleframe;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Created by HuGuodong on 2019/11/12.
 */
public class SimpleFrameTest {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      var frame = new SimpleFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}

class SimpleFrame extends JFrame {

  static final int DEFAULT_WIDTH = 300;
  static final int DEFAULT_HEIGHT = 200;

  public SimpleFrame() {
    Toolkit kit = Toolkit.getDefaultToolkit();
    var screenSize = kit.getScreenSize();
    int w = screenSize.width;
    int h = screenSize.height;
    setSize(w / 2, h / 2);
    // ?
    var img = new ImageIcon("chp10/simpleframe/duke.gif").getImage();
    setIconImage(img);
    setTitle("FuckImageBed");
  }
}