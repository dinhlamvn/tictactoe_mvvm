package android.leo.tic_tac_toemvvm.model.game;

public class Player {

    public String name;
    public PlayerValue value;

    public Player(String name, String value) {
        this.name = name;
        if (value.equalsIgnoreCase("x")) {
            this.value = PlayerValue.VALUE_X;
        } else if (value.equalsIgnoreCase("o")) {
            this.value = PlayerValue.VALUE_O;
        } else {
            this.value = PlayerValue.VALUE_EMPTY;
        }
    }

    public Player(String name, PlayerValue value) {
        this.name = name;
        this.value = value;
    }

    public Player(String name, int value) {
        this.name = name;
        if (value == 0) {
            this.value = PlayerValue.VALUE_X;
        } else if (value == 1) {
            this.value = PlayerValue.VALUE_O;
        } else {
            this.value = PlayerValue.VALUE_EMPTY;
        }
    }

    public enum PlayerValue {
        VALUE_EMPTY("VALUE_EMPTY"),
        VALUE_X("VALUE_X"),
        VALUE_O("VALUE_O");

        private String value = "";

        PlayerValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            String s = super.toString();
            if (s.equals("VALUE_X")) {
                return "X";
            }
            return "O";
        }

    }
}
