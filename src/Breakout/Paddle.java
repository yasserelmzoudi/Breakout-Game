package Breakout;

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

  private final Rectangle myRectangle;
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
    if (code == KeyCode.LEFT && getLeftX() - SPEED >= 0) {
      myRectangle.setX(getLeftX() - SPEED);
    } else if (code == KeyCode.RIGHT && getRightX() + SPEED <= Game.SIZE) {
      myRectangle.setX(getLeftX() + SPEED);
    }
  }

  private double getX() {
    return myRectangle.getX();
  }

  private double getY() {
    return myRectangle.getY();
  }

  public double getRightX() {
    return getX() + getWidth();
  }

  private double getWidth() {
    return myRectangle.getWidth();
  }

  public void setWidth(double newWidth) {
    myWidth = newWidth;
    myRectangle.setWidth(myWidth);
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

  private double getHeight() {
    return myRectangle.getHeight();
  }
}
