package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class PaddleTest extends DukeApplicationTest {

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
  public void testPaddleOnWall() {
    myPaddleRectangle.setX(0);
    press(myScene, KeyCode.LEFT);
    assertEquals(0, myPaddleRectangle.getX());

    myPaddleRectangle.setX(Game.SIZE - Paddle.LENGTH);
    press(myScene, KeyCode.RIGHT);
    assertEquals(Game.SIZE - Paddle.LENGTH, myPaddleRectangle.getX());
  }


}