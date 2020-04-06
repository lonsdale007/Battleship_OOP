package Battleship;

public class Computer extends Player {

    @Override
    public void makeShot(Cell cell) {
        super.makeShot(cell);
    }

    public Cell makeDecision() {
        return analyzeField();
    }

    public Cell analyzeField() {
        boolean isOk = true;

        while (isOk) {
            int randomLetter = (int) (Math.random() * field.getFieldSize());
            int randomNumber = (int) (Math.random() * field.getFieldSize());

            Cell cell = field.getCell(new Point(randomNumber, randomLetter));

            if (cell.getCellState() == CellState.EMPTY || cell.getCellState() == CellState.DECK) {
                return cell;
            } else isOk = false;
        }

        return null;
    }
}
