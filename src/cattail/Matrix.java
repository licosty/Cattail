package cattail;

public class Matrix {

    private Icon[][] matrix;
    private Minefield minefield;

    public Matrix(Icon defaultIcon) {
        minefield = Minefield.getInstance();
        this.matrix = new Icon[minefield.getWidth()][minefield.getHeight()];
        for (int y = 0; y < minefield.getHeight(); y++) {
            for (int x = 0; x < minefield.getWidth(); x++) {
                matrix[x][y] = defaultIcon;
            }
        }
    }

    public Icon getIconByCoords(int x, int y) {
        if (minefield.isNotBound(x, y))
            return matrix[x][y];
        return null;
    }

    public void setIconByCoords(int x, int y, Icon icon) {
        matrix[x][y] = icon;
    }
}
