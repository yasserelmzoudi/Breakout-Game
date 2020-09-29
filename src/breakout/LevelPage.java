package breakout;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class LevelPage {

  private static final int MAIN_BALL = 0;
  private int levelNumber;
  private Group groupRoot;
  private Scene scene;
  private Paddle paddle;
  private List<Ball> balls;
  private Ball ball;
  private Display display;
  private int unbreakableBricks = 0;
  private List<Brick> bricks;
  private boolean paused = false;

  public static final int DIFFICULTY = 1;
  private List<PowerUp> fallingPowerUps;
  private List<PowerUp> activePowerUps;


  public LevelPage(int levelNumber) {
    this.levelNumber = levelNumber;
  }

  public Scene initialize() throws IOException, URISyntaxException {
    groupRoot = new Group();
    scene = new Scene(groupRoot, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    createGameObjects();
    addGameObjectsToRoot();
    scene.setOnKeyPressed(key -> handleKeyInput(key.getCode()));
    return scene;
  }

  private void handleKeyInput(KeyCode code) {
    if (!paused) {
      paddle.movePaddle(code);
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

  private void reset() {
    paddle.getRectangle().setX(Paddle.STARTING_X);
    paddle.getRectangle().setY(Paddle.STARTING_Y);
    balls.get(MAIN_BALL).getCircle().setCenterX(Ball.STARTING_X);
    balls.get(MAIN_BALL).getCircle().setCenterY(Ball.STARTING_Y);
    balls.get(MAIN_BALL).setHorizontalSpeed(0);
    balls.get(MAIN_BALL).setVerticalSpeed(Ball.VERTICAL_SPEED);
  }

  private void dropPowerUp() {
    PowerUp powerUp = PowerUp.powerUpGenerator(Game.SIZE / 2, Game.SIZE / 2);
    fallingPowerUps.add(powerUp);
    groupRoot.getChildren().add(powerUp.getRectangle());
    groupRoot.getChildren().add(powerUp.getText());
  }

  private void pause(KeyCode code) {
    if (code == KeyCode.SPACE) {
      paused = !paused;
    }
  }

  private void extraLife() {
    display.changeLives(1);
  }

  private void breakBlock() {
    Brick brick = bricks.remove(0);
    brick.destroyBrick(groupRoot, bricks, fallingPowerUps);
    display.changeScore(brick.getScore());
    /*if (bricks.size() == unbreakableBricks) {
      gameOver("YOU WIN!");
    }*/
  }

  private void createGameObjects() throws IOException, URISyntaxException {
    paddle = new Paddle();
    fallingPowerUps = new ArrayList<>();
    activePowerUps = new ArrayList<>();
    balls = new ArrayList<>();
    ball = new Ball();
    balls.add(ball);
    buildBlocksFromFile(groupRoot);
    display = new Display(DIFFICULTY);
  }
  private void addGameObjectsToRoot() {
    groupRoot.getChildren().add(paddle.getRectangle());
    groupRoot.getChildren().add(balls.get(MAIN_BALL).getCircle());
    groupRoot.getChildren().add(display.getScoreText());
    groupRoot.getChildren().add(display.getLivesText());

  }

  public void buildBlocksFromFile(Group root) // Maybe this method should be in Brick.java?
      throws IOException, URISyntaxException {
    Path path = Paths
        .get(Objects.requireNonNull(Game.class.getClassLoader().getResource("level" + levelNumber)).toURI());
    int currentX;
    int currentY = 0;

    bricks = new ArrayList<>();

    for (String row : Files.readAllLines(path)) {
      currentX = 0;
      for (String blockType : row.split("")) {
        if (validBrick(blockType)) {
          Brick brick = brickBuilder(currentX, currentY, blockType);
          root.getChildren().add(brick.getRectangle());
        }
        currentX += Brick.LENGTH;
      }
      currentY += Brick.HEIGHT;
    }
  }

  private boolean validBrick(String blockType) {
    return !(blockType.equals(" ") || blockType.equals(""));
  }

  private Brick brickBuilder(int currentX, int currentY, String blockType) {
    Brick brick;
    switch(blockType) {
      case "X" -> brick = new MultiHitBrick(currentX, currentY);
      case "i" -> {
        brick = new UnbreakableBrick(currentX, currentY);
        unbreakableBricks++;
      }
      default -> brick = new BasicBrick(currentX, currentY);
    }
    bricks.add(brick);
    return brick;
  }

}
