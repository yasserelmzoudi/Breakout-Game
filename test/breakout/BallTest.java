package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class BallTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;

  private Ball myBall;
  private Circle myBallCircle;
  private Display myDisplay;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myBall = myGame.getBall();
    myBallCircle = myBall.getCircle();
    myDisplay = myGame.getDisplay();
  }

  @Test
  public void testBounceOffPaddle() throws IOException, URISyntaxException {
    while (myBall.getVerticalSpeed() > 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(-1 * Ball.VERTICAL_SPEED, myBall.getVerticalSpeed());
  }

  @Test
  public void testBallReset() throws IOException, URISyntaxException {
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
    assertEquals(Display.MAX_LIVES - Game.DIFFICULTY - 1, myDisplay.getLives());
  }

}