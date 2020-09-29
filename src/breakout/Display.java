package breakout;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Display {

  private int myLives;
  private int myScore;
  private int myHighScore;
  private int myLevel;
  private Text myLivesText;
  private Text myScoreText;
  private Text myHighScoreText;
  private Text myLevelText;

  public static final int MAX_LIVES = 4;
  public static final double TEXT_LOCATION = Game.SIZE / 16;
  public static final double TEXT_OFFSET = 20;
  public static final double SIDE_OFFSET = 100;
  private static final Color TEXT_COLOR = Color.BLACK;

  public Display(int difficulty) throws URISyntaxException, IOException {
    myLives = MAX_LIVES - difficulty;
    myLivesText = new Text();
    myLivesText.setFill(TEXT_COLOR);
    setLivesText();
    myLivesText.setX(TEXT_LOCATION);
    myLivesText.setY(Game.SIZE - TEXT_LOCATION - TEXT_OFFSET);
    myLivesText.setId("livesText");

    myScore = 0;
    myScoreText = new Text();
    myScoreText.setFill(TEXT_COLOR);
    setScoreText();
    myScoreText.setX(TEXT_LOCATION);
    myScoreText.setY(Game.SIZE - TEXT_LOCATION);
    myScoreText.setId("scoreText");

    myLevel = 1;
    myLevelText = new Text();
    myLevelText.setFill(TEXT_COLOR);
    setLevelText();
    myLevelText.setX(Game.SIZE - SIDE_OFFSET);
    myLevelText.setY(Game.SIZE - TEXT_LOCATION);
    myLevelText.setId("levelText");

    Path highScorePath = Paths.get(Objects.requireNonNull(Game.class.getClassLoader().getResource("highscore.txt")).toURI());
    myHighScore = Integer.parseInt(Files.readString(highScorePath));
    myHighScoreText = new Text();
    myHighScoreText.setFill(TEXT_COLOR);
    setHighScoreText();
    myHighScoreText.setX(Game.SIZE - SIDE_OFFSET);
    myHighScoreText.setY(Game.SIZE - TEXT_LOCATION - TEXT_OFFSET);
    myHighScoreText.setId("highScoreText");
  }

  public int getLives() {
    return myLives;
  }

  public int getScore() {
    return myScore;
  }

  public int getHighScore() {
    return myHighScore;
  }

  public int getLevel() {
    return myLevel;
  }

  public Text getLivesText() {
    return myLivesText;
  }

  public Text getScoreText() {
    return myScoreText;
  }

  public Text getLevelText() { return myLevelText; }

  public Text getHighScoreText() { return myHighScoreText; }

  private void setLivesText() {
    myLivesText.setText("Lives: " + myLives);
  }

  private void setScoreText() {
    myScoreText.setText("Score: " + myScore);
  }

  private void setHighScoreText() {
    myHighScoreText.setText("High Score: " + myHighScore);
  }

  private void setLevelText() {
    myLevelText.setText("Level: " + myLevel);
  }

  public void changeScore(int pointDifference) {
    myScore += pointDifference;
    setScoreText();
  }

  public void changeLives(int lifeDifference) {
    myLives += lifeDifference;
    setLivesText();
  }

  public void setHighScore(int newHighScore) {
    myHighScore = newHighScore;
    setHighScoreText();
  }

  public void setLevel(int level) {
    myLevel = level;
    setLevelText();
  }




  public boolean isGameOver() {
    return myLives == 0;
  }
}
