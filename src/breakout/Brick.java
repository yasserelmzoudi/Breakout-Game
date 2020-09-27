package breakout;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Brick {

  public static final int LENGTH = 40;
  public static final int HEIGHT = 20;
  public static final Color BRICK_BASE_COLOR = Color.CRIMSON;

  private Rectangle myRectangle;
  private Color myColor;

  /**
   * Block constructor
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
   * Get's the Block object's rectangle instance variable
   *
   * @return myRectangle
   */
  public Rectangle getRectangle() {
    return myRectangle;
  }

  public abstract void activateBrick(Display display, Group root, List<Brick> bricks,
      List<PowerUp> powerUps);

  public abstract int getScore();

  public void destroyBrick(Group root, List<Brick> bricks, List<PowerUp> powerUps) {
    Platform.runLater(() -> root.getChildren().remove(myRectangle));
    bricks.remove(this);
    spawnPowerUp(root, powerUps);
  }

  protected void spawnPowerUp(Group root, List<PowerUp> fallingPowerUps) {
    int randomPowerUpSeed = ThreadLocalRandom.current().nextInt(0, 100);
    if (randomPowerUpSeed < Game.POWER_UP_SPAWN_CHANCE) {
      PowerUp newPowerUp = PowerUp.powerUpGenerator(getX(), getY());
      Platform.runLater(() -> root.getChildren().add(newPowerUp.getRectangle()));
      Platform.runLater(() -> root.getChildren().add(newPowerUp.getText()));
      fallingPowerUps.add(newPowerUp);
    }
  }

  public Color getColor() {
    return myColor;
  }

  protected void setColor(Color newColor) {
    myColor = newColor;
  }

  public double getX() {
    return myRectangle.getX();
  }

  public double getY() {
    return myRectangle.getY();
  }

  public double getHeight() {
    return myRectangle.getHeight();
  }

  public double getWidth() {
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
