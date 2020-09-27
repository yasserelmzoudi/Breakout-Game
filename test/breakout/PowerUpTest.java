package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class PowerUpTest extends DukeApplicationTest {

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
  public void testPowerUpFall() {
    PowerUp myPowerUp = new MultiBallPowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myGame.getFallingPowerUps().add(myPowerUp);
    myGame.step(Game.SECOND_DELAY);
    assertEquals(Game.SIZE / 2 + PowerUp.SPEED * Game.SECOND_DELAY + PowerUp.HEIGHT,
        myPowerUp.getBottom());
  }

  @Test
  public void testMultiBallPowerUp() {
    PowerUp myPowerUp = new MultiBallPowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myGame.getFallingPowerUps().add(myPowerUp);
    while (myGame.getBalls().size() == 1) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(3, myGame.getBalls().size());
  }

  @Test
  public void testExtendedPaddlePowerUp() {
    PowerUp myPowerUp = new ExtendedPaddlePowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myGame.getFallingPowerUps().add(myPowerUp);
    while (myGame.getPaddle().getRectangle().getWidth() == Paddle.STARTING_WIDTH) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Paddle.STARTING_WIDTH * ExtendedPaddlePowerUp.WIDTH_INCREASE,
        myGame.getPaddle().getRectangle().getWidth());
  }

}