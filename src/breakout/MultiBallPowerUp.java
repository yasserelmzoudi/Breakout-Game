package breakout;

import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Platform;

public class MultiBallPowerUp extends PowerUp {

  private static final String IMAGE_STRING = "MB";

  public MultiBallPowerUp(double startingX, double startingY) {
    super(startingX, startingY);
  }

  @Override
  public void activate(Game game) {
    for (int i = 0; i < 2; i++) {
      Ball ball = new Ball();
      Platform.runLater(() -> game.getRoot().getChildren().add(ball.getCircle()));
      game.getBalls().add(ball);
      ball.setHorizontalSpeed(
          ThreadLocalRandom.current().nextDouble(-Ball.HORIZONTAL_SPEED, Ball.HORIZONTAL_SPEED));
    }
  }

  @Override
  public boolean deactivate(Game game) {
    return true;
  }


  @Override
  public String getImageString() {
    return IMAGE_STRING;
  }
}
