package PowerUps;

import Breakout.Game;
import Breakout.Paddle;

public class ExtendedPaddlePowerUp extends PowerUp {

  public static final double WIDTH_INCREASE = 1.5;
  private static final String IMAGE_STRING = "EP";

  private double timePassed = 0;

  public ExtendedPaddlePowerUp(double x, double y) {
    super(x, y);
  }

  @Override
  public void activate(Game game) {
    Paddle paddle = game.getPaddle();
    paddle.setWidth(paddle.getRectangle().getWidth() * WIDTH_INCREASE);
  }

  @Override
  public boolean deactivate(Game game) {
    if (timePassed >= PowerUp.MAX_POWER_UP_TIME) {
      Paddle paddle = game.getPaddle();
      paddle.setWidth(Paddle.STARTING_WIDTH);
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
