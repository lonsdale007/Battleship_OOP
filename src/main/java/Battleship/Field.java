package Battleship;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Field {
    private static int FIELD_SIZE = 10;

    private Map<Cell, Ship> ships = new HashMap<>();
    private Map<Point, Cell> cells = new HashMap<>();

    public Field() {
        setupField();
    }

    public int getFieldSize() {
        return FIELD_SIZE;
    }

    private void setupField() {
        for(int y = 0; y < FIELD_SIZE; ++y) {
            for(int x = 0; x < FIELD_SIZE; ++x) {
                Point p = new Point(x, y);

                Cell cell =  new Cell();
                if (x > 0) getCell(new Point(p.getX() - 1, p.getY())).setNeighbor(cell, Direction.EAST);
                if (y > 0) getCell(new Point(p.getX(), p.getY() - 1)).setNeighbor(cell, Direction.SOUTH);
                if (x > 0 && y > 0) getCell(new Point(p.getX() - 1, p.getY() - 1)).setNeighbor(cell, Direction.SOUTH_EAST);
                if (x < FIELD_SIZE-1 && y > 0) getCell(new Point(p.getX() + 1, p.getY() - 1)).setNeighbor(cell, Direction.SOUTH_WEST);

                cells.put(p, cell);
            }
        }
    }

    public void playerFire(Cell cell) {
        if (cell.getCellState() == CellState.EMPTY)
            cell.setCellState(CellState.MISSED);
        else if (cell.getCellState() == CellState.DECK)
            cell.setCellState(CellState.PADDED);
    }

    public Cell getCell(@NotNull Point point) {
        return cells.get(point);
    }

    public Map<Cell, Ship> getShipsOnField() {
        return ships;
    }

    public boolean isAllShipsDestroyed() {

        int destroyedShipsCount = 0;

        for (Map.Entry<Cell, Ship> entry : ships.entrySet()) {
            if (entry.getValue().isDestroyed())
                destroyedShipsCount++;
        }

        return destroyedShipsCount == 10;
    }

    public void createShips(TypeShip typeShip) {
        int shipCount = typeShip.ordinal() + 1;

        while (shipCount != 0) {
            if (setShip(typeShip)) {
                shipCount--;
            }
        }
    }

    private boolean setShip(TypeShip typeShip) {
        int decksCount = 4 - typeShip.ordinal();

        boolean hasNeighboursDecks = true;
        int randomLetter = (int) (Math.random() * FIELD_SIZE);
        int randomNumber = (int) (Math.random() * FIELD_SIZE);
        int randomOrientation = (int) (Math.random() * 2);
        Orientation orientation = randomOrientation == 0 ? Orientation.HORIZONTAL : Orientation.VERTICAL;
        Direction direction = orientation == Orientation.HORIZONTAL ? Direction.EAST : Direction.SOUTH;

        Cell start = cells.get(new Point(randomLetter, randomNumber));
        Cell nextCell = start;
        Cell neighbour;
        for (int i = 0; i < decksCount; ++i) {
            for (Direction neighboursDirection: Direction.values()) {
                neighbour = nextCell.neighborCell(neighboursDirection);
                if (neighbour != null)
                    if(hasNeighboursDecks = neighbour.getCellState() == CellState.DECK || nextCell.getCellState() == CellState.DECK)
                        break;
            }

            if (hasNeighboursDecks) return false;

            nextCell = nextCell.neighborCell(direction);
            if (nextCell == null && decksCount > 1) {
                if (i < decksCount - 1)
                    hasNeighboursDecks = true;
                break;
            }
        }

        if (!hasNeighboursDecks) {
            Ship ship = new Ship(typeShip, orientation);

            nextCell = start;
            for (int i = 0; i < decksCount; ++i) {
                if (orientation == Orientation.HORIZONTAL) {
                    direction = Direction.EAST;
                } else {
                    direction = Direction.SOUTH;
                }
                nextCell.setCellState(CellState.DECK);
                ship.addDeck(nextCell);

                nextCell = nextCell.neighborCell(direction);
            }

            ships.put(start, ship);

            return true;
        }

        return false;
    }
}
