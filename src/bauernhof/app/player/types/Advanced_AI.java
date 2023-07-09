package bauernhof.app.player.types;
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
    public Advanced_AI(String name) {
        super(name, PlayerType.ADVANCED_AI);
    }
    public void setGameBoardState(final GameBoardState gameboardstate) {
        this.gameboardstate = gameboardstate;
    }


    @Override
    public Move calculateNextMove() throws Exception {
        WorkingThread workingThread1 = new WorkingThread(gameboardstate);
        System.out.println("MOVE");
        WorkingThread workingThread2 = new WorkingThread();
        System.out.println("MOVE");
        WorkingThread workingThread3 = new WorkingThread();
        System.out.println("MOVE");
        WorkingThread workingThread4 = new WorkingThread();

        try {
            workingThread1.join();
            workingThread2.join();
            workingThread3.join();
            workingThread4.join();
        }
        catch (InterruptedException e) {
            System.err.println("ERROR");
        }
        System.out.println("ENDE");

        System.out.println(WorkingThread.getTree().getRootNode());
        System.out.println(WorkingThread.getTree().getRootNode().getNextNodes().size());
        for (MoveNode node : WorkingThread.getTree().getRootNode().getNextNodes()) {
            System.out.println(node.getNextNodes().size());
        }
        System.exit(0);

        SequenceThread sequenceThread1 = new SequenceThread(gameboardstate);
        SequenceThread sequenceThread2 = new SequenceThread();
        SequenceThread sequenceThread3 = new SequenceThread();
        SequenceThread sequenceThread4 = new SequenceThread();

        try {
            sequenceThread1.join();
            sequenceThread2.join();
            sequenceThread3.join();
            sequenceThread4.join();
        }

        catch (InterruptedException e) {
            System.err.println("Error");
        }

        return SequenceThread.bestNode().getMove();
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
