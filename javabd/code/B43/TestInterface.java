package B43;

/**
 * Created by HuGuodong on 2019-10-13.
 */
public class TestInterface {

}

class Drawer implements Draw1{

  @Override
  public void draw() {

  }

  @Override
  public void paint() {

  }
}

abstract class Paint extends TestInterface{
  abstract void print();
}

interface Draw{
  void draw();
}

interface Draw1 extends Draw{
  void paint();
}