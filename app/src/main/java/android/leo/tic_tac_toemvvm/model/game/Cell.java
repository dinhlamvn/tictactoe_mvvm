package android.leo.tic_tac_toemvvm.model.game;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public Cell(String name, String value) {
        this.player = new Player(name, value);
    }

    public boolean isEmpty() {
        return player == null || player.value == Player.PlayerValue.VALUE_EMPTY;
    }
}
