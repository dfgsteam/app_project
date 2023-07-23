package bauernhof.app.system;

import bauernhof.app.player.PlayerCards;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

import java.util.ArrayList;

/**
 * @author Ramon Cemil Kimyon
 * @date 20.07.2023 15:33
 */
public interface Game {
    int getCurrentPlayerID();
    ImmutableList<Integer> getAllScores();
    ArrayList<Card> getDepositedCards();
    ImmutableList<Card> getDrawPileCards();
    PlayerCards getPlayerCards(final int playerid);
    PlayerCards getCurrentPlayerCards();
    int getRound();
    boolean executeMove(final Move move) throws Exception;
    int getNumPlayers();
    int getWinnerID();

    GameConfiguration getConfiguration();
    String getName(final int playerid) throws Exception;
    int getScore(final int playerid) throws Exception;
    Settings getSettings();
}
