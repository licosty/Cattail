package cattail;

import java.util.ArrayList;
import java.util.Random;

class Minefield {
    private static Minefield instance;
    private Matrix upperLayer;
    private Tail tail;

    private int height;
    private int width;

    private static Random random = new Random();
    private int countFlags;

    private Minefield() {
    }

    public static Minefield getInstance() {
        if (instance == null)
            instance = new Minefield();
        return instance;
    }

    Matrix initUpperLayer() {
        upperLayer = new Matrix(Icon.CLOSED);
        countFlags = width * height;
        return upperLayer;
    }

    public boolean isNotBound(int x, int y) {
        return x >= 0 && x < width &&
               y >= 0 && y < height;
    }

    int[] getRandomCoord() {
        int[] coord = new int[2];
        coord[0] = random.nextInt(width);
        coord[1] = random.nextInt(height);

        return coord;
    }

    public Tail createTail(int tails) {
        tail = new Tail(tails);
        return tail;
    }

    ArrayList<int[]> getCoordsAround(int coordX, int coordY) {
        ArrayList<int[]> list = new ArrayList<>();
        for (int x = coordX - 1; x <= coordX + 1; x++) {
            for (int y = coordY - 1; y <= coordY + 1 ; y++) {
                if (isNotBound(x, y)) {
                    if (x != coordX || y != coordY)
                        list.add(new int[]{x, y});
                }
            }
        }
        return list;
    }

    public Icon getIcon(int x, int y) {
        if (upperLayer.getIconByCoords(x, y) == Icon.OPENED)
            return tail.getTailByCoords(x, y);
        return upperLayer.getIconByCoords(x, y);
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

    public int getCountFlags() {
        return countFlags;
    }

    public void countFlagsDecrement() {
        countFlags--;
    }
}
