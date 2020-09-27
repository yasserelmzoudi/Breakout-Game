package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {

  public static final int LENGTH = 40;
  public static final int HEIGHT = 20;
  public static final String BLOCK_LETTER = "x";
  public static final Color BLOCK_BASE_COLOR = Color.CRIMSON;

  private Rectangle myRectangle;
  private int myHealth;

  /**
   * Block constructor
   *
   * @param x location of block
   * @param y location of block
   */
  public Brick(int x, int y) {
    myRectangle = new Rectangle(x, y, LENGTH, HEIGHT);
    myRectangle.setFill(BLOCK_BASE_COLOR);
    myRectangle.setId("block" + x + y);
  }

  /**
   * Get's the Block object's rectangle instance variable
   *
   * @return myRectangle
   */
  public Rectangle getRectangle() {
    return myRectangle;
  }

  //public void blockHit

  public double getX() {
    return myRectangle.getX();
  }

  public double getY() {
    return myRectangle.getY();
  }

  public double getHeight() {
    return myRectangle.getHeight();
  }

  public double getWidth() {
    return myRectangle.getWidth();
  }

  public double getRightX() {
    return getX() + getWidth();
  }

  public double getLeftX() {
    return getX();
  }

  public double getTopY() {
    return getY();
  }

  public double getBottomY() {
    return getY() + getHeight();
  }
}
