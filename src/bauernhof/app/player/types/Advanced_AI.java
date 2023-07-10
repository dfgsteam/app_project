package bauernhof.app.player.types;
import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.app.player.types.MoveTree.Threads.SequenceThread;
import bauernhof.app.player.types.MoveTree.Threads.WorkingThread;
import bauernhof.preset.Move;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class Advanced_AI extends AbstractGamePlayer implements AIHeader {
    private GameBoardState gameboardstate;
    public Advanced_AI(String name, final GameBoardState gameboardstate) {
        super(name, PlayerType.ADVANCED_AI);
        this.gameboardstate = gameboardstate;
    }

    @Override
    public Move calculateNextMove() throws Exception {
        WorkingThread workingThread1 = new WorkingThread(gameboardstate);
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
