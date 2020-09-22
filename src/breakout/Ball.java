package breakout;

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
  public static final double VERTICAL_SPEED = 80;
  public static final double HORIZONTAL_SPEED = 80;

  private Circle ball;
  private double verticalSpeed;
  private double horizontalSpeed;

  public Ball() {
    ball = new Circle(STARTING_X, STARTING_Y, BALL_RADIUS);
    ball.setFill(Color.RED);
    ball.setId("ball" + new Random());

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
    return ball.getRadius();
  }

  public Circle getCircle() {
    return ball;
  }

  public void setSpeed(double time) {
    horizontalSpeed *= time;
    verticalSpeed *= time;
  }

  public void ballMovement(double elapsedTime) {
    setX(getX() + elapsedTime * horizontalSpeed);
    setY(getY() + elapsedTime * verticalSpeed);
  }

  public void checkPaddleHit(Paddle paddle) {
    if (ball.getBoundsInParent().intersects(paddle.getRectangle().getBoundsInParent())) {
      if (horizontalSpeed == 0) {
        setHorizontalSpeed(HORIZONTAL_SPEED);
      }
      bounceVertical();
    }
  }

  public void checkWallHit(Display myDisplay, List<Ball> ballList, Group root) {
    if (rightSideWallHit() || leftSideWallHit()) {
      bounceHorizontal();
    }
    else if (topSideWallHit()) {
      bounceVertical();
    }
    else if (bottomSideWallHit()) {
      if(ballList.size() > 1){
        root.getChildren().remove(this);
        Platform.runLater(() -> ballList.remove(this));
      }
      else {
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

  public boolean bottomSideWallHit() {
    return getBottomY() >= Game.SIZE;
  }

  public boolean topSideWallHit() {
    return getTopY() <= 0;
  }

  public boolean leftSideWallHit() {
    return getLeftX() <= 0;
  }

  public boolean rightSideWallHit() {
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

  public boolean checkBrickHit(Block brick) {
    boolean brickHit = false;
    if (ball.getBoundsInParent().intersects(brick.getRectangle().getBoundsInParent())) {
      if (rightSideBrickHit(brick) || leftSideBrickHit(brick)) {
        bounceHorizontal();
        brickHit = true;
        System.out.println("BOUNCE HORIZONTAL");
      }
      else if (topSideBrickHit(brick) || bottomSideBrickHit(brick)) {
        bounceVertical();
        brickHit = true;
        System.out.println("BOUNCE VERTICAL");
      }
    }
    return brickHit;
  }

  public boolean leftSideBrickHit(Block brick) {
    //return (getRightX() <= brick.getLeftX());
    return getBottomY() >= brick.getTopY() && getTopY() <= brick.getBottomY();
  }

  public boolean rightSideBrickHit(Block brick) {
    //return (getLeftX() >= brick.getRightX());
    return getBottomY() >= brick.getTopY() && getTopY() <= brick.getBottomY();
  }

  public boolean bottomSideBrickHit(Block brick) {
    //return (getTopY() >= brick.getBottomY());
    return getRightX() >= brick.getLeftX() && getLeftX() <= brick.getRightX() && getTopY() >= brick.getBottomY();
  }

  public boolean topSideBrickHit(Block brick) {
    //return (getBottomY() <= brick.getTopY());
    return getRightX() >= brick.getLeftX() && getLeftX() <= brick.getRightX() && getBottomY() <= brick.getTopY();
  }
}
