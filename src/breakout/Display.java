package breakout;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Display {
  private int myLives;
  private int myScore;
  private Text myLivesText;
  private Text myScoreText;
  private static final int MAX_LIVES = 4;
  public static final double TEXT_LOCATION = Game.SIZE/16;
  public static final double TEXT_OFFSET = 20;
  private static final Color TEXT_COLOR = Color.BLACK;

  public Display(int difficulty) {
    myLives = MAX_LIVES - difficulty;
    myLivesText = new Text();
    myLivesText.setFill(TEXT_COLOR);
    myLivesText.setText("Lives: " + myLives);
    myLivesText.setX(TEXT_LOCATION);
    myLivesText.setY(Game.SIZE - TEXT_LOCATION - TEXT_OFFSET);
    myLivesText.setId("livesText");

    myScore = 0;
    myScoreText = new Text();
    myScoreText.setFill(TEXT_COLOR);
    myScoreText.setText("Score: " + myScore);
    myScoreText.setX(TEXT_LOCATION);
    myScoreText.setY(Game.SIZE - TEXT_LOCATION);
    myScoreText.setId("scoreText");
  }

  public int getLives(){
    return myLives;
  }

  public Text getLivesText(){
    return myLivesText;
  }

  public int getScore(){
    return myScore;
  }

  public Text getScoreText(){
    return myScoreText;
  }

}
