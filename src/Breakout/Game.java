package Breakout;

import Bricks.Brick;
import PowerUps.PowerUp;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The actual application which manages the various game objects and processes.
 */
public class Game extends Application {

  public static final String TITLE = "Jack and Yasser's Breakout";
  public static final int SIZE = 400;
  public static final int FRAMES_PER_SECOND = 120;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
  public static final String LEVEL = "level1.txt";
  public static final int MAIN_BALL = 0;
  public static final int DIFFICULTY = 1;
  public static final int POWER_UP_SPAWN_CHANCE = 10;
  public static final int POINTS_FOR_HITTING_BRICK = 100;

  private Scene myScene;
  private Group myRoot;
  private Stage stage;
  private Paddle myPaddle;
  private Display myDisplay;
  private List<Ball> myBalls;
  private List<Brick> myBricks;
  private List<PowerUp> myFallingPowerUps;
  private List<PowerUp> myActivePowerUps;
  private int myUnbreakableBricks = 0;
  private Timeline animation;

  private boolean paused = false;
  private int currentLevelNumber = 1;
  private LevelLayout level;

  /**
   * Begins the application by opening a window with objects initialized
   *
   * @param stage the stage used to display the application
   */
  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    this.stage = stage;
    myScene = setupScene(currentLevelNumber, SIZE, SIZE, BACKGROUND);
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      try {
        step(SECOND_DELAY);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      } catch (URISyntaxException uriSyntaxException) {
        uriSyntaxException.printStackTrace();
      }
    });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  Scene setupScene(int levelNumber, int width, int height, Paint background) throws IOException, URISyntaxException {
    myRoot = new Group();
    currentLevelNumber = levelNumber;
    level = new LevelLayout(myRoot, currentLevelNumber);
    myBricks = new ArrayList<>();
    myUnbreakableBricks = 0;
    level.buildBlocksFromFile(myRoot, currentLevelNumber, myBricks, myUnbreakableBricks);
    myPaddle = new Paddle();
    myRoot.getChildren().add(myPaddle.getRectangle());

    myFallingPowerUps = new ArrayList<>();
    myActivePowerUps = new ArrayList<>();

    myBalls = new ArrayList<>();
    myBalls.add(new Ball());
    myRoot.getChildren().add(myBalls.get(MAIN_BALL).getCircle());

    myDisplay = new Display(DIFFICULTY);
    myRoot.getChildren().add(myDisplay.getScoreText());
    myRoot.getChildren().add(myDisplay.getLivesText());

    Scene scene = new Scene(myRoot, width, height, background);
    scene.setOnKeyPressed(key -> handleKeyInput(key.getCode()));
    return scene;
  }

  private void handleKeyInput(KeyCode code) {
    if (!paused) {
      myPaddle.movePaddle(code);
      checkCheatKey(code);
    }
    pause(code);
  }

  private void checkCheatKey(KeyCode code) {
    switch(code) {
      case R -> reset();
      case P -> dropPowerUp();
      case B -> breakBlock();
      case L -> extraLife();
    }
  }

  void step(double elapsedTime) throws IOException, URISyntaxException {
    if (!paused) {
      moveBalls(elapsedTime);
      movePowerUps(elapsedTime);
      checkBallBrickCollision();
      checkPowerUpDeactivate();
      checkLevelEnd();
    }
  }

  private void checkLevelEnd() throws IOException, URISyntaxException {
    if (isLevelEnd()) {
      animation.stop();
      currentLevelNumber++;
      start(stage);
    }
  }

  private boolean isLevelEnd() {
    return myBricks.size() == myUnbreakableBricks;
  }

  private void checkPowerUpDeactivate() {
    myActivePowerUps.removeIf(powerUp -> powerUp.deactivate(this));
  }

  private void moveBalls(double elapsedTime) {
    for (Ball ball : myBalls) {
      ball.ballMovement(elapsedTime);
      ball.checkPaddleHit(myPaddle);
      ball.checkWallHit(myDisplay, myBalls, myRoot);
      if (ball.bottomSideWallHit()) {
        break;
      }
      if (myDisplay.isGameOver()) {
        gameOver("YOU LOSE!");
      }
    }
  }

  private void gameOver(String gameOverMessage) {
    myRoot.getChildren().clear();
    Text gameOverText = new Text(gameOverMessage);
    gameOverText.setTextOrigin(VPos.TOP);
    Font font = new Font(30);
    gameOverText.setFont(font);
    gameOverText.layoutXProperty()
        .bind(myScene.widthProperty().subtract(gameOverText.prefWidth(-1)).divide(2));
    gameOverText.layoutYProperty()
        .bind(myScene.heightProperty().subtract(gameOverText.prefHeight(-1)).divide(2));
    myRoot.getChildren().add(gameOverText);
  }

  private void checkBallBrickCollision() {
    for (Ball ball : myBalls) {
      checkBrickCollision(ball);
    }
  }

  private void checkBrickCollision(Ball ball) {
    for (Brick brick : myBricks) {
      if (ball.checkBrickHit(brick)) {
        brick.activateBrick(myDisplay, myRoot, myBricks, myFallingPowerUps);
        /*if (myBricks.size() == myUnbreakableBricks) {
          gameOver("YOU WIN!");
        }*/
        break;
      }
    }
  }

  private void movePowerUps(double elapsedTime) {
    for (PowerUp powerUp : myFallingPowerUps) {
      if (powerUp.fallFromDestroyedBlock(this, elapsedTime)) {
        Platform.runLater(() -> getFallingPowerUps().remove(powerUp));
        getActivePowerUps().add(powerUp);
      }
    }
  }

  private void reset() {
    myPaddle.getRectangle().setX(Paddle.STARTING_X);
    myPaddle.getRectangle().setY(Paddle.STARTING_Y);
    myBalls.get(MAIN_BALL).getCircle().setCenterX(Ball.STARTING_X);
    myBalls.get(MAIN_BALL).getCircle().setCenterY(Ball.STARTING_Y);
    myBalls.get(MAIN_BALL).setHorizontalSpeed(0);
    myBalls.get(MAIN_BALL).setVerticalSpeed(Ball.VERTICAL_SPEED);
  }

  private void dropPowerUp() {
    PowerUp powerUp = PowerUp.powerUpGenerator(SIZE / 2, SIZE / 2);
    myFallingPowerUps.add(powerUp);
    myRoot.getChildren().add(powerUp.getRectangle());
    myRoot.getChildren().add(powerUp.getText());
  }

  private void pause(KeyCode code) {
    if (code == KeyCode.SPACE) {
      paused = !paused;
    }
  }

  private void extraLife() {
    myDisplay.changeLives(1);
  }

  private void breakBlock() {
    Brick brick = myBricks.remove(0);
    brick.destroyBrick(myRoot, myBricks, myFallingPowerUps);
    myDisplay.changeScore(brick.getScore());
    /*if (myBricks.size() == myUnbreakableBricks) {
      gameOver("YOU WIN!");
    }*/
  }

  public Ball getBall() {
    return myBalls.get(MAIN_BALL);
  }

  public List<Ball> getBalls() {
    return myBalls;
  }

  public List<Brick> getBricks() {
    return myBricks;
  }

  public Paddle getPaddle() {
    return myPaddle;
  }

  public List<PowerUp> getActivePowerUps() {
    return myActivePowerUps;
  }

  public List<PowerUp> getFallingPowerUps() {
    return myFallingPowerUps;
  }

  public Group getRoot() {
    return myRoot;
  }

  public Display getDisplay() {
    return myDisplay;
  }

  /**
   * Used to launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

}
