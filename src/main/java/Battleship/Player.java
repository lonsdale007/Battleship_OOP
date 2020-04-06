package Battleship;

public class Player {

    private boolean isActive;
    Field field;

    public void setField(Field field) {
        this.field = field;
    }

    void setActive(boolean value) {
        isActive = value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void makeShot(Cell cell) {
        if (cell != null)
            field.playerFire(cell);
    }
}
