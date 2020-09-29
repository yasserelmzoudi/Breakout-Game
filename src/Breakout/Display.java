package Breakout;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Display {

  private int myLives;
  private int myScore;
  private Text myLivesText;
  private Text myScoreText;
  public static final int MAX_LIVES = 4;
  public static final double TEXT_LOCATION = Game.SIZE / 16;
  public static final double TEXT_OFFSET = 20;
  private static final Color TEXT_COLOR = Color.BLACK;

  public Display(int difficulty) {
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
  }

  public int getLives() {
    return myLives;
  }

  public Text getLivesText() {
    return myLivesText;
  }

  private void setLivesText() {
    myLivesText.setText("Lives: " + myLives);
  }

  public int getScore() {
    return myScore;
  }

  private void setScoreText() {
    myScoreText.setText("Score: " + myScore);
  }

  public Text getScoreText() {
    return myScoreText;
  }

  public void changeScore(int pointDifference) {
    myScore += pointDifference;
    setScoreText();
  }

  public void changeLives(int lifeDifference) {
    myLives += lifeDifference;
    setLivesText();
  }

  public boolean isGameOver() {
    return myLives == 0;
  }
}
