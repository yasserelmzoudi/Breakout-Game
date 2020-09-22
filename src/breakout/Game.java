package breakout;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
  public static final String LEVEL = "testlevel.txt";
  public static final int MAIN_BALL = 0;
  public static final int DIFFICULTY = 1;
  public static final int POINTS_FOR_HITTING_BLOCK = 100;

  private Scene myScene;
  private Group myRoot;
  private Paddle myPaddle;
  private Display myDisplay;
  private List<Ball> myBalls;
  private List<Block> myBricks;
  private List<PowerUp> myPowerUps;

  private boolean paused = false;

  /**
   * Begins the application by opening a window with objects initialized
   *
   * @param stage the stage used to display the application
   */
  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    myScene = setupScene(SIZE, SIZE, BACKGROUND);
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  Scene setupScene(int width, int height, Paint background) throws IOException, URISyntaxException {
    myRoot = new Group();

    myPaddle = new Paddle();
    myRoot.getChildren().add(myPaddle.getRectangle());

    myPowerUps = new ArrayList<>();

    myBalls = new ArrayList<>();
    myBalls.add(new Ball());
    myRoot.getChildren().add(myBalls.get(MAIN_BALL).getCircle());

    buildBlocksFromFile(LEVEL, myRoot);

    myDisplay = new Display(DIFFICULTY);
    myRoot.getChildren().add(myDisplay.getScoreText());
    myRoot.getChildren().add(myDisplay.getLivesText());

    Scene scene = new Scene(myRoot, width, height, background);
    scene.setOnKeyPressed(key -> handleKeyInput(key.getCode()));
    return scene;
  }

  /**
   * Builds the blocks for a level from a text file
   *
   * @param level filename of the level
   * @param root  of the JavaFX resource tree
   * @throws IOException
   * @throws URISyntaxException
   */
  public void buildBlocksFromFile(String level,
      Group root) // Maybe this method should be in Block.java?
      throws IOException, URISyntaxException {
    Path path = Paths
        .get(Objects.requireNonNull(Main.class.getClassLoader().getResource(level)).toURI());
    int currentX;
    int currentY = 0;

    myBricks = new ArrayList<>();

    for (String row : Files.readAllLines(path)) {
      currentX = 0;
      for (String space : row.split("")) {
        if (space.equalsIgnoreCase(Block.BLOCK_LETTER)) {
          Block block = new Block(currentX, currentY);
          myBricks.add(block);
          root.getChildren().add(block.getRectangle());
        }
        currentX += Block.LENGTH;
      }
      currentY += Block.HEIGHT;
    }
  }

  private void handleKeyInput(KeyCode code) {
    if (!paused) {
      myPaddle.movePaddle(code);
      reset(code);
      dropPowerUp(code);
      extraLife(code);
    }
    pause(code);
  }

  void step(double elapsedTime) {
    if (!paused) {
      moveBalls(elapsedTime);
      movePowerUps(elapsedTime);
      checkBallBrickCollision();
    }
  }

  private void moveBalls(double elapsedTime) {
    for (Ball ball : myBalls) {
      ball.ballMovement(elapsedTime);
      ball.checkPaddleHit(myPaddle);
      ball.checkWallHit(myDisplay, myBalls, myRoot);
    }
  }

  private void checkBallBrickCollision() {
    for (Ball ball : myBalls) {
      for (Block brick : myBricks) {
        if (ball.checkBrickHit(brick)) {
          myDisplay.changeScore(POINTS_FOR_HITTING_BLOCK);
          Platform.runLater(() -> myRoot.getChildren().remove(brick.getRectangle()));
          myBricks.remove(brick);
          spawnPowerUp(myRoot, myPowerUps, brick.getX(), brick.getY());
          break;
        }
      }
    }
  }

  private void spawnPowerUp(Group root, List<PowerUp> myPowerUps, double initialX, double initialY) {
    if(ThreadLocalRandom.current().nextInt(0, 100) < 10){
      PowerUp newPowerUp = new MultiBallPowerUp(initialX, initialY);
      root.getChildren().add(newPowerUp.getRectangle());
      root.getChildren().add(newPowerUp.getText());
      myPowerUps.add(newPowerUp);
    }
  }


  private void movePowerUps(double elapsedTime) {
    for (PowerUp powerUp : myPowerUps) {
      powerUp.fallFromDestroyedBlock(this, elapsedTime);
    }
  }

  private void reset(KeyCode code) {
    if (code == KeyCode.R) {
      myPaddle.getRectangle().setX(Paddle.STARTING_X);
      myPaddle.getRectangle().setY(Paddle.STARTING_Y);
      myBalls.get(MAIN_BALL).getCircle().setCenterX(Ball.STARTING_X);
      myBalls.get(MAIN_BALL).getCircle().setCenterY(Ball.STARTING_Y);
      myBalls.get(MAIN_BALL).setHorizontalSpeed(0);
      myBalls.get(MAIN_BALL).setVerticalSpeed(Ball.VERTICAL_SPEED);
    }
  }

  private void dropPowerUp(KeyCode code) {
    if (code == KeyCode.P) {
      PowerUp powerUp = new MultiBallPowerUp(SIZE / 2, SIZE / 2);
      myPowerUps.add(powerUp);
      myRoot.getChildren().add(powerUp.getRectangle());
      myRoot.getChildren().add(powerUp.getText());
    }
  }

  private void pause(KeyCode code) {
    if (code == KeyCode.SPACE) {
      paused = !paused;
    }
  }

  private void extraLife(KeyCode code) {
    if(code == KeyCode.L){
      myDisplay.changeLives(1);
    }
  }

  public Ball getBall() {
    return myBalls.get(MAIN_BALL);
  }

  public List<Ball> getBalls() {
    return myBalls;
  }

  public List<Block> getBricks(){
    return myBricks;
  }

  public Paddle getPaddle() {
    return myPaddle;
  }

  public List<PowerUp> getPowerUps() {
    return myPowerUps;
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
