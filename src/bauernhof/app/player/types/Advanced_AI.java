package bauernhof.app.player.types;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.app.player.types.MoveTree.Threads.SequenceThread;
import bauernhof.app.player.types.MoveTree.Threads.WorkingThread;
import bauernhof.app.settings.Se;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Advanced_AI extends AbstractGamePlayer implements AIHeader {

    public Advanced_AI(String name, GameBoardState state) {
        super(name, state, PlayerType.ADVANCED_AI);
    }

    @Override
    public Move calculateNextMove() {
        WorkingThread workingThread1 = new WorkingThread(this.getState());
        WorkingThread workingThread2 = new WorkingThread(workingThread1.getTree());
        WorkingThread workingThread3 = new WorkingThread(workingThread1.getTree());
        WorkingThread workingThread4 = new WorkingThread(workingThread1.getTree());

        try {
            workingThread1.join();
            workingThread2.join();
            workingThread3.join();
            workingThread4.join();
        }
        
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        MoveTree tree = workingThread1.getTree();

        SequenceThread sequenceThread1 = new SequenceThread(tree, true);
        SequenceThread sequenceThread2 = new SequenceThread(tree, false);
        SequenceThread sequenceThread3 = new SequenceThread(tree, false);

        try {
            sequenceThread1.join();
            sequenceThread2.join();
            sequenceThread3.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        MoveNode move_node = sequenceThread1.getThreadNode();
        return move_node.getMove();
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
    public Card removeFromOwn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromOwn'");
    }
    
}
