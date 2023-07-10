package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.PlayerType;
import bauernhof.preset.card.Card;

public class SequenceThread extends AbstractThread {

    private static Queue<MoveNode> next_calculations;
    public static ArrayList<Integer> differences;

    public SequenceThread(boolean first) throws Exception {
        if (first) {
            this.setThreadNode(getTree().getRootNode());
            next_calculations = new LinkedList<MoveNode>(getTree().getRootNode().getNextNodes());
            differences = new ArrayList<Integer>();
            run();
        }

        else {
            if (!next_calculations.isEmpty()) {
                run();
            }
        }

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
            points = this.getThreadNode().getActualBoardState().getPlayers()[0] == getTree().getRootNode().getActualBoardState().getActualPlayer() ? this.getThreadNode().getActualBoardState().getPlayers()[1].getScore() : this.getThreadNode().getActualBoardState().getPlayers()[0].getScore();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (AbstractGamePlayer player : this.getThreadNode().getActualBoardState().getPlayers()) {
            if (player == getTree().getRootNode().getActualBoardState().getActualPlayer()) {
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
        synchronized (next_calculations) {
            if (next_calculations.isEmpty()) {
                return;
            }
            else {
                this.setThreadNode(next_calculations.remove());
                while (goDeeper());
                try {
                    differences.add(this.getThreadNode().getActualBoardState().getPlayers()[getTree().getRootNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore() - maxEnemyPoints());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean goDeeper() {
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


   


    //-------------
    @Override
    public void run() {
        while (!SequenceThread.next_calculations.isEmpty()) { sequenceThreadAction(); }
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
