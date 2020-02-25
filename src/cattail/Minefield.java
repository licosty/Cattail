package cattail;

import java.util.ArrayList;
import java.util.Random;

class Minefield {
    private static Minefield instance;
    private Tail tail;

    private int height;
    private int width;

    private static Random random = new Random();

    private Minefield() {
    }

    public static Minefield getInstance() {
        if (instance == null)
            instance = new Minefield();
        return instance;
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

    public void createTail(int tails) {
        tail = new Tail(tails);
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
        return tail.getTailByCoords(x, y);
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
