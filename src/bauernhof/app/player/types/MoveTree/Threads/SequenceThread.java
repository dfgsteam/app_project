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

public class SequenceThread extends AbstractThread {

    private static Queue<MoveNode> next_calculations;
    public static ArrayList<Integer> differences;


    public SequenceThread(boolean first) {
        if (first) {
            next_calculations = new LinkedList<MoveNode>(getTree().getRootNode().getNextNodes());
            differences = new ArrayList<Integer>();
            start();
        }
        
         else { start(); }
    }

    private final int mostScoreNode() throws Exception {
        int max = this.getThreadNode().getNextNodes().get(0).getActualBoardState().getPlayers()[this.getThreadNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore();
        int index = 0;
        for (MoveNode node : this.getThreadNode().getNextNodes()) {
            if (node.getActualBoardState().getPlayers()[getThreadNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore() > max) {
                max = node.getActualBoardState().getPlayers()[getThreadNode().getActualBoardState().getActualPlayer().getPlayerID()].getScore();
                index = this.getThreadNode().getNextNodes().indexOf(node);
            }
        }
        return index;
    }

    private final int sumOfAllEnemyPoints() throws Exception {
        int sum = 0;
        for (AbstractGamePlayer player : this.getThreadNode().getActualBoardState().getPlayers()) {
            if (player == getTree().getRootNode().getActualBoardState().getActualPlayer()) { continue; }
            System.out.println(player.getScore());
            sum += player.getScore();
        }
        
        return sum;
    }
    

    

    @Override
    public void sequenceThreadAction() {
        synchronized (next_calculations) {
            if (next_calculations.isEmpty()) {
                return; 
            }
            this.setThreadNode(next_calculations.remove());
            while (goDeeper());
            try {
                differences.add(sumOfAllEnemyPoints());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean goDeeper() {
        if (this.getThreadNode().getNextNodes().isEmpty() || this.getThreadNode().getDepth()+1 >= getMaxDepth()) { return false; }
        try {
            this.setThreadNode(getThreadNode().getNextNodes().get(mostScoreNode()));
        } catch (Exception e) {
            System.err.println("Deeper Error");
        }
        return true;
    }


    //-------------
    @Override
    public void run() {
        while (!next_calculations.isEmpty()) { sequenceThreadAction(); }
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

    @Override
    public MoveNode getBestOfActual() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBestOfActual'");
    }
}
