package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class PowerUp {

  public static final double SPEED = 20;
  public static final double LENGTH = 25;
  public static final double HEIGHT = 15;
  public static final Color TEXT_COLOR = Color.BLACK;
  public static final Color BACKGROUND_COLOR = Color.ORANGE;
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

  public double getLeftSideX() {
    return myRectangle.getX();
  }

  public double getRightSideX() {
    return myRectangle.getX() + LENGTH;
  }

  public double getBottom() {
    return myRectangle.getY() + HEIGHT;
  }

  public void fallFromDestroyedBlock(Game game, double elapsedTime) {
    myText.setY(myText.getY() + elapsedTime * SPEED);
    myRectangle.setY(myRectangle.getY() + elapsedTime * SPEED);
    if (collisionWithPaddle(game.getPaddle())) {
      activate(game);
      game.getRoot().getChildren().remove(myText);
      game.getRoot().getChildren().remove(myRectangle);
    }
  }

  public boolean collisionWithPaddle(Paddle paddle) {
    return getBottom() >= Paddle.STARTING_Y && getRightSideX() >= paddle.getLeftSideX()
        && getLeftSideX() <= paddle.getRightSideX();
  }

  public abstract void activate(Game game);

  public abstract String getImageString();
}
