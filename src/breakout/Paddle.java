package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class to instantiate and control a Paddle
 */
public class Paddle {

  public static final double STARTING_WIDTH = 40;
  public static final double HEIGHT = 10;
  public static final double STARTING_X = Game.SIZE / 2 - STARTING_WIDTH / 2;
  public static final double STARTING_Y = 7 * Game.SIZE / 8;
  public static final double SPEED = 10;

  private Rectangle myRectangle;
  private double myWidth;

  /**
   * Paddle constructor
   */
  public Paddle() {
    myRectangle = new Rectangle(STARTING_X, STARTING_Y, STARTING_WIDTH, HEIGHT);
    myRectangle.setFill(Color.BLUE);
    myRectangle.setId("paddle");
    myWidth = STARTING_WIDTH;
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
    if (code == KeyCode.LEFT && getLeftSideX() - SPEED >= 0) {
      myRectangle.setX(getLeftSideX() - SPEED);
    } else if (code == KeyCode.RIGHT && getRightSideX() + SPEED <= Game.SIZE) {
      myRectangle.setX(getLeftSideX() + SPEED);
    }
  }

  public double getLeftSideX() {
    return myRectangle.getX();
  }

  public double getRightSideX() {
    return myRectangle.getX() + myRectangle.getWidth();
  }

  public void setWidth(double newWidth) {
    myWidth = newWidth;
    myRectangle.setWidth(myWidth);
  }
}
