package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class PaddleTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;

  private Paddle myPaddle;
  private Rectangle myPaddleRectangle;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myPaddle = myGame.getPaddle();
    myPaddleRectangle = myPaddle.getRectangle();
  }

  @Test
  public void testPaddleMovement() {
    myPaddleRectangle.setX(Paddle.STARTING_X);
    myPaddleRectangle.setY(Paddle.STARTING_Y);

    press(myScene, KeyCode.LEFT);

    assertEquals(Paddle.STARTING_X - Paddle.SPEED, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());

    press(myScene, KeyCode.RIGHT);

    assertEquals(Paddle.STARTING_X, myPaddleRectangle.getX());
    assertEquals(Paddle.STARTING_Y, myPaddleRectangle.getY());
  }

  @Test
  public void testPaddleOnWall() {
    myPaddleRectangle.setX(0);
    press(myScene, KeyCode.LEFT);
    assertEquals(0, myPaddleRectangle.getX());

    myPaddleRectangle.setX(Game.SIZE - Paddle.STARTING_WIDTH);
    press(myScene, KeyCode.RIGHT);
    assertEquals(Game.SIZE - Paddle.STARTING_WIDTH, myPaddleRectangle.getX());
  }


}