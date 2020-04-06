package Battleship;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.CollationElementIterator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class Cell {

    private CellState cellState = CellState.EMPTY;

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }

    /**
     * Neighbour cells
     */
    private Map<Direction, Cell> neighborCells = new EnumMap<>(Direction.class);

    public Cell neighborCell(@NotNull Direction direction) {
        return neighborCells.get(direction);
    }

    void setNeighbor(@NotNull Cell cell, @NotNull Direction direction) {
        if(neighborCells.containsKey(direction) && neighborCells.containsValue(cell)) return;
        if(neighborCells.containsKey(direction)) throw new IllegalArgumentException();
        neighborCells.put(direction, cell);
        if(cell.neighborCell(direction.getOppositeDirection()) == null) {
            cell.setNeighbor(this, direction.getOppositeDirection());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return cellState == cell.cellState && Objects.equals(neighborCells, cell.neighborCells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellState, neighborCells.size());
    }

    @Override
    public String toString() {
        return "Cell{" +
                ", cellState=" + cellState +
                ", neighborCells=" + neighborCells.size() +
                '}';
    }

    public String toStr() {
        if (this.getCellState() == CellState.EMPTY)
            return ".";
        else if (this.getCellState() == CellState.DECK)
            return "o";
        else if (this.getCellState() == CellState.PADDED)
            return "x";
        else if (this.getCellState() == CellState.MISSED)
            return "M";
        return ".";
    }
}
