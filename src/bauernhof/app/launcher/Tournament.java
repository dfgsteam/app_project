package bauernhof.app.launcher;

import bauernhof.app.card.Ca;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.Player;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Ramon Cemil Kimyon
 * @date 13.07.2023 14:05
 */
public class Tournament {
    public Tournament(final String[] players, final PlayerType[] types, final GameConfiguration configuration, ImmutableList<Card> cards, int numbergames) throws Exception {
        final ArrayList<GameBoardState> states = new ArrayList<>();
        for (int i = 0; i < numbergames; i++) {
            System.out.println("\n\n\n\n");
            System.out.println("RUNDE: " + i);
            System.out.println("\n\n\n\n");
            cards = mixCards(cards);
            if (List.of(types).contains(PlayerType.HUMAN))
                if (i == 0) {
                    states.add(new GameBoardState(players, types, configuration, cards));
                    states.get(i).initGame(null);
                } else {
                    while (states.get(i - 1).getRun()) ;
                    states.add(new GameBoardState(players, types, configuration, cards));
                    states.get(i).initGame(null);
                }
            else {
                states.add(new GameBoardState(players, types, configuration, cards));
                states.get(i).initGame(null);
            }
        }
        System.out.println(states.size());
        int[] scores;
        final ArrayList<Integer> wins = new ArrayList<>();
        for (int i = 0; i < types.length; i++)
            wins.add(0);
        for (final GameBoardState state : states) {
            scores = new int[types.length];
            for (int i = 0; i < state.getPlayers().length; i++) {
                scores[i] = state.getPlayers()[i].getScore();
            }
           Arrays.sort(scores);
            for (final Player player : state.getPlayers())
                if (player.getScore() == scores[state.getPlayers().length - 1])
                    wins.set(((AbstractGamePlayer) player).getPlayerID(), wins.get(((AbstractGamePlayer) player).getPlayerID()) + 1);
        }
        System.out.println(wins);
    }
    private ImmutableList<Card> mixCards(final ImmutableList<Card> cards) {
        ArrayList<Card> cards2 = new ArrayList<>(cards);
        Collections.shuffle(cards2);
        ImmutableList<Card> cards3 = new ImmutableList<>(cards2);
        return cards3;

    }
}
