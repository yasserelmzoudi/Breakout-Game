package breakout;

import javafx.scene.Group;

public class MultiBallPowerUp extends PowerUp{

  private static final String IMAGE_STRING = "MB";

  public MultiBallPowerUp(double startingX, double startingY) {
    super(startingX, startingY);
  }

  @Override
  public void activate(Group root) {
    Ball ball = new Ball();

  }

  @Override
  public String getImageString() {
    return IMAGE_STRING;
  }
}
