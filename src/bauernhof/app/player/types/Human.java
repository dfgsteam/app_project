package bauernhof.app.player.types;
import bauernhof.app.launcher.GameStatus;
import bauernhof.app.player.*;
import bauernhof.preset.PlayerType;

public class Human extends LocalPlayer {

    public Human(String name, GameStatus actual_game) {
        super(name, actual_game, PlayerType.HUMAN);
    }
}
