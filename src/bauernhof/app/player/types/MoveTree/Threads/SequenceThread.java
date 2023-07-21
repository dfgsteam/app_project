package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
            while (!SequenceThread.next_calculations.isEmpty()) { sequenceThreadAction(); }
        }

        else {
            if (!next_calculations.isEmpty()) {
                while (!SequenceThread.next_calculations.isEmpty()) { sequenceThreadAction(); }
            }
        }

    }

    @Override
    public MoveNode getBestOfActual() throws Exception {
        int max_points = this.getThreadNode().getNextNodes().get(0).getActualBoardState().getPlayerCards(this.getThreadNode().getActualBoardState().getCurrentPlayerID()).getScore();
        int index = 0;
        int i = 0;
        for (MoveNode node : this.getThreadNode().getNextNodes()) {
            if (max_points < node.getActualBoardState().getPlayerCards(this.getThreadNode().getActualBoardState().getCurrentPlayerID()).getScore()) {
                max_points = node.getActualBoardState().getPlayerCards(this.getThreadNode().getActualBoardState().getCurrentPlayerID()).getScore();
                index = i;
            }
            i++;
        }

        return this.getThreadNode().getNextNodes().get(index);
    }

    private final int maxEnemyPoints() {
        int points = 0;
        try {
            /*
            TODO: Das ergibt keinen Sinn bei mehr als 2 Spielern
             */
            points = 0 == getTree().getRootNode().getActualBoardState().getCurrentPlayerID() ? this.getThreadNode().getActualBoardState().getPlayerCards(1).getScore() : this.getThreadNode().getActualBoardState().getPlayerCards(0).getScore();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        for (int i = 0; i < this.getThreadNode().getActualBoardState().getNumPlayers(); i++) {
            if (i == getTree().getRootNode().getActualBoardState().getCurrentPlayerID()) {
                continue;
            }
        
            try {
                if (this.getThreadNode().getActualBoardState().getPlayerCards(i).getScore() > points) {
                    points = this.getThreadNode().getActualBoardState().getPlayerCards(i).getScore();
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
        // synchronized (next_calculations) {
            if (next_calculations.isEmpty()) {
                return;
            }
            else {
                this.setThreadNode(next_calculations.remove());
                while (goDeeper());
                try {

                    differences.add(this.getThreadNode().getActualBoardState().getPlayerCards(getTree().getRootNode().getActualBoardState().getCurrentPlayerID()).getScore() - maxEnemyPoints());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        // }
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
    // @Override
    // public void run() {
    //     while (!SequenceThread.next_calculations.isEmpty()) { sequenceThreadAction(); }
    // }
    
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
