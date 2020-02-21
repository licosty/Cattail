package cattail;

public class CattailGame {
    Minefield minefield;

    public CattailGame() {
        minefield = Minefield.getInstance();
    }

    public void start(int width, int height) {
        minefield.setWidth(width);
        minefield.setHeight(height);
        minefield.setDefaultIcon(Icon.CLOSED);
        minefield.setMatrix();
        minefield.setIconByCoords(0, 0, Icon.STEPPED);
    }

    public Icon getIcon(int x, int y) {
        return minefield.getIconByCoords(x, y);
    }
}
