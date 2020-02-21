package cattail;

public class Minefield {
    private int height;
    private int width;

    private static Minefield instance;

    private Minefield() {}

    public static Minefield getInstance() {
        if (instance == null)
            instance = new Minefield();
        return instance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
