package breakout;

import java.util.List;

public class SlowBallPowerUp extends PowerUp {

  public static final double SPEED_DECREASE = 0.5;
  public static final double SPEED_INCREASE = 1 / SPEED_DECREASE;
  private static final String IMAGE_STRING = "SB";

  private double timePassed = 0;

  public SlowBallPowerUp(double x, double y) {
    super(x, y);
  }

  @Override
  public void activate(Game game) {
    List<Ball> balls = game.getBalls();
    for (Ball ball : balls) {
      ball.setHorizontalSpeed(ball.getHorizontalSpeed() * SPEED_DECREASE);
      ball.setVerticalSpeed(ball.getVerticalSpeed() * SPEED_DECREASE);
    }
  }

  @Override
  public boolean deactivate(Game game) {
    if (timePassed >= PowerUp.MAX_POWER_UP_TIME) {
      List<Ball> balls = game.getBalls();
      for (Ball ball : balls) {
        ball.setHorizontalSpeed(ball.getHorizontalSpeed() * SPEED_INCREASE);
        ball.setVerticalSpeed(ball.getVerticalSpeed() * SPEED_INCREASE);
      }
      return true;
    }
    timePassed++;
    return false;
  }

  @Override
  public String getImageString() {
    return IMAGE_STRING;
  }
}
