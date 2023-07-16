package bauernhof.app.system;

import bauernhof.preset.GameConfiguration;
import bauernhof.preset.ImmutableList;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ramon Cemil Kimyon
 * @date 13.07.2023 14:05
 */
public class Tournament {
    private ArrayList<Integer> wins;
    public Tournament(final String[] players, final PlayerType[] types, final GameConfiguration configuration, ImmutableList<Card> cards, int numbergames) throws Exception {
        final ArrayList<GameBoard> states = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < numbergames; i++) {
            if (List.of(types).contains(PlayerType.ADVANCED_AI)) {
                System.out.println("\n\n\n\n");
                System.out.println("RUNDE: " + i);
                System.out.println("\n\n\n\n");
            } else {
                if (x != (int) (((double) i / numbergames) * 100))
                    System.out.println("Status: "+ (int) (((double) i / numbergames) * 100) + "%");
                x = (int) (((double) i / numbergames) * 100);
            }
            if (List.of(types).contains(PlayerType.HUMAN))
                if (i == 0) {
                    states.add(new GameBoard(players, types, configuration, cards));
                    states.get(i).initGame(null);
                } else {
                    while (states.get(i - 1).getRun()) ;
                    states.add(new GameBoard(players, types, configuration, cards));
                    states.get(i).initGame(null);
                }
            else {
                states.add(new GameBoard(players, types, configuration, cards));
                states.get(i).initGame(null);
            }
        }
        System.out.println(states.size());
        int[] scores;
        wins = new ArrayList<>();
        wins.clear();
        for (int i = 0; i < types.length + 1; i++) wins.add(0);
        for (final GameBoard state : states) {
            if (state.getWinner() == null)
                wins.set(types.length, wins.get(types.length) + 1);
            else
                wins.set(state.getWinner().getPlayerID(), wins.get(state.getWinner().getPlayerID()) + 1);
        }
        System.out.println(wins);
    }
    public ArrayList<Integer> getWins() {
        return wins;
    }

}
