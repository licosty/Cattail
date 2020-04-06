package cattail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CattailView extends JPanel {
    private JFrame frame;
    private CattailGame game;

    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int ICON_SIZE = 50;
    private final int TAILS = 10;

    public CattailView() {
        game = new CattailGame(COLUMNS, ROWS, TAILS);
        game.start();
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
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(getIcon("cattail-icon"));
        frame.pack();
        frame.setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / ICON_SIZE;
                int y = e.getY() / ICON_SIZE;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(x, y);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(x, y);
                }
                repaint();
                if (game.gameOver()) {
                    JDialog jDialog = createDialog("Cattail", true);

                    jDialog.setVisible(true);
                }
            }
        });
    }

    private JDialog createDialog(String cattail, boolean modal) {
        JDialog jDialog = new JDialog(frame, cattail, modal);
        jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jDialog.setMinimumSize(new Dimension(180, 90));

        JPanel label = createLabel();
        JPanel button = createButton(jDialog);
        jDialog.add(label, BorderLayout.NORTH);
        jDialog.add(button, BorderLayout.SOUTH);

        jDialog.pack();
        jDialog.setLocationRelativeTo(null);
        return jDialog;
    }

    private JPanel createLabel() {
        JLabel label = new JLabel(game.getMessage());
        JPanel panel = new JPanel();
        panel.add(label);
        return panel;
    }

    private JPanel createButton(JDialog jDialog) {
        JPanel panel = new JPanel();
        JButton ok = new JButton("ok");
        ok.addActionListener(e -> {
            game.pressLeftButton(1, 1);
            frame.repaint();
            jDialog.setVisible(false);
        });
        panel.add(ok);
        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                g.drawImage(game.getMinefield().getIcon(x, y).icon,
                            x * ICON_SIZE,
                            y * ICON_SIZE, this);
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
