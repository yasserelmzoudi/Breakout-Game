package PowerUps;

import Breakout.Game;
import Breakout.Paddle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class PowerUp {

  public static final double SPEED = 20;
  public static final double LENGTH = 25;
  public static final double HEIGHT = 15;
  public static final Color TEXT_COLOR = Color.BLACK;
  public static final Color BACKGROUND_COLOR = Color.ORANGE;
  public static final double MAX_POWER_UP_TIME = Game.FRAMES_PER_SECOND * 15;

  private Text myText;
  private Rectangle myRectangle;

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

  public boolean collisionWithPaddle(Paddle paddle) {
    return getBottom() >= Paddle.STARTING_Y && getTop() <= Paddle.STARTING_Y + Paddle.HEIGHT
        && getRightSideX() >= paddle.getLeftSideX()
        && getLeftSideX() <= paddle.getRightSideX();
  }

  public static PowerUp powerUpGenerator(double x, double y) {
    PowerUp newPowerUp;
    int randomPowerUpType = ThreadLocalRandom.current().nextInt(0, 3);
    switch(randomPowerUpType) {
      case 0 -> newPowerUp = new MultiBallPowerUp(x, y);
      case 1 -> newPowerUp = new ExtendedPaddlePowerUp(x, y);
      default -> newPowerUp = new SlowBallPowerUp(x, y);
    }
    return newPowerUp;
  }

  public abstract void activate(Game game);

  public abstract boolean deactivate(Game game);

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
