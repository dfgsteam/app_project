package bauernhof.app.player.types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.types.MoveTree.MoveTree;
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
    private GameBoard board_before = null;
    private Move move_before = null;
    private MoveTree tree_before;

    public Advanced_AI(final Settings settings, final PlayerCards playercards, final GameBoard gameboard) {
        super(settings, playercards, gameboard);
    }

    @Override
    public Move request() throws Exception {
        before = System.currentTimeMillis();
        if (board_before != null) {
            HashSet<Card> before_depoCards = new HashSet<Card>(board_before.getDepositedCards()), actual_depoCards = new HashSet<Card>(this.getGameBoard().getDepositedCards());
            Card before_drawCard = board_before.getDrawPileCards().get(board_before.getDrawPileCards().size()-1), actual_drawCard = this.getGameBoard().getDrawPileCards().get(this.getGameBoard().getDrawPileCards().size()-1);

           
            if (before_depoCards.equals(actual_depoCards) && before_drawCard.equals(actual_drawCard)) {
            return move_before; 
            }
        }

        board_before = this.getGameBoard().clone();
        WorkingThread workingThread1 = new WorkingThread(gameBoard.clone());

        SequenceThread sequenceThread1 = new SequenceThread(true);
        
        System.out.println(SequenceThread.differences);
        Move move = workingThread1.getTree().getRootNode().getNextNodes().get(SequenceThread.differences.indexOf(Collections.max(SequenceThread.differences))).getMove();

        currentimes.add(System.currentTimeMillis() - before);
        int x = 0;
        for(Long b : currentimes)
            x += b;
        System.out.println("AVERAGE: " + x/currentimes.size());
        System.out.println("CURRENT: " + (System.currentTimeMillis() - before));
        System.out.println("");
        move_before = move;
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
