package android.leo.tic_tac_toemvvm.model.game;

import android.arch.lifecycle.MutableLiveData;

public class Game {

    private static final String TAG = Game.class.getSimpleName();

    private static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;

    public Player currentPlayer;

    public Cell[][] cells;

    public MutableLiveData<Player> winners = new MutableLiveData<>();

    public Game(String playerOneName, String playerTwoName) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOneName, "x");
        player2 = new Player(playerTwoName, "o");
        currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean isGameEnded() {
        if (hasThreeSameOnHorizontalCells() || hasThreeSameOnVerticalCells()
                || hasThreeSameOnDiagonalCells()) {
            winners.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winners.setValue(null);
            return true;
        }

        return false;
    }

    public boolean hasThreeSameOnHorizontalCells() {
        Player.PlayerValue value = currentPlayer.value;

        return areEquals(value, cells[0][0], cells[1][0], cells[2][0])
                || areEquals(value, cells[0][1], cells[1][1], cells[2][1])
                || areEquals(value, cells[0][2], cells[1][2], cells[2][2]);
    }

    public boolean hasThreeSameOnVerticalCells() {
        Player.PlayerValue value = currentPlayer.value;

        return areEquals(value, cells[0][0], cells[0][1], cells[0][2])
                || areEquals(value, cells[1][0], cells[1][1], cells[1][2])
                || areEquals(value, cells[2][0], cells[2][1], cells[2][2]);
    }

    public boolean hasThreeSameOnDiagonalCells() {
        Player.PlayerValue value = currentPlayer.value;

        return areEquals(value, cells[0][0], cells[1][1], cells[2][2])
                || areEquals(value, cells[0][2], cells[1][1], cells[2][0]);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j] == null || cells[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areEquals(Player.PlayerValue value, Cell... cells) {
        for (Cell cell : cells) {
            if (cell == null || cell.isEmpty() || cell.player != currentPlayer || cell.player.value != value) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        currentPlayer = player1;
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    }
}
