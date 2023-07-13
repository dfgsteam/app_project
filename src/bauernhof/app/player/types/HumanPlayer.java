package bauernhof.app.player.types;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

/**
 * Human Player - PlayerType Human Instanz
 *
 * @author Ramon Cemil Kimyon
 * @date 10.06.2023 15:15
 */
public class HumanPlayer extends AbstractGamePlayer {
    private Move move;
    private Card add;
    private Move humanmove;
    private GameBoardState state;
    public HumanPlayer(String name, GameBoardState gameboardstate) {
        super(name, PlayerType.HUMAN);
        this.state = gameboardstate;
    }

    @Override
    public Move request() throws Exception {
        return this.humanmove;
    }
    public final void doMove(final Card remove) throws Exception {
        System.out.println(add);
        this.humanmove = new Move(add, remove);
        state.doMove(this.humanmove);
        System.out.println("THIS");
    }

    public final void setAdd(final Card card) {
        this.add = card;
        this.add(card);
    }
}
