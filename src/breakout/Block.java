package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block {

  public static final int LENGTH = 40;
  public static final int HEIGHT = 20;
  public static final String BLOCK_LETTER = "x";
  public static final Color BLOCK_COLOR = Color.CRIMSON;

  private Rectangle myRectangle;

  /**
   * Block constructor
   *
   * @param x location of block
   * @param y location of block
   */
  public Block(int x, int y) {
    myRectangle = new Rectangle(x, y, LENGTH, HEIGHT);
    myRectangle.setFill(BLOCK_COLOR);
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

}
