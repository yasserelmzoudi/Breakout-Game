package Breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import PowerUps.ExtendedPaddlePowerUp;
import PowerUps.MultiBallPowerUp;
import PowerUps.PowerUp;
import PowerUps.SlowBallPowerUp;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class PowerUpTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;

  private Paddle myPaddle;
  private Rectangle myPaddleRectangle;
  private Ball myBall;
  private List<Ball> myBalls;
  private List<PowerUp> myFallingPowerUps;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myPaddle = myGame.getPaddle();
    myPaddleRectangle = myPaddle.getRectangle();
    myBall = myGame.getBall();
    myBalls = myGame.getBalls();
    myFallingPowerUps = myGame.getFallingPowerUps();
  }

  @Test
  public void testPowerUpFall() throws IOException, URISyntaxException {
    PowerUp myPowerUp = new MultiBallPowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myFallingPowerUps.add(myPowerUp);
    myGame.step(Game.SECOND_DELAY);
    assertEquals(Game.SIZE / 2 + PowerUp.SPEED * Game.SECOND_DELAY + PowerUp.HEIGHT,
        myPowerUp.getBottom());
  }

  @Test
  public void testMultiBallPowerUp() throws IOException, URISyntaxException {
    PowerUp myPowerUp = new MultiBallPowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myFallingPowerUps.add(myPowerUp);
    while (myBalls.size() == 1) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(3, myBalls.size());
  }

  @Test
  public void testExtendedPaddlePowerUp() throws IOException, URISyntaxException {
    PowerUp myPowerUp = new ExtendedPaddlePowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myFallingPowerUps.add(myPowerUp);
    while (myPaddleRectangle.getWidth() == Paddle.STARTING_WIDTH) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Paddle.STARTING_WIDTH * ExtendedPaddlePowerUp.WIDTH_INCREASE,
        myPaddleRectangle.getWidth());
  }

  @Test
  public void testSlowBallPowerUp() throws IOException, URISyntaxException {
    PowerUp myPowerUp = new SlowBallPowerUp(Game.SIZE / 2, Game.SIZE / 2);
    myFallingPowerUps.add(myPowerUp);
    while (Math.abs(myBall.getVerticalSpeed()) == Ball.VERTICAL_SPEED) {
      myGame.step(Game.SECOND_DELAY);
    }
    assertEquals(Ball.VERTICAL_SPEED * SlowBallPowerUp.SPEED_DECREASE,
        Math.abs(myBall.getVerticalSpeed()));
    assertEquals(Ball.HORIZONTAL_SPEED * SlowBallPowerUp.SPEED_DECREASE,
        Math.abs(myBall.getHorizontalSpeed()));
  }

}