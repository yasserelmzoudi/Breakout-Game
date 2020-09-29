package Breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Bricks.BasicBrick;
import Bricks.Brick;
import Bricks.MultiHitBrick;
import Bricks.UnbreakableBrick;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class BrickTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;

  private Ball myBall;
  private Circle myBallCircle;
  private List<Brick> myBricks;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myBall = myGame.getBall();
    myBallCircle = myBall.getCircle();
    myBricks = myGame.getBricks();
  }

  @Test
  public void testBasicBrick() throws IOException, URISyntaxException {
    Brick brick = new BasicBrick((int) myBallCircle.getCenterX(),
        (int) myBallCircle.getCenterY() - 5);
    myBricks.add(brick);
    myBall.setVerticalSpeed(-80);
    while (myBall.getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertFalse(myBricks.contains(brick));
  }

  @Test
  public void testMultiHitBrick() throws IOException, URISyntaxException {
    Brick brick = new MultiHitBrick((int) myBallCircle.getCenterX(),
        (int) myBallCircle.getCenterY() - 5);
    myBricks.add(brick);
    myBall.setVerticalSpeed(-80);
    while (myBall.getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Brick.BRICK_BASE_COLOR.brighter(), brick.getColor());

    myBall.setVerticalSpeed(-80);
    while (myBall.getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Brick.BRICK_BASE_COLOR.brighter().brighter(), brick.getColor());

    myBall.setVerticalSpeed(-80);
    while (myBall.getVerticalSpeed() < 0) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertFalse(myBricks.contains(brick));

  }

  @Test
  public void testUnbreakableBrick() throws IOException, URISyntaxException {
    Brick brick = new UnbreakableBrick((int) myBallCircle.getCenterX(),
        (int) myBallCircle.getCenterY() - 5);
    myBricks.add(brick);
    for (int hitNumber = 0; hitNumber < 10; hitNumber++) {
      myBall.setVerticalSpeed(-80);
      while (myBall.getVerticalSpeed() < 0) {
        myGame.step(Game.SECOND_DELAY);
      }
    }
    assertTrue(myBricks.contains(brick));
  }

}