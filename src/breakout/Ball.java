package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

  public static final double STARTING_X = Game.SIZE / 2;
  public static final double STARTING_Y = Game.SIZE / 2;
  public static final double BALL_RADIUS = 6;
  public static final double VERTICAL_SPEED = 80;
  public static final double HORIZONTAL_SPEED = 80;

  private Circle ball;
  private double verticalSpeed;
  private double horizontalSpeed;

  public Ball() {
    ball = new Circle(STARTING_X, STARTING_Y, BALL_RADIUS);
    ball.setFill(Color.RED);
    ball.setId("ball");

    verticalSpeed = VERTICAL_SPEED;
    horizontalSpeed = 0;
  }

  public void setX(double x) {
    ball.setCenterX(x);
  }

  public void setY(double y) {
    ball.setCenterY(y);
  }

  public void setHorizontalSpeed(double horizontalSpeed) {
    this.horizontalSpeed = horizontalSpeed;
  }

  public void setVerticalSpeed(double verticalSpeed) {
    this.verticalSpeed = verticalSpeed;
  }

  public double getX() {
    return ball.getCenterX();
  }

  public double getY() {
    return ball.getCenterY();
  }

  public double getVerticalSpeed() {
    return verticalSpeed;
  }

  public double getHorizontalSpeed() {
    return horizontalSpeed;
  }

  public double getRadius() {
    return BALL_RADIUS;
  }

  public Circle getCircle() {
    return ball;
  }

  public void setSpeed(double time) {
    horizontalSpeed *= time;
    verticalSpeed *= time;
  }

  public void ballMovement(double elapsedTime) {
    ball.setCenterX(ball.getCenterX() + elapsedTime * horizontalSpeed);
    ball.setCenterY(ball.getCenterY() + elapsedTime * verticalSpeed);
  }

  public void checkPaddleHit(Paddle paddle) {
    if (ball.getBoundsInParent().intersects(paddle.getRectangle().getBoundsInParent())) {
      if (horizontalSpeed == 0) {
        setHorizontalSpeed(HORIZONTAL_SPEED);
      }
      bounceVertical();
    }
  }

  public void checkWallHit() {
    if (rightSideHit()) {
      bounceHorizontal();
    }
    if (leftSideHit()) {
      bounceHorizontal();
    }
    if (topSideHit()) {
      bounceVertical();
    }
    if (bottomSideHit()) {
      reset();
    }
  }

  public void reset() {
    setX(STARTING_X);
    setY(STARTING_Y);
    setVerticalSpeed(VERTICAL_SPEED);
    setHorizontalSpeed(0);
  }

  public boolean bottomSideHit() {
    return getBottomY() >= Game.SIZE;
  }

  public boolean topSideHit() {
    return getTopY() <= 0;
  }

  public boolean leftSideHit() {
    return getLeftX() <= 0;
  }

  public boolean rightSideHit() {
    return getRightX() >= Game.SIZE;
  }

  public void bounceVertical() {
    verticalSpeed *= -1;
  }

  public void bounceHorizontal() {
    horizontalSpeed *= -1;
  }

  public double getRightX() {
    return getX() + getRadius();
  }

  public double getLeftX() {
    return getX() - getRadius();
  }

  public double getTopY() {
    return getY() - getRadius();
  }

  public double getBottomY() {
    return getY() + getRadius();
  }
}
