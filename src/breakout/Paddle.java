package breakout;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class to instantiate and control a Paddle
 */
public class Paddle {

  public static final int LENGTH = 40;
  public static final int HEIGHT = 10;
  public static final int STARTING_X = Game.SIZE / 2 - LENGTH / 2;
  public static final int STARTING_Y = 7 * Game.SIZE / 8;
  public static final int SPEED = 10;

  private Rectangle myRectangle;

  /**
   * Paddle constructor
   */
  public Paddle() {
    myRectangle = new Rectangle(STARTING_X, STARTING_Y, LENGTH, HEIGHT);
    myRectangle.setFill(Color.BLUE);
    myRectangle.setId("paddle");
  }

  /**
   * Get's the Paddle object's Rectangle instance variable
   *
   * @return myRectangle
   */
  public Rectangle getRectangle() {
    return myRectangle;
  }

  /**
   * Used to move the Paddle in response to a key press
   *
   * @param code the direction of movement for the Paddle
   */
  public void movePaddle(KeyCode code) {
    if (code == KeyCode.LEFT) {
      myRectangle.setX(myRectangle.getX() - SPEED);
    } else if (code == KeyCode.RIGHT) {
      myRectangle.setX(myRectangle.getX() + SPEED);
    }
  }

}
