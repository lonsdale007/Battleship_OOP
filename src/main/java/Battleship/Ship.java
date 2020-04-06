package Battleship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ship {

    private TypeShip typeShip;
    private Orientation orientation;
    private ArrayList<Cell> decks = new ArrayList<>();

    public Ship(TypeShip typeShip, Orientation orientation) {
        this.typeShip = typeShip;
        this.orientation = orientation;
    }

    public ArrayList<Cell> getDecks() {
        return decks;
    }

    public void addDeck(Cell deck) {
        decks.add(deck);
    }

    public Orientation orientation() {
        return orientation;
    }

    public TypeShip typeShip() {
        return typeShip;
    }

    public boolean isDestroyed() {
        int paddedDecksCount = 0;
        for (Cell deck : decks) {
            if (deck.getCellState() == CellState.PADDED)
                paddedDecksCount++;
        }

        return paddedDecksCount == decks.size();
    }
}
