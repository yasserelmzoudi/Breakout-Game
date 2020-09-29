package Bricks;

import Breakout.Display;
import PowerUps.PowerUp;
import java.util.List;
import javafx.scene.Group;

public class BasicBrick extends Brick {

  public static final int POINTS_FOR_BASIC_BRICK = 100;

  /**
   * Brick constructor
   *
   * @param x location of block
   * @param y location of block
   */
  public BasicBrick(int x, int y) {
    super(x, y);
  }

  public void activateBrick(Display display, Group root, List<Brick> bricks,
      List<PowerUp> powerUps) {
    display.changeScore(POINTS_FOR_BASIC_BRICK);
    destroyBrick(root, bricks, powerUps);
  }

  public int getScore() {
    return POINTS_FOR_BASIC_BRICK;
  }

}
