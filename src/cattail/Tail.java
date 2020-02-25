package cattail;

class Tail {
    private Matrix tails;
    private int amountTails;

    public Tail(int amountTails) {
        this.amountTails = amountTails;
        tails = new Matrix(Icon.ZERO);

        for (int i = 0; i < amountTails; i++) {
            placeTail();
        }
    }

    private void placeTail() {
        int[] coord = Minefield.getInstance().getRandomCoord();
        int x = coord[0];
        int y = coord[1];

        tails.setIconByCoords(x, y, Icon.TAIL);

        for (int[] array : Minefield.getInstance().getCoordsAround(x, y)) {
            tails.setIconByCoords(array[0], array[1], Icon.NUM1);
        }
    }

    Icon getTailByCoords(int x, int y) {
        return tails.getIconByCoords(x, y);
    }
}
