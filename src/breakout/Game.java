package breakout;

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

  private Scene myScene;
  private Paddle myPaddle;
  private Ball myBall;

  /**
   * Begins the application by opening a window with objects initialized
   *
   * @param stage the stage used to display the application
   */
  @Override
  public void start(Stage stage) {
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

  Scene setupScene(int width, int height, Paint background) {
    Group root = new Group();
    myPaddle = new Paddle();
    root.getChildren().add(myPaddle.getRectangle());
    myBall = new Ball();
    root.getChildren().add(myBall.getCircle());
    Scene scene = new Scene(root, width, height, background);
    scene.setOnKeyPressed(key -> handleKeyInput(key.getCode()));
    return scene;
  }

  private void handleKeyInput(KeyCode code) {
    myPaddle.movePaddle(code);
  }
  
  void step (double elapsedTime) {
    myBall.ballMovement(elapsedTime);
  }

  /**
   * Used to launch the application.
   *
   * @param args
   */
  public static void main (String[] args) {
    launch(args);
  }

}
