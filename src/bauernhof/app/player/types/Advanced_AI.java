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
    private GameBoardState gameboardstate;
    private ArrayList<Integer> times = new ArrayList<>();
    private long first, second;
    public Advanced_AI(String name) {
        super(name, PlayerType.ADVANCED_AI);
    }
    public void setGameBoardState(final GameBoardState gameboardstate) {
        this.gameboardstate = gameboardstate;
    }


    @Override
    public Move request() throws Exception {
        first = System.currentTimeMillis();
        WorkingThread workingThread1 = new WorkingThread(gameboardstate.clone());
        // WorkingThread workingThread2 = new WorkingThread();
        // WorkingThread workingThread3 = new WorkingThread();
        // WorkingThread workingThread4 = new WorkingThread();

        // try {
        //     workingThread1.join();
        //     workingThread2.join();
        //     workingThread3.join();
        //     workingThread4.join();
        // }
        // catch (InterruptedException e) {
        //     System.err.println("ERROR");
        // }

        SequenceThread sequenceThread1 = new SequenceThread(true);
        // SequenceThread sequenceThread2 = new SequenceThread(false);
        // SequenceThread sequenceThread3 = new SequenceThread(false);
        // SequenceThread sequenceThread4 = new SequenceThread(false);

        // try {
        //     sequenceThread1.join();
        //     sequenceThread2.join();
        //     sequenceThread3.join();
        //     sequenceThread4.join();
        // }
        // catch (InterruptedException e) {
        //     System.out.println("ERROR");
        // }

        final Move move  = AbstractThread.getTree().getRootNode().getNextNodes().get(SequenceThread.differences.indexOf(Collections.max(SequenceThread.differences))).getMove();
        times.add((int) (System.currentTimeMillis() - first));
        System.out.println("AI ROUND: " + this.gameboardstate.getRound());
        System.out.println("CURRENT: " + (System.currentTimeMillis() - first));
        int y = 0;
        for (final int x : times)
            y += x;
        System.out.println("AVERAGE: " + (y/times.size()));
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
