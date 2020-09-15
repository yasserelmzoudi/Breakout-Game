package breakout;

import java.util.concurrent.TimeUnit;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest extends ApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;
  private Rectangle myPaddleRectangle;
  private Circle myBall;

  @Override
  public void start(Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    // find individual items within game by ID (must have been set in your code using setID())
    myPaddleRectangle = lookup("#paddle").query();
    myBall = lookup("#ball").query();
  }

  @Test
  public void testInitialPositions() {
    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
    assertEquals(Paddle.LENGTH, myPaddleRectangle.getWidth());
    assertEquals(Paddle.HEIGHT, myPaddleRectangle.getHeight());
    
    assertEquals(Ball.STARTING_X, myBall.getCenterX());
    assertEquals(Ball.STARTING_Y, myBall.getCenterY());
    assertEquals(Ball.BALL_RADIUS, myBall.getRadius());
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

  private void press(Scene myScene, KeyCode key) {
    myScene.processKeyEvent(
        new KeyEvent(KeyEvent.KEY_PRESSED, key.getChar(), key.getName(), key, false, false, false,
            false));
  }
}

