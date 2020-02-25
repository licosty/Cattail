package cattail;

import javax.swing.*;
import java.awt.*;

public class CattailView extends JPanel {
    private JFrame frame;
    private CattailGame game;

    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int ICON_SIZE = 50;
    private final int TAILS = 10;

    public CattailView() {
        game = new CattailGame(COLUMNS, ROWS);
        game.start(TAILS);
        setIcons();
        initFrame();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cattail");
        setPreferredSize(new Dimension(
                Minefield.getInstance().getWidth() * ICON_SIZE,
                Minefield.getInstance().getHeight() * ICON_SIZE));
        frame.add(this);
        frame.setVisible(true);
        frame.setIconImage(getIcon("tail"));
        frame.pack();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                g.drawImage(game.getMinefield().getIcon(x, y).icon, x * ICON_SIZE, y * ICON_SIZE, this);
            }
        }
    }

    public Image getIcon(String iconName) {
        String name = "/icons/" + iconName + ".png";
        ImageIcon image = new ImageIcon(getClass().getResource(name));
        return image.getImage();
    }

    private void setIcons() {
        for (Icon i : Icon.values()) {
            i.icon = getIcon(i.name().toLowerCase());
        }
    }
}
