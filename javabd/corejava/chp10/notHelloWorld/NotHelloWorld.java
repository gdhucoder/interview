package chp10.notHelloWorld;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Created by HuGuodong on 2019/11/12.
 */
public class NotHelloWorld {

  public static void main(String[] args) {
    EventQueue.invokeLater(()->{
      var frame = new NotHelloWorldFrame();
      frame.setTitle("NotHelloWorld");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}
class NotHelloWorldFrame extends JFrame{
  public NotHelloWorldFrame(){
    add(new NotHelloWorldComponent());
    pack();
  }


}

class NotHelloWorldComponent extends JComponent{

  @Override
  protected void paintComponent(Graphics g) {
    g.draw3DRect(10,10,10,10, true);
    g.drawString("HELLO",100,100);
  }
  public Dimension getPreferredSize(){
    return new Dimension(500,200);
  }
}