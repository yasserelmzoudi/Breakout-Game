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

public class BallTest extends DukeApplicationTest {

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
  public void testBounceOffPaddle() {
    while (myGame.getBall().getVerticalSpeed() > 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(-1 * Ball.VERTICAL_SPEED, myGame.getBall().getVerticalSpeed());
  }

  @Test
  public void testBallReset() {
    double ballBefore = myBallCircle.getCenterY();
    double ballAfter = myBallCircle.getCenterY();

    while (ballBefore <= ballAfter) {
      press(myScene, KeyCode.LEFT);
      ballBefore = myBallCircle.getCenterY();
      myGame.step(Game.SECOND_DELAY);
      ballAfter = myBallCircle.getCenterY();
    }
    assertEquals(Ball.STARTING_X, myBallCircle.getCenterX());
    assertEquals(Ball.STARTING_X, myBallCircle.getCenterY());
    assertEquals(Display.MAX_LIVES - Game.DIFFICULTY - 1, myGame.getDisplay().getLives());
  }

}