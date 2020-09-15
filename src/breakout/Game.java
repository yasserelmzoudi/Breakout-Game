package breakout;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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

  private Scene myScene;
  private Paddle myPaddle;
  private Ball myBall;
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
    Group root = new Group();
    myPaddle = new Paddle();
    root.getChildren().add(myPaddle.getRectangle());
    myBall = new Ball();
    root.getChildren().add(myBall.getCircle());
    buildBlocksFromFile(LEVEL, root);
    Scene scene = new Scene(root, width, height, background);
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

    for (String row : Files.readAllLines(path)) {
      currentX = 0;
      for (String space : row.split("")) {
        if (space.equalsIgnoreCase(Block.BLOCK_LETTER)) {
          Block block = new Block(currentX, currentY);
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
    }
    pause(code);
  }

  void step(double elapsedTime) {
    if (!paused) {
      myBall.ballMovement(elapsedTime);
      myBall.checkPaddleHit(myPaddle);
      myBall.checkWallHit();
    }
  }

  private void reset(KeyCode code) {
    if (code == KeyCode.R) {
      myPaddle.getRectangle().setX(Paddle.STARTING_X);
      myPaddle.getRectangle().setY(Paddle.STARTING_Y);
      myBall.getCircle().setCenterX(Ball.STARTING_X);
      myBall.getCircle().setCenterY(Ball.STARTING_Y);
      myBall.setHorizontalSpeed(0);
      myBall.setVerticalSpeed(Ball.VERTICAL_SPEED);
    }
  }

  private void pause(KeyCode code) {
    if (code == KeyCode.SPACE) {
      paused = !paused;
    }
  }

  public Ball getBall() {
    return myBall;
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
