package cattail;

public class CattailGame {
    public boolean gameOver;
    private Minefield minefield;
    private Matrix upperLayer;
    private Tail tail;
    private String message;

    public CattailGame(int columns, int rows, int tails) {
        minefield = Minefield.getInstance();
        minefield.setWidth(columns);
        minefield.setHeight(rows);
        tail = minefield.createTail(tails);
    }

    public void start() {
        tail.placeTails();
        upperLayer = minefield.initUpperLayer();
        gameOver = false;
    }

    public Minefield getMinefield() {
        return minefield;
    }

    public void pressLeftButton(int x, int y) {
        if (gameOver) {
            start();
            return;
        }
        setOpened(x, y);
        checkWinner();
    }

    public void pressRightButton(int x, int y) {
        toggleFlagged(x, y);
    }

    public void setOpened(int x, int y) {
        switch (upperLayer.getIconByCoords(x, y)) {
            case FLAGGED: break;
            case OPENED: openAroundNumber(x, y); break;
            case CLOSED:
                switch (tail.getTailByCoords(x, y)) {
                    case ZERO: openCellsAround(x, y); break;
                    case TAIL: openAllBombs(x, y); break;
                    default: upperLayer.setIconByCoords(x, y, Icon.OPENED);
                             minefield.countFlagsDecrement();
                }
        }
    }

    private void openAroundNumber(int x, int y) {

        if (getCountFlagsAround(x, y) == tail.getTailByCoords(x, y).ordinal()) {
            for (int[] around : minefield.getCoordsAround(x, y)){
                if (upperLayer.getIconByCoords(around[0], around[1]) == Icon.CLOSED) {
                    setOpened(around[0], around[1]);
                }
            }
        }
    }

    private void openCellsAround(int x, int y) {
        upperLayer.setIconByCoords(x, y, Icon.OPENED);
        minefield.countFlagsDecrement();
        for (int[] around : minefield.getCoordsAround(x, y)) {
            setOpened(around[0], around[1]);
        }
    }

    private void openAllBombs(int bombX, int bombY) {
        gameOver = true;
        message = "Один котик сегодня зол";
        upperLayer.setIconByCoords(bombX, bombY, Icon.STEPPED);
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                if (tail.getTailByCoords(x, y) == Icon.TAIL) {
                    if (upperLayer.getIconByCoords(x, y) == Icon.CLOSED) {
                        upperLayer.setIconByCoords(x, y, Icon.OPENED);
                    }

                } else {
                    if (upperLayer.getIconByCoords(x, y) == Icon.FLAGGED) {
                        upperLayer.setIconByCoords(x, y, Icon.NOTAIL);
                    }
                }
            }
        }
    }

    private int getCountFlagsAround(int x, int y) {
        int count = 0;
        for (int[] around : minefield.getCoordsAround(x, y)) {
            if (upperLayer.getIconByCoords(around[0], around[1]) == Icon.FLAGGED) {
                count++;
            }
        }
        return count;
    }

    public void toggleFlagged(int x, int y) {
        if (upperLayer.getIconByCoords(x, y) == Icon.FLAGGED)
            upperLayer.setIconByCoords(x, y, Icon.CLOSED);
        else if (upperLayer.getIconByCoords(x, y) == Icon.CLOSED)
            upperLayer.setIconByCoords(x, y, Icon.FLAGGED);
    }

    public boolean gameOver() {
        return gameOver;
    }

    private void checkWinner() {
        if (!gameOver) {
            if (minefield.getCountFlags() == tail.getAmountTails()) {
                gameOver = true;
                message = "Поздравляю! Все хвостики остались целы!";
            }
        }
    }

    public String getMessage() {
        return message;
    }
}
