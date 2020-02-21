package cattail;

import javax.swing.*;
import java.awt.*;

public class CattailGame extends JFrame {
    private JPanel panel;

    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int ICON_SIZE = 50;

    public CattailGame() {
        Minefield minefield = Minefield.getInstance();
        minefield.setWidth(COLUMNS);
        minefield.setHeight(ROWS);
        setIcons();
        initPanel();
        initFrame();
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cattail");
        setVisible(true);
        setIconImage(getIcon("tail"));
    }

    private void initPanel() {
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int y = 0; y < ROWS; y++) {
                    for (int x = 0; x < COLUMNS; x++) {
                        g.drawImage(Icons.TAIL.icon, x * ICON_SIZE, y * ICON_SIZE, this);
                    }
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Minefield.getInstance().getWidth() * ICON_SIZE,
                Minefield.getInstance().getHeight() * ICON_SIZE));
        add(panel);
    }

    public Image getIcon(String iconName) {
        String name = "/icons/" + iconName + ".png";
        ImageIcon image = new ImageIcon(getClass().getResource(name));
        return image.getImage();
    }

    private void setIcons() {
        for (Icons i : Icons.values()) {
            i.icon = getIcon(i.name().toLowerCase());
        }
    }
}
