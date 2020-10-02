package Breakout;

import Bricks.Brick;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Yasser Elmzoudi, Jack Ellwood This class is dependent on the breakout package as well as
 * the Paddle class and defines the characteristics of a Ball, whose Shape is a Circle. A Ball can
 * be created by simply calling new Ball() with no parameters as the default parameters of Ball have
 * already been set
 */
public class Ball {

  public static final double STARTING_X = Game.SIZE / 2;
  public static final double STARTING_Y = Game.SIZE / 2;
  public static final double BALL_RADIUS = 6;
  public static final double VERTICAL_SPEED = 120;
  public static final double HORIZONTAL_SPEED = 120;

  private final Circle ball;
  private double verticalSpeed;
  private double horizontalSpeed;

  /**
   * Ball constructor that creates a default representation of a Ball which is a red Circle in the
   * middle of the screen that is falling down with a given velocity
   */
  public Ball() {
    ball = new Circle(STARTING_X, STARTING_Y, BALL_RADIUS);
    ball.setFill(Color.RED);
    ball.setId("ball" + new Random());

    verticalSpeed = VERTICAL_SPEED;
    horizontalSpeed = 0;
  }

  /**
   * Updates the x and y positions of a Ball by adjusting them based on a provided time variable
   * using its current speeds
   *
   * @param elapsedTime double representing the amount of time that has passed
   */
  public void ballMovement(double elapsedTime) {
    setX(getX() + elapsedTime * horizontalSpeed);
    setY(getY() + elapsedTime * verticalSpeed);
  }

  /**
   * Checks to see if a Ball has hit a given paddle and bounces off of the Paddle accordingly based
   * on where the Paddle is hit
   *
   * @param paddle Paddle representing paddle that is checked for a collision with a Ball
   */
  public void checkPaddleHit(Paddle paddle) {
    if (isPaddleHit(paddle)) {
      if (horizontalSpeed == 0) {
        setHorizontalSpeed(HORIZONTAL_SPEED);
      }
      if (topSidePaddleHit(paddle) || bottomSidePaddleHit(paddle)) {
        bounceVertical();
      } else if (rightSidePaddleHit(paddle) || leftSidePaddleHit(paddle)) {
        bounceHorizontal();
      }
    }
  }

  private boolean leftSidePaddleHit(Paddle paddle) {
    return getBottomY() >= paddle.getTopY() && getTopY() <= paddle.getBottomY();
  }

  private boolean rightSidePaddleHit(Paddle paddle) {
    return getBottomY() >= paddle.getTopY() && getTopY() <= paddle.getBottomY();
  }

  private boolean bottomSidePaddleHit(Paddle paddle) {
    return getRightX() >= paddle.getLeftX() && getLeftX() <= paddle.getRightX()
        && getTopY() >= paddle.getBottomY();
  }

  private boolean topSidePaddleHit(Paddle paddle) {
    return getRightX() >= paddle.getLeftX() && getLeftX() <= paddle.getRightX()
        && getBottomY() <= paddle.getTopY();
  }

  private boolean isPaddleHit(Paddle paddle) {
    return ball.getBoundsInParent().intersects(paddle.getRectangle().getBoundsInParent());
  }

  /**
   * Checks to see if a Ball has hit any wall and bounces off of the wall accordingly based on where
   * the wall is hit. Also, updates the Display in the case of a loss of life
   *
   * @param myDisplay Display that is to be changed if a life is lost when a ball hits the bottom of a screen
   * @param ballList List<Ball> representing all of the Balls that are currently active
   * @param root Group representing the root of the Objects currently created
   */
  public void checkWallHit(Display myDisplay, List<Ball> ballList, Group root) {
    if (rightSideWallHit() || leftSideWallHit()) {
      bounceHorizontal();
    } else if (topSideWallHit()) {
      bounceVertical();
    } else if (bottomSideWallHit()) {
      if (ballList.size() > 1) {
        removeBall(ballList, root);
      } else {
        reset();
        updateDisplayOnDeath(myDisplay);
      }
    }
  }

  private void updateDisplayOnDeath(Display myDisplay) {
    myDisplay.changeLives(-1);
  }

  private void removeBall(List<Ball> ballList, Group root) {
    root.getChildren().remove(ball);
    Platform.runLater(() -> ballList.remove(this));
  }

