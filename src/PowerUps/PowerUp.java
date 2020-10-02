package PowerUps;

import Breakout.Game;
import Breakout.Paddle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Abstraction representing the different type of PowerUps that are possible
 *
 * @author Yasser Elmzoud, Jack Ellwood
 */
public abstract class PowerUp {

  public static final double SPEED = 20;
  public static final double LENGTH = 25;
  public static final double HEIGHT = 15;
  public static final Color TEXT_COLOR = Color.BLACK;
  public static final Color BACKGROUND_COLOR = Color.ORANGE;
  public static final double MAX_POWER_UP_TIME = Game.FRAMES_PER_SECOND * 15;

  private final Text myText;
  private final Rectangle myRectangle;

  /**
   * Constructs a PowerUp at the given location
   * @param x double representing x-coordinate of PowerUp
   * @param y double representing y-coordinate of PowerUp
   */
  public PowerUp(double x, double y) {
    myText = new Text(getImageString());
    myText.setX(x);
    myText.setY(y);
    myText.setFill(TEXT_COLOR);
    myText.setId("powerUpText" + x + y);

    myRectangle = new Rectangle(x, y, LENGTH, HEIGHT);
    myRectangle.setFill(BACKGROUND_COLOR);
    myRectangle.setId("powerUpRectangle" + x + y);
  }

  /**
   * Generates a random PowerUp from possible PowerUps
   * @param x double representing x-coordinate of PowerUp
   * @param y double representing y-coordinate of PowerUp
   * @return PowerUp representing randomly generated PowerUp
   */
  public static PowerUp powerUpGenerator(double x, double y) {
    PowerUp newPowerUp;
    int randomPowerUpType = ThreadLocalRandom.current().nextInt(0, 3);
    switch (randomPowerUpType) {
      case 0 -> newPowerUp = new MultiBallPowerUp(x, y);
      case 1 -> newPowerUp = new ExtendedPaddlePowerUp(x, y);
      default -> newPowerUp = new SlowBallPowerUp(x, y);
    }
    return newPowerUp;
  }

  /**
   * Causes a PowerUp to fall from a destroyed Brick
   * @param game Game in which the PowerUp falls
   * @param elapsedTime double representing time that has passed
   * @return boolean representing if PowerUp collided with a Paddle
   */
  public boolean fallFromDestroyedBlock(Game game, double elapsedTime) {
    myText.setY(myText.getY() + elapsedTime * SPEED);
    myRectangle.setY(myRectangle.getY() + elapsedTime * SPEED);
    if (collisionWithPaddle(game.getPaddle())) {
      activate(game);
      game.getRoot().getChildren().remove(myText);
      game.getRoot().getChildren().remove(myRectangle);
      return true;
    }
    return false;
  }

  /**
   * Checks to see if PowerUp has collided with Paddle
   * @param paddle Paddle to be checked for collision with PowerUp
   * @return boolean representing if Paddle has collided with PowerUp
   */
  public boolean collisionWithPaddle(Paddle paddle) {
    return getBottom() >= Paddle.STARTING_Y && getTop() <= Paddle.STARTING_Y + Paddle.HEIGHT
        && getRightSideX() >= paddle.getLeftX()
        && getLeftSideX() <= paddle.getRightX();
  }

  /**
   * Activates the ability of the PowerUp and applies it to the given Game
   * @param game Game that is to be adjusted based on the PowerUp's abilities
   */
  public abstract void activate(Game game);

  /**
   * Deactivates the ability of the PowerUp and removes it from the given Game
   * @param game Game that is to be adjusted based on the PowerUp's abilities
   * @return boolean representing if PowerUp has been deactivated
   */
  public abstract boolean deactivate(Game game);

  /**
   * Returns String representing name of PowerUp
   * @return String representing name of PowerUp
   */
  public abstract String getImageString();

  private double getLeftSideX() {
    return myRectangle.getX();
  }

  private double getRightSideX() {
    return myRectangle.getX() + LENGTH;
  }

  public double getTop() {
    return myRectangle.getY();
  }

  public double getBottom() {
    return myRectangle.getY() + HEIGHT;
  }

  public Rectangle getRectangle() {
    return myRectangle;
  }

  public Text getText() {
    return myText;
  }
}
