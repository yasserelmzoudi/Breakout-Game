package breakout;

import javafx.scene.Group;
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

  private double getLeftSideX() {
    return myRectangle.getX();
  }

  private double getRightSideX() {
    return myRectangle.getX() + LENGTH;
  }

  private double getBottom() {
    return myRectangle.getY() + HEIGHT;
  }

  public void fallFromDestroyedBlock(Group root, double elapsedTime, Paddle paddle) {
    myText.setY(myText.getY() + elapsedTime * SPEED);
    myRectangle.setY(myRectangle.getY() + elapsedTime * SPEED);
    if(collisionWithPaddle(paddle)){
      activate(root);
      root.getChildren().remove(myText);
      root.getChildren().remove(myRectangle);
    }
  }

  public boolean collisionWithPaddle(Paddle paddle) {
    return getBottom() <= Paddle.STARTING_Y && getRightSideX() >= paddle.getLeftSideX()
        && getLeftSideX() <= paddle.getRightSideX();
  }

  public abstract void activate(Group root);

  public abstract String getImageString();
}