  /**
   * Resets a Balls position and speed to its starting positions and speeds
   */
  public void reset() {
    setX(STARTING_X);
    setY(STARTING_Y);
    setVerticalSpeed(VERTICAL_SPEED);
    setHorizontalSpeed(0);
  }

  /**
   * Checks to see if a Ball has hit a given Brick and bounces off of the Brick accordingly based
   * on where the Brick is hit
   * @param brick Brick representing brick that is checked for a collision with a Ball
   * @return boolean representing if a Brick has been hit so that multiple collisions can be detected at once
   */
  public boolean checkBrickHit(Brick brick) {
    if (isBrickHit(brick)) {
      if (topSideBrickHit(brick) || bottomSideBrickHit(brick)) {
        bounceVertical();
        return true;
      } else if (rightSideBrickHit(brick) || leftSideBrickHit(brick)) {
        bounceHorizontal();
        return true;
      }
    }
    return false;
  }

  private boolean isBrickHit(Brick brick) {
    return ball.getBoundsInParent().intersects(brick.getRectangle().getBoundsInParent());
  }

  /**
   * Provides a Ball's center x-coordinate
   * @return double representing a Ball's center x-coordinate
   */
  public double getX() {
    return ball.getCenterX();
  }

  /**
   * Sets a Ball's center x-coordinate to a provided value
   * @param x double representing a Ball's new center x-coordinate
   */
  public void setX(double x) {
    ball.setCenterX(x);
  }
  /**
   * Provides a Ball's center y-coordinate
   * @return double representing a Ball's center y-coordinate
   */
  public double getY() {
    return ball.getCenterY();
  }

  /**
   * Sets a Ball's center y-coordinate to a provided value
   * @param y double representing a Ball's new center y-coordinate
   */
  public void setY(double y) {
    ball.setCenterY(y);
  }

  /**
   * Provides a Ball's current horizontal speed
   * @return double representing a Ball's horizontal speed
   */
  public double getHorizontalSpeed() {
    return horizontalSpeed;
  }

  /**
   * Set's a Ball's horizontal speed to a provided value
   * @param horizontalSpeed double representing a Ball's new horizontal speed
   */
  public void setHorizontalSpeed(double horizontalSpeed) {
    this.horizontalSpeed = horizontalSpeed;
  }

  /**
   * Provides a Ball's current vertical speed
   * @return double representing a Ball's vertical speed
   */
  public double getVerticalSpeed() {
    return verticalSpeed;
  }

  /**
   * Set's a Ball's vertical speed to a provided value
   * @param verticalSpeed double representing a Ball's new vertical speed
   */
  public void setVerticalSpeed(double verticalSpeed) {
    this.verticalSpeed = verticalSpeed;
  }

  private double getRadius() {
    return ball.getRadius();
  }

  /**
   * Provides a Ball's Shape
   * @return Circle representing a Ball's Shape
   */
  public Circle getCircle() {
    return ball;
  }

  private void bounceVertical() {
    verticalSpeed *= -1;
  }

  private void bounceHorizontal() {
    horizontalSpeed *= -1;
  }

  /**
   * Determines if a Ball hits the bottom wall
   * @return boolean representing if the bottom wall is hit
   */
  public boolean bottomSideWallHit() {
    return getBottomY() >= Game.SIZE;
  }

  private boolean topSideWallHit() {
    return getTopY() <= 0;
  }

  private boolean leftSideWallHit() {
    return getLeftX() <= 0;
  }

  private boolean rightSideWallHit() {
    return getRightX() >= Game.SIZE;
  }

  private double getRightX() {
    return getX() + getRadius();
  }

  private double getLeftX() {
    return getX() - getRadius();
  }

  private double getTopY() {
    return getY() - getRadius();
  }

  private double getBottomY() {
    return getY() + getRadius();
  }

  private boolean leftSideBrickHit(Brick brick) {
    return getBottomY() >= brick.getTopY() && getTopY() <= brick.getBottomY();
  }

  private boolean rightSideBrickHit(Brick brick) {
    return getBottomY() >= brick.getTopY() && getTopY() <= brick.getBottomY();
  }

  private boolean bottomSideBrickHit(Brick brick) {
    return getRightX() >= brick.getLeftX() && getLeftX() <= brick.getRightX() && getTopY() >= brick
        .getBottomY();
  }

  private boolean topSideBrickHit(Brick brick) {
    return getRightX() >= brick.getLeftX() && getLeftX() <= brick.getRightX()
        && getBottomY() <= brick.getTopY();
  }
}
