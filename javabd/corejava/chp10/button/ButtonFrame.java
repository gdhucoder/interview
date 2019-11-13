package chp10.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by HuGuodong on 2019/11/13.
 */
public class ButtonFrame extends JFrame {

  private JPanel buttonPanel;
  private static final int DEFAULT_WIDTH = 300;
  private static final int DEFAULT_HEIGHT = 200;

  public ButtonFrame() {
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    var yellowButton = new JButton("Yellow");
    var blueButton = new JButton("Blue");
    var redButton = new JButton("Red");

    buttonPanel = new JPanel();

    buttonPanel.add(yellowButton);
    buttonPanel.add(blueButton);
    buttonPanel.add(redButton);

    add(buttonPanel);

    var yellowAction = new ColorAction(Color.YELLOW);
    var blueAction = new ColorAction(Color.BLUE);
    var redAction = new ColorAction(Color.RED);

    yellowButton.addActionListener(yellowAction);
    blueButton.addActionListener(blueAction);
    redButton.addActionListener(redAction);

  }

  private class ColorAction implements ActionListener {

    private Color backgroundColor;

    public ColorAction(Color c) {
      backgroundColor = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      buttonPanel.setBackground(backgroundColor);
    }
  }
}
