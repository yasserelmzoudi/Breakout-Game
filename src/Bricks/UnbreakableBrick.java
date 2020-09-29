package Bricks;

import Breakout.Display;
import PowerUps.PowerUp;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class UnbreakableBrick extends Brick {

  public static final Color UNBREAKABLE_COLOR = Color.DARKGRAY;

  public UnbreakableBrick(int x, int y) {
    super(x, y);
    setColor(UNBREAKABLE_COLOR);
    getRectangle().setFill(UNBREAKABLE_COLOR);
  }

  @Override
  public void activateBrick(Display display, Group root, List<Brick> bricks,
      List<PowerUp> powerUps) {
  }

  @Override
  public int getScore() {
    return 0;
  }
}
