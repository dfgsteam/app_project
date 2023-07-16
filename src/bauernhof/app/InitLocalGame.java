package bauernhof.app;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.PlayerType;

public class InitLocalGame {
    public InitLocalGame(GameConfiguration gameConfiguration, String[] playerNames, PlayerType[] playerTypes, Color[] playerColors, int delay, boolean gui, int logLevel, int volume) throws Exception {
        

        // Load gui
        if (gui) {
            GameBoardState gameBoard = new GameBoardState(playerNames, playerTypes, gameConfiguration, new ImmutableList<>(gameConfiguration.getCards()));
            UiGame uiGame = new UiGame(gameConfiguration, gameBoard);
            gameBoard.initGame(uiGame);
        }
        
    }

    private void test() {}
}
