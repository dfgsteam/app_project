package bauernhof.app.player.types;
import java.awt.*;
import java.util.Collections;

import bauernhof.app.player.PlayerCards;
import bauernhof.app.system.GameBoard;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.Threads.AbstractThread;
import bauernhof.app.player.types.MoveTree.Threads.SequenceThread;
import bauernhof.app.player.types.MoveTree.Threads.WorkingThread;
import bauernhof.app.system.GameSystem;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

public class Advanced_AI extends AbstractGamePlayer implements AIHeader {
    private GameSystem gameboardstate;
    public Advanced_AI(final Settings settings, final PlayerCards playercards, final GameSystem gamesystem) {
        super(settings, playercards, gamesystem);
        this.gameboardstate = gamesystem;
    }


    @Override
    public Move request() throws Exception {
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
        System.out.println(SequenceThread.differences);   
        return AbstractThread.getTree().getRootNode().getNextNodes().get(SequenceThread.differences.indexOf(Collections.max(SequenceThread.differences))).getMove();
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
