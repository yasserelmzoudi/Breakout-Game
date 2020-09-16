package breakout;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;
  private Rectangle myPaddleRectangle;
  private Circle myBall;
  private Rectangle myBlockRectangle;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    // find individual items within game by ID (must have been set in your code using setID())
    myPaddleRectangle = lookup("#paddle").query();
    myBall = lookup("#ball").query();
  }

  @Test
  public void testInitialPaddlePositions() {
    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
    assertEquals(Paddle.LENGTH, myPaddleRectangle.getWidth());
    assertEquals(Paddle.HEIGHT, myPaddleRectangle.getHeight());
    // sleep(1, TimeUnit.SECONDS); // If you want to see the test
  }

  @Test
  public void testInitialBallPosition() {
    assertEquals(Ball.STARTING_X, myBall.getCenterX());
    assertEquals(Ball.STARTING_Y, myBall.getCenterY());
    assertEquals(Ball.BALL_RADIUS, myBall.getRadius());
    assertEquals(0, myGame.getBall().getHorizontalSpeed());
    assertEquals(Ball.VERTICAL_SPEED, myGame.getBall().getVerticalSpeed());
    // sleep(1, TimeUnit.SECONDS); // If you want to see the test
  }

  @Test
  public void testInitialBlockPositions() {
    myBlockRectangle = lookup("#block00").query();
    assertEquals(0, myBlockRectangle.getX());
    assertEquals(0, myBlockRectangle.getY());

    myBlockRectangle = lookup("#block040").query();
    assertEquals(0, myBlockRectangle.getX());
    assertEquals(40, myBlockRectangle.getY());

    myBlockRectangle = lookup("#block080").query();
    assertEquals(0, myBlockRectangle.getX());
    assertEquals(80, myBlockRectangle.getY());
    // sleep(1, TimeUnit.SECONDS); // If you want to see the test
  }

  @Test
  public void testPaddleMovement() {
    myPaddleRectangle.setX(Paddle.STARTING_X);
    myPaddleRectangle.setY(Paddle.STARTING_Y);

    //sleep(1, TimeUnit.SECONDS);
    press(myScene, KeyCode.LEFT);
    //sleep(1, TimeUnit.SECONDS);

    assertEquals(Paddle.STARTING_X - Paddle.SPEED, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());

    //sleep(1, TimeUnit.SECONDS);
    press(myScene, KeyCode.RIGHT);
    //sleep(1, TimeUnit.SECONDS);

    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
  }

  @Test
  public void testBounceOffPaddle() {
    while (myGame.getBall().getVerticalSpeed() > 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(-1 * Ball.VERTICAL_SPEED, myGame.getBall().getVerticalSpeed());
  }

  @Test
  public void testBallReset() {
    double ballBefore = myBall.getCenterY();
    double ballAfter = myBall.getCenterY();

    while (ballBefore <= ballAfter) {
      press(myScene, KeyCode.LEFT);
      ballBefore = myBall.getCenterY();
      myGame.step(Game.SECOND_DELAY);
      ballAfter = myBall.getCenterY();
    }
    assertEquals(Ball.STARTING_X, myBall.getCenterX());
    assertEquals(Ball.STARTING_X, myBall.getCenterY());
  }

  @Test
  public void testReset() {
    //sleep(1, TimeUnit.SECONDS);
    press(myScene, KeyCode.R);
    //sleep(1, TimeUnit.SECONDS);

    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
    assertEquals(Ball.STARTING_X, myBall.getCenterX());
    assertEquals(Ball.STARTING_Y, myBall.getCenterY());
  }

  @Test
  public void testPause() {
    myPaddleRectangle.setX(Paddle.STARTING_X);
    myPaddleRectangle.setY(Paddle.STARTING_Y);

    press(myScene, KeyCode.SPACE);
    press(myScene, KeyCode.RIGHT);

    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
  }

}

