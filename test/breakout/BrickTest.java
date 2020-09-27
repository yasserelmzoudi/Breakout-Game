package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

class BrickTest extends DukeApplicationTest {

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
  public void testBasicBrick() {
    Brick brick = new BasicBrick(myBallCircle.getCenterX(), myBallCircle.getCenterY() - 5);
    myGame.getBricks().add(brick);
    myGame.getBall().setVerticalSpeed(-80);
    while (myGame.getBall().getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertFalse(myGame.getBricks().contains(brick));
  }

  @Test
  public void testMultiHitBrick() {
    Brick brick = new MultiHitBrick(myBallCircle.getCenterX(), myBallCircle.getCenterY() - 5);
    myGame.getBricks().add(brick);
    myGame.getBall().setVerticalSpeed(-80);
    while (myGame.getBall().getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Brick.BRICK_BASE_COLOR.brighter(), brick.getColor());

    myGame.getBall().setVerticalSpeed(-80);
    while (myGame.getBall().getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Brick.BRICK_BASE_COLOR.brighter().brighter(), brick.getColor());

    myGame.getBall().setVerticalSpeed(-80);
    while (myGame.getBall().getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertFalse(myGame.getBricks().contains(brick));

  }

  @Test
  public void testUnbreakableBrick() {
    Brick brick = new UnbreakableBrick(myBallCircle.getCenterX(), myBallCircle.getCenterY() - 5);
    myGame.getBricks().add(brick);
    for (int hitNumber = 0; hitNumber < 10; hitNumber++) {
      myGame.getBall().setVerticalSpeed(-80);
      while (myGame.getBall().getVerticalSpeed() < 0) {
        myGame.step(Game.SECOND_DELAY);
      }
    }
    assertTrue(myGame.getBricks().contains(brick));
  }

}