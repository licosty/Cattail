import javax.swing.*;
import java.awt.*;

public enum Icons {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    CLOSED,
    TAIL,
    STEPPED,
    FLAGED,
    INFORM;

    private Image image;

    public Image getImage(String imageName) {
        String name = "/icons/" + imageName + ".png";
        image = new ImageIcon(getClass().getResource(name)).getImage();
        return image;
    }
}
