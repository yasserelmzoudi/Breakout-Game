package breakout;



import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


public class GameTest extends ApplicationTest {
  private final Game myGame = new Game();
  private Scene myScene;
  private Circle myBall;

  @Override
  public void start (Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    // find individual items within game by ID (must have been set in your code using setID())
    myBall = lookup("#ball").query();
  }

  @Test
  public void testInitialPositions () {
    assertEquals(Ball.STARTING_X, myBall.getCenterX());
    assertEquals(Ball.STARTING_Y, myBall.getCenterY());
    assertEquals(Ball.BALL_RADIUS, myBall.getRadius());
    // sleep(1, TimeUnit.SECONDS); // If you want to see the test
  }
}

