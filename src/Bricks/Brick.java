package Bricks;

import Breakout.Display;
import Breakout.Game;
import PowerUps.PowerUp;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the abstraction of Brick that all subclasses are made from
 *
 * @author Yasser Elmzoudi, Jack Ellwood
 */
public abstract class Brick {

  public static final int LENGTH = 40;
  public static final int HEIGHT = 20;
  public static final Color BRICK_BASE_COLOR = Color.CRIMSON;

  private final Rectangle myRectangle;
  private Color myColor;

  /**
   * Brick constructor
   *
   * @param x location of block
   * @param y location of block
   */
  public Brick(int x, int y) {
    myRectangle = new Rectangle(x, y, LENGTH, HEIGHT);
    myRectangle.setId("block" + x + y);
    myColor = BRICK_BASE_COLOR;
    myRectangle.setFill(myColor);
  }

  /**
   * Activates the brick by setting score and destruction when hit
   * @param display Display showing number of lives and score
   * @param root Group of Objects to be added to Game
   * @param bricks List of Bricks that should be updated once a Brick is destroyed
   * @param powerUps List of PowerUps that could spawn from a Brick
   */
  public abstract void activateBrick(Display display, Group root, List<Brick> bricks,
      List<PowerUp> powerUps);

  /**
   * Returns score of a Brick
   * @return int representing score of a Brick
   */
  public abstract int getScore();

  /**
   * Destroys a Brick and drops a random PowerUp
   * @param root Group of Objects to be added to Game
   * @param bricks List of Bricks that should be updated once a Brick is destroyed
   * @param powerUps List of PowerUps that could spawn from a Brick
   */
  public void destroyBrick(Group root, List<Brick> bricks, List<PowerUp> powerUps) {
    Platform.runLater(() -> root.getChildren().remove(myRectangle));
    bricks.remove(this);
    spawnPowerUp(root, powerUps);
  }

  private void spawnPowerUp(Group root, List<PowerUp> fallingPowerUps) {
    int randomPowerUpSeed = ThreadLocalRandom.current().nextInt(0, 100);
    if (randomPowerUpSeed < Game.POWER_UP_SPAWN_CHANCE) {
      PowerUp newPowerUp = PowerUp.powerUpGenerator(getX(), getY());
      Platform.runLater(() -> root.getChildren().add(newPowerUp.getRectangle()));
      Platform.runLater(() -> root.getChildren().add(newPowerUp.getText()));
      fallingPowerUps.add(newPowerUp);
    }
  }

  /**
   * Returns the Color of Brick
   * @return Color representing Color of Brick
   */
  public Color getColor() {
    return myColor;
  }

  protected void setColor(Color newColor) {
    myColor = newColor;
  }

  /**
   * Get's the Block object's rectangle instance variable
   *
   * @return myRectangle
   */
  public Rectangle getRectangle() {
    return myRectangle;
  }

  private double getX() {
    return myRectangle.getX();
  }

  private double getY() {
    return myRectangle.getY();
  }

  private double getHeight() {
    return myRectangle.getHeight();
  }

  private double getWidth() {
    return myRectangle.getWidth();
  }

  public double getRightX() {
    return getX() + getWidth();
  }

  public double getLeftX() {
    return getX();
  }

  public double getTopY() {
    return getY();
  }

  public double getBottomY() {
    return getY() + getHeight();
  }
}
