package cattail;

import java.awt.*;

public enum Icon {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    TAIL,
    OPENED,
    CLOSED,
    FLAGGED,
    STEPPED,
    NOTAIL;

    public Image icon;

    public Icon getNextIcon() {
        return Icon.values()[this.ordinal() + 1];
    }
}
