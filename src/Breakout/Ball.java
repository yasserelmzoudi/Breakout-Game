package Breakout;

import Bricks.Brick;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

  public static final double STARTING_X = Game.SIZE / 2;
  public static final double STARTING_Y = Game.SIZE / 2;
  public static final double BALL_RADIUS = 6;
  public static final double VERTICAL_SPEED = 120;
  public static final double HORIZONTAL_SPEED = 120;

  private final Circle ball;
  private double verticalSpeed;
  private double horizontalSpeed;

  public Ball() {
    ball = new Circle(STARTING_X, STARTING_Y, BALL_RADIUS);
    ball.setFill(Color.RED);
    ball.setId("ball" + new Random());

    verticalSpeed = VERTICAL_SPEED;
    horizontalSpeed = 0;
  }

  public void ballMovement(double elapsedTime) {
    setX(getX() + elapsedTime * horizontalSpeed);
    setY(getY() + elapsedTime * verticalSpeed);
  }

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

  public void checkWallHit(Display myDisplay, List<Ball> ballList, Group root) {
    if (rightSideWallHit() || leftSideWallHit()) {
      bounceHorizontal();
    } else if (topSideWallHit()) {
      bounceVertical();
    } else if (bottomSideWallHit()) {
      if (ballList.size() > 1) {
        root.getChildren().remove(ball);
        Platform.runLater(() -> ballList.remove(this));
      } else {
        reset();
        myDisplay.changeLives(-1);
      }
    }
  }

  public void reset() {
    setX(STARTING_X);
    setY(STARTING_Y);
    setVerticalSpeed(VERTICAL_SPEED);
    setHorizontalSpeed(0);
  }

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

  public double getX() {
    return ball.getCenterX();
  }

  public void setX(double x) {
    ball.setCenterX(x);
  }

  public double getY() {
    return ball.getCenterY();
  }

  public void setY(double y) {
    ball.setCenterY(y);
  }

  public double getHorizontalSpeed() {
    return horizontalSpeed;
  }

  public void setHorizontalSpeed(double horizontalSpeed) {
    this.horizontalSpeed = horizontalSpeed;
  }

  public double getVerticalSpeed() {
    return verticalSpeed;
  }

  public void setVerticalSpeed(double verticalSpeed) {
    this.verticalSpeed = verticalSpeed;
  }

  public double getRadius() {
    return ball.getRadius();
  }

  public Circle getCircle() {
    return ball;
  }

  private void bounceVertical() {
    verticalSpeed *= -1;
  }

  private void bounceHorizontal() {
    horizontalSpeed *= -1;
  }

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
