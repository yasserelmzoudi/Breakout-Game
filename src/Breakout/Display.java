package Breakout;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Presents the information of a current Breakout game to the user, such as number of lives and
 * score
 *
 * @author Yasser Elmzoudi, Jack Ellwood
 */
public class Display {

  public static final int MAX_LIVES = 4;
  public static final double TEXT_LOCATION = Game.SIZE / 16;
  public static final double TEXT_OFFSET = 20;
  private static final Color TEXT_COLOR = Color.BLACK;
  private final Text myLivesText;
  private final Text myScoreText;
  private int myLives;
  private int myScore;

  /**
   * Creates a Display from a difficulty such that the number of lives a player receives are
   * dependent on the difficulty
   *
   * @param difficulty int representing difficulty of game, where difficulty affects  the number of
   *                   lives available
   */
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

  /**
   * Returns number of lives left
   *
   * @return int representing number of lives left
   */
  public int getLives() {
    return myLives;
  }

  /**
   * Returns lives left as a Text
   *
   * @return Text displaying number of lives left
   */
  public Text getLivesText() {
    return myLivesText;
  }

  /**
   * Sets lives Text to the current number of lives left
   */
  private void setLivesText() {
    myLivesText.setText("Lives: " + myLives);
  }

  /**
   * Returns current score
   *
   * @return int representing current score
   */
  public int getScore() {
    return myScore;
  }

  /**
   * Sets score Text to the current score
   */
  private void setScoreText() {
    myScoreText.setText("Score: " + myScore);
  }

  /**
   * Returns score as a Text
   *
   * @return Text displaying current score
   */
  public Text getScoreText() {
    return myScoreText;
  }

  /**
   * Adds a given score difference to the current score
   *
   * @param pointDifference int representing score to be added to the current score
   */
  public void changeScore(int pointDifference) {
    myScore += pointDifference;
    setScoreText();
  }

  /**
   * Adds a given live difference to the current number of lives
   *
   * @param lifeDifference int representing number of lives to be added to the current number of
   *                       lives
   */
  public void changeLives(int lifeDifference) {
    myLives += lifeDifference;
    setLivesText();
  }

  /**
   * Checks to see if the current number of lives is 0
   * @return boolean represening if the current number of lives is 0
   */
  public boolean isGameOver() {
    return myLives == 0;
  }
}
