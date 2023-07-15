package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.Advanced_AI;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class SequenceThread extends AbstractThread {

    private Queue<MoveNode> next_calculations = new LinkedList<MoveNode>();
    public ArrayList<Integer> differences = new ArrayList<>();
    public WorkingThread workingThread;

    public SequenceThread(WorkingThread workingThread) throws Exception {
        super();
        this.workingThread = workingThread;
        start();
    }

    @Override
    public MoveNode getBestOfActual() throws Exception {
            int max_points = this.getThreadNode().getNextNodes().get(0).getActualBoardState().getPlayers()[this.getThreadNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore();
            int index = 0;
            int i = 0;
            for (MoveNode node : this.getThreadNode().getNextNodes()) {
                if (max_points < node.getActualBoardState().getPlayers()[this.getThreadNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore()) {
                    max_points = node.getActualBoardState().getPlayers()[this.getThreadNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore();
                index = i;
            }
            i++;
        }

        return this.getThreadNode().getNextNodes().get(index);
    }

    private final int maxEnemyPoints() {
        int points = 0;
        try {
            points = this.getThreadNode().getActualBoardState().getPlayers()[0].getPlayerID() == getTree().getRootNode().getActualBoardState().getActualPlayer().getPlayerID() ? this.getThreadNode().getActualBoardState().getPlayers()[1].getScore() : this.getThreadNode().getActualBoardState().getPlayers()[0].getScore();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (AbstractGamePlayer player : this.getThreadNode().getActualBoardState().getPlayers()) {
            if (player.getPlayerID() == getTree().getRootNode().getActualBoardState().getActualPlayer().getPlayerID()) {
                continue;
            }
        
            try {
                if (player.getScore() > points) {
                    points = player.getScore();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return points;
    }

    @Override
    public void sequenceThreadAction() {
        if (next_calculations.isEmpty()) {
                return;
            }
            
        else {
            this.setThreadNode(next_calculations.remove());
        }

                while (goDeeper());
                try {
                        differences.add(this.getThreadNode().getActualBoardState().getPlayers()[getTree().getRootNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore() - maxEnemyPoints());
                    }
                 catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    @Override
    public boolean goDeeper() {
        if (workingThread.getState() != Thread.State.WAITING && this.getThreadNode().getDepth() >= workingThread.getCalculatedDepth()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return goDeeper();
        }
        if (this.getThreadNode().getNextNodes().isEmpty() || this.getThreadNode().getDepth() >= this.getMaxDepth()) {
            return false;
        }

        try {
            this.setThreadNode(getBestOfActual());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public void setUp() {
        next_calculations = new LinkedList<MoveNode>(getTree().getRootNode().getNextNodes());
        differences = new ArrayList<Integer>();
    }


   


    // -------------
    @Override
    public void run() {
        while (true) {
            if (!this.next_calculations.isEmpty()) {
                sequenceThreadAction();
            }

            else {
                try {
                    this.pause(0);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    //Not usable methods
    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcNextNode'");
    }

    @Override
    public boolean workingThreadAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'workingThreadAction'");
    }

    
}
