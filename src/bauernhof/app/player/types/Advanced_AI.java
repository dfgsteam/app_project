package bauernhof.app.player.types;
import java.util.ArrayList;
import java.util.Collections;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.types.MoveTree.Threads.AbstractThread;
import bauernhof.app.player.types.MoveTree.Threads.SequenceThread;
import bauernhof.app.player.types.MoveTree.Threads.WorkingThread;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

public class Advanced_AI extends AbstractGamePlayer implements AIHeader {
    private ArrayList<Long> currentimes = new ArrayList<>();
    private long before;
    public Advanced_AI(final Settings settings, final PlayerCards playercards, final GameBoard gameboard) {
        super(settings, playercards, gameboard);
    }

    @Override
    public Move request() throws Exception {
        before = System.currentTimeMillis();
        WorkingThread workingThread1 = new WorkingThread(gameBoard.clone());

        SequenceThread sequenceThread1 = new SequenceThread(true);
        
        System.out.println(SequenceThread.differences);
        Move move = AbstractThread.getTree().getRootNode().getNextNodes().get(SequenceThread.differences.indexOf(Collections.max(SequenceThread.differences))).getMove();

        System.out.println("ROUND: " + gameBoard.getRound());
        System.out.println("CURRENT: " + (System.currentTimeMillis() - before));
        currentimes.add(System.currentTimeMillis() - before);
        int x = 0;
        for(Long b : currentimes)
            x += b;
        System.out.println("AVERAGE: " + x/currentimes.size());
        System.out.println("");
        return move;
      }

    //Not usable methods
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
    public Card removeFromOwn(Card to_take) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromOwn'");
    }
    
}
