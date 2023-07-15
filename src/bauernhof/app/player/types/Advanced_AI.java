package bauernhof.app.player.types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.sound.midi.Sequence;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.Threads.AbstractThread;
import bauernhof.app.player.types.MoveTree.Threads.SequenceThread;
import bauernhof.app.player.types.MoveTree.Threads.WorkingThread;
import bauernhof.app.settings.Se;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Advanced_AI extends AbstractGamePlayer implements AIHeader {

    private static Object monitor = new Object();

    private ArrayList<Long> currentimes = new ArrayList<>();
    private long before;
    private GameBoardState gameboardstate;
    private WorkingThread treeCalculator;
    private SequenceThread sequencePicker;

    public Advanced_AI(String name) {
        super(name, PlayerType.ADVANCED_AI);
        try {
            treeCalculator = new WorkingThread();
            sequencePicker = new SequenceThread(treeCalculator);
            treeCalculator.setSequenceThread(sequencePicker);
        } catch (Exception e) {
            System.err.println("ERROR");
        }
        // sequencePicker = new SequenceThread();
    }
    public void setGameBoardState(final GameBoardState gameboardstate) {
        this.gameboardstate = gameboardstate;
    }


    @Override
    public Move request() throws Exception {
        
        treeCalculator.setUp(gameboardstate.clone());
        treeCalculator.go();

        while (!(treeCalculator.getState() == Thread.State.WAITING && sequencePicker.getState() == Thread.State.WAITING));
        
        System.out.println(sequencePicker.differences);
        Move move = AbstractThread.getTree().getRootNode().getNextNodes().get(sequencePicker.differences.indexOf(Collections.max(sequencePicker.differences))).getMove();

        System.out.println("ROUND: " + gameboardstate.getRound());
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
