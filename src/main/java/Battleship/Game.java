package Battleship;

import org.jetbrains.annotations.NotNull;

public class Game {

    private GameStatus gameStatus;
    private Player activePlayer;
    private Player winner;
    private Field userField;
    private Field computerField;
    private Player user = new Player();
    private Player computer = new Computer();

    public Game() {
        gameStatus = GameStatus.GAME_IS_ON;

        buildField();

        user.setField(computerField);
        computer.setField(userField);

        setActivePlayer(user);
    }

    public void buildField() {
        // Make fields
        userField = new Field();
        computerField = new Field();

        // Set ships
        userField.createShips(TypeShip.BATTLESHIP);
        userField.createShips(TypeShip.CRUISER);
        userField.createShips(TypeShip.DESTROYER);
        userField.createShips(TypeShip.TORPEDO);

        computerField.createShips(TypeShip.BATTLESHIP);
        computerField.createShips(TypeShip.CRUISER);
        computerField.createShips(TypeShip.DESTROYER);
        computerField.createShips(TypeShip.TORPEDO);
    }

    public Player getUser() {
        return user;
    }

    public Player getComputer() {
        return computer;
    }

    public Field getUserField() {
        return userField;
    }

    public Field getComputerField() {
        return computerField;
    }

    public GameStatus gameStatus() {
        return  gameStatus;
    }

    public Player activePlayer() {
        return  activePlayer;
    }

    public Player winner() {
        return winner;
    }

    public void finish() {
        if (gameStatus != GameStatus.GAME_IS_ON) throw new RuntimeException("Game not is ON");
        gameStatus = GameStatus.GAME_FINISHED_AHEAD_OF_SCHEDULE;
    }

    private void passMoveNextPlayer() {
        if (gameStatus != GameStatus.GAME_IS_ON) throw new RuntimeException("Game not is ON");


    }

    private void setWinner(@NotNull Player player) {
        if (gameStatus != GameStatus.GAME_IS_ON) throw new RuntimeException("Game not is on");
        winner = player;
        gameStatus = GameStatus.WINNER_FOUND;

        // Deactivate all players?
        activePlayer = null;
        player.setActive(false);
    }

    private void setActivePlayer(@NotNull Player player) {
        if (gameStatus != GameStatus.GAME_IS_ON) throw new RuntimeException("Game not is on");

        if (activePlayer != null) activePlayer.setActive(false);
        activePlayer = player;
        player.setActive(true);
    }
}
