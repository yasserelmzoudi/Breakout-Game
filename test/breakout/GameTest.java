package breakout;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;
  private Rectangle myPaddleRectangle;
  private Circle myBallCircle;
  private Rectangle myBlockRectangle;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    // find individual items within game by ID (must have been set in your code using setID())
    myPaddleRectangle = lookup("#paddle").query();
    myBallCircle = myGame.getBall().getCircle();
  }

  @Test
  public void testInitialPaddlePositions() {
    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
    assertEquals(Paddle.STARTING_WIDTH, myPaddleRectangle.getWidth());
    assertEquals(Paddle.HEIGHT, myPaddleRectangle.getHeight());
    // sleep(1, TimeUnit.SECONDS); // If you want to see the test
  }

  @Test
  public void testInitialBallPosition() {
    assertEquals(Ball.STARTING_X, myBallCircle.getCenterX());
    assertEquals(Ball.STARTING_Y, myBallCircle.getCenterY());
    assertEquals(Ball.BALL_RADIUS, myBallCircle.getRadius());
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
  public void testInitialDisplayPosition() {
    Text myScoreText = lookup("#scoreText").query();
    Text myLivesText = lookup("#livesText").query();

    assertEquals(Display.TEXT_LOCATION, myLivesText.getX());
    assertEquals(Display.TEXT_LOCATION, myScoreText.getX());

    assertEquals(Game.SIZE - Display.TEXT_LOCATION - Display.TEXT_OFFSET, myLivesText.getY());
    assertEquals(Game.SIZE - Display.TEXT_LOCATION, myScoreText.getY());
  }

  @Test
  public void testBlockHit() {
    myGame.getBall().setVerticalSpeed(-80);
    int brickNum = myGame.getBricks().size();
    while (myGame.getBall().getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(brickNum - 1, myGame.getBricks().size());
    assertEquals(Game.POINTS_FOR_HITTING_BRICK, myGame.getDisplay().getScore());
  }

  @Test
  public void testReset() {
    //sleep(1, TimeUnit.SECONDS);
    press(myScene, KeyCode.R);
    //sleep(1, TimeUnit.SECONDS);

    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
    assertEquals(Ball.STARTING_X, myBallCircle.getCenterX());
    assertEquals(Ball.STARTING_Y, myBallCircle.getCenterY());
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

  @Test
  public void testPowerUpCheat() {
    press(myScene, KeyCode.P);
    assertEquals(1, myGame.getFallingPowerUps().size());
  }

  @Test
  public void testExtraLife() {
    press(myScene, KeyCode.L);
    assertEquals(Display.MAX_LIVES - Game.DIFFICULTY + 1, myGame.getDisplay().getLives());
  }

  @Test
  public void testBreakBlock() {
    int brickNum = myGame.getBricks().size();
    press(myScene, KeyCode.B);
    assertEquals(brickNum - 1, myGame.getBricks().size());
  }

}

