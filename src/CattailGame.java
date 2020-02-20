import javax.swing.*;
import java.awt.*;

public class CattailGame extends JFrame {
    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int ICON_SIZE = 50;

    public CattailGame() {
        setSize(new Dimension(COLUMNS * ICON_SIZE, ROWS * ICON_SIZE));
        setTitle("Cattail");
        setVisible(true);
        setIconImage(Icons.TAIL.getImage("tail"));
    }
}
