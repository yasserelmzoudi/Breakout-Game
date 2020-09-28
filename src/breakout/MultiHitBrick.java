package breakout;

import java.util.List;
import javafx.scene.Group;

public class MultiHitBrick extends Brick {

  private static final int STARTING_HEALTH = 3;

  private int myHealth;
  private int myScore;

  public MultiHitBrick(int x, int y) {
    super(x, y);
    myScore = BasicBrick.POINTS_FOR_BASIC_BRICK * STARTING_HEALTH;
    myHealth = STARTING_HEALTH;
    setInitialColor(myHealth);
  }

  private void setInitialColor(int health) {
    for (int colorChanges = 1; colorChanges < health; colorChanges++) {
      setColor(getColor().darker());
      getRectangle().setFill(getColor());
    }
  }

  @Override
  public void activateBrick(Display display, Group root, List<Brick> bricks,
      List<PowerUp> powerUps) {
    brickHit();
    if (myHealth <= 0) {
      display.changeScore(myScore);
      destroyBrick(root, bricks, powerUps);
    }
  }

  @Override
  public int getScore() {
    return myScore;
  }

  private void brickHit() {
    setColor(getColor().brighter());
    getRectangle().setFill(getColor());
    myHealth--;
  }

}
