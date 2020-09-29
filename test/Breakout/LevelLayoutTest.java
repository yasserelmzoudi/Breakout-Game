package Breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Bricks.Brick;
import Bricks.UnbreakableBrick;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class LevelLayoutTest extends DukeApplicationTest{

  private final Game myGame = new Game();
  private Scene myScene;
  private Paddle myPaddle;
  private Ball myBall;
  private List<Brick> myBricks;
  private Rectangle myBrickRectangle;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myPaddle = myGame.getPaddle();
    myBall = myGame.getBall();
    myBricks = myGame.getBricks();
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
  public void testLevel1Layout() {
    try {
      myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    myBricks = myGame.getBricks();
    assertEquals(Brick.BRICK_BASE_COLOR, myBricks.get(10).getColor());
  }

  @Test
  public void testLevel2Layout() {
    try {
      myScene = myGame.setupScene(2, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    myBricks = myGame.getBricks();
    assertEquals(Brick.BRICK_BASE_COLOR.darker().darker(), myBricks.get(10).getColor());
  }

  @Test
  public void testLevel3Layout() {
    try {
      myScene = myGame.setupScene(3, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    myBricks = myGame.getBricks();
    assertEquals(UnbreakableBrick.UNBREAKABLE_COLOR, myBricks.get(9).getColor());
  }

}