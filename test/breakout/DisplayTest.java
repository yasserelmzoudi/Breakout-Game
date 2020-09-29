package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class DisplayTest extends DukeApplicationTest {

  private final Game myGame = new Game();
  private Scene myScene;
  private Display myDisplay;

  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(1, Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    myDisplay = myGame.getDisplay();
  }

  @Test
  public void testLivesDisplay() {
    myDisplay.changeLives(1);
    assertEquals(Display.MAX_LIVES - Game.DIFFICULTY + 1, myDisplay.getLives());
  }

  @Test
  public void testScoreDisplay() {
    myDisplay.changeScore(500);
    assertEquals(500, myDisplay.getScore());
  }

}