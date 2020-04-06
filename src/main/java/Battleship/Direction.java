package Battleship;

public enum Direction {
    NORTH, NORTH_EAST,
    EAST, SOUTH_EAST,
    SOUTH, SOUTH_WEST,
    WEST, NORTH_WEST;

    private Direction opposite;

    static  {
        NORTH.opposite = SOUTH; NORTH_EAST.opposite = SOUTH_WEST;
        SOUTH.opposite = NORTH; SOUTH_EAST.opposite = NORTH_WEST;
        WEST.opposite = EAST;   SOUTH_WEST.opposite = NORTH_EAST;
        EAST.opposite = WEST;   NORTH_WEST.opposite = SOUTH_EAST;
    }

    public Direction getOppositeDirection() {
        return opposite;
    }
}
