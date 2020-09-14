package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

  private static final double STARTING_X = 30;
  private static final double STARTING_Y = 30;
  private static final double BALL_RADIUS = 20;
  private Circle ball;

  public Ball() {
    ball = new Circle(STARTING_X, STARTING_Y, BALL_RADIUS);
    ball.setFill(Color.RED);
    ball.setId("ball");
  }

  public Circle getCircle() {
    return ball;
  }
}
