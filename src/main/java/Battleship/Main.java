package Battleship;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Field userField = game.getUserField();
        Field computerField = game.getComputerField();
        Player user = game.getUser();
        Player computer = game.getComputer();

        System.out.print("\n");
        System.out.println("User's field with ships:");
        for (int y = 0; y < userField.getFieldSize(); ++y) {
            for (int x = 0; x < userField.getFieldSize(); ++x) {
                System.out.print(userField.getCell(new Point(x, y)).toStr() + "  ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");
        System.out.println("Computer's field with ships:");
        for (int y = 0; y < computerField.getFieldSize(); ++y) {
            for (int x = 0; x < computerField.getFieldSize(); ++x) {
                System.out.print(computerField.getCell(new Point(x, y)).toStr() + "  ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");
        System.out.println("User made a shot on 5 3");
        user.makeShot(computerField.getCell(new Point(5, 3)));

        System.out.print("\n");
        System.out.println("Computer's field with ships:");
        for (int y = 0; y < computerField.getFieldSize(); ++y) {
            for (int x = 0; x < computerField.getFieldSize(); ++x) {
                System.out.print(computerField.getCell(new Point(x, y)).toStr() + "  ");
            }
            System.out.print("\n");
        }
    }
}
