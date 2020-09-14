package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

  public static final double STARTING_X = Game.SIZE / 2;
  public static final double STARTING_Y = Game.SIZE / 2;
  public static final double BALL_RADIUS = 20;
  public static final double VERTICAL_SPEED = 10;
  public static final double HORIZONTAL_SPEED = 10;

  private Circle ball;

  public Ball() {
    ball = new Circle(STARTING_X, STARTING_Y, BALL_RADIUS);
    ball.setFill(Color.RED);
    ball.setId("ball");
  }

  public void setX(double x) {
    ball.setCenterX(x);
  }

  public void setY(double y) {
    ball.setCenterY(y);
  }

  public double getStartingX() {
    return STARTING_X;
  }

  public double getStartingY() {
    return STARTING_Y;
  }

  public double BALL_RADIUS() {
    return BALL_RADIUS;
  }

  public Circle getCircle() {
    return ball;
  }
}
