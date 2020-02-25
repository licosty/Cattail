package cattail;

public class CattailGame {
    private Minefield minefield;

    public CattailGame(int columns, int rows) {
        minefield = Minefield.getInstance();
        minefield.setWidth(columns);
        minefield.setHeight(rows);
    }

    public void start(int tails) {
        minefield.createTail(tails);
    }

    public Minefield getMinefield() {
        return minefield;
    }
}
