package bauernhof.app.player.types;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Advanced_AI extends AbstractGamePlayer implements AIHeader {

    private MoveNode actual_movepos;

    public Advanced_AI(String name, GameBoardState state, PlayerType type) {
        super(name, state, type);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Move calculateNextMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateNextMove'");
    }

    @Override
    public Card cardFromDeposit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cardFromDeposit'");
    }

    @Override
    public Card cardFromStack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cardFromStack'");
    }

    @Override
    public Card removeFromOwn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromOwn'");
    }
    
}
