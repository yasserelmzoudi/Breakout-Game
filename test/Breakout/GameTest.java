package Breakout;

import Bricks.Brick;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
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
  private Paddle myPaddle;
  private Ball myBall;
  private List<Brick> myBricks;
  private Display myDisplay;

  private Rectangle myPaddleRectangle;
  private Circle myBallCircle;
  private Rectangle myBrickRectangle;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myPaddle = myGame.getPaddle();
    myPaddleRectangle = myPaddle.getRectangle();
    myBall = myGame.getBall();
    myBallCircle = myBall.getCircle();
    myBricks = myGame.getBricks();
    myDisplay = myGame.getDisplay();
  }

  @Test
  public void testInitialPaddlePositions() {
    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
    assertEquals(Paddle.STARTING_WIDTH, myPaddleRectangle.getWidth());
    assertEquals(Paddle.HEIGHT, myPaddleRectangle.getHeight());
  }

  @Test
  public void testInitialBallPosition() {
    assertEquals(Ball.STARTING_X, myBallCircle.getCenterX());
    assertEquals(Ball.STARTING_Y, myBallCircle.getCenterY());
    assertEquals(Ball.BALL_RADIUS, myBallCircle.getRadius());
    assertEquals(0, myBall.getHorizontalSpeed());
    assertEquals(Ball.VERTICAL_SPEED, myGame.getBall().getVerticalSpeed());
  }

  @Test
  public void testInitialBlockPositions() {
    myBrickRectangle = lookup("#block00").query();
    assertEquals(0, myBrickRectangle.getX());
    assertEquals(0, myBrickRectangle.getY());

    myBrickRectangle = lookup("#block040").query();
    assertEquals(0, myBrickRectangle.getX());
    assertEquals(40, myBrickRectangle.getY());

    myBrickRectangle = lookup("#block080").query();
    assertEquals(0, myBrickRectangle.getX());
    assertEquals(80, myBrickRectangle.getY());
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
  public void testBlockHit() throws IOException, URISyntaxException {
    myBall.setVerticalSpeed(-80);
    int brickNum = myBricks.size();
    while (myBall.getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(brickNum - 1, myBricks.size());
    assertEquals(Game.POINTS_FOR_HITTING_BRICK, myDisplay.getScore());
  }

  @Test
  public void testReset() {
    press(myScene, KeyCode.R);

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
    assertEquals(Display.MAX_LIVES - Game.DIFFICULTY + 1, myDisplay.getLives());
  }

  @Test
  public void testBreakBlock() {
    int brickNum = myBricks.size();
    press(myScene, KeyCode.B);
    assertEquals(brickNum - 1, myBricks.size());
  }

}

