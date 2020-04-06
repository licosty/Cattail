package cattail;

class Tail {
    private Matrix tails;
    private final int amountTails;

    public Tail(int amountTails) {
        this.amountTails = amountTails;
    }

    void placeTails() {
        tails = new Matrix(Icon.ZERO);
        for (int i = 0; i < amountTails; i++) {
            placeTail();
        }
    }

    private void placeTail() {

        while (true) {
            int[] coord = Minefield.getInstance().getRandomCoord();
            if (Icon.TAIL == tails.getIconByCoords(coord[0], coord[1]))
                continue;

            tails.setIconByCoords(coord[0], coord[1], Icon.TAIL);

            for (int[] array : Minefield.getInstance().getCoordsAround(coord[0], coord[1])) {
                if (Icon.TAIL != tails.getIconByCoords(array[0], array[1])) {
                    tails.setIconByCoords(
                            array[0], array[1],
                            tails.getIconByCoords(array[0], array[1]).getNextIcon());
                }
            }
            break;
        }
    }

    Icon getTailByCoords(int x, int y) {
        return tails.getIconByCoords(x, y);
    }

    public int getAmountTails() {
        return amountTails;
    }
}
