package cattail;

class Minefield {
    private static Minefield instance;

    private int height;
    private int width;
    private Icon[][] matrix;
    private Icon defaultIcon;

    private Minefield() {
    }

    public static Minefield getInstance() {
        if (instance == null)
            instance = new Minefield();
        return instance;
    }

    public Icon getIconByCoords(int x, int y) {
        if (isBound(x, y))
            return matrix[x][y];
        return null;
    }

    public void setIconByCoords(int x, int y, Icon icon) {
        matrix[x][y] = icon;
    }

    private boolean isBound(int x, int y) {
        return x >= 0 && x < width &&
               y >= 0 && y < height;
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

    public void setMatrix() {
        this.matrix = new Icon[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix[x][y] = defaultIcon;
            }
        }
    }

    public Icon[][] getMatrix() {
        return matrix;
    }

    public void setDefaultIcon(Icon defaultIcon) {
        this.defaultIcon = defaultIcon;
    }
}
