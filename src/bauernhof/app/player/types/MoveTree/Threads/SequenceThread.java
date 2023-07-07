package bauernhof.app.player.types.MoveTree.Threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.PlayerType;

public class SequenceThread extends AbstractThread{
    private static Queue<MoveNode> next_calculations;
    private static ArrayList<Integer> differences;

    public SequenceThread(MoveTree tree, boolean first) {
        if (first) {
            this.setThreadNode(tree.getRootNode());
            next_calculations = new LinkedList<MoveNode>(this.getThreadNode().getNextNodes());
            differences = new ArrayList<Integer>();
            start();
        }

        else if (!next_calculations.isEmpty()) {
            this.setThreadNode(this.getTree().getRootNode().getNextNodes().get(differences.indexOf(Collections.max(differences))));
        }
    }



    private final int biggest_difference(MoveNode node) throws Exception {
        int diff = node.getActualBoardState().getActualPlayer() == node.getActualBoardState().getPlayers()[0] ? node.getActualBoardState().getActualPlayer().getScore() - node.getActualBoardState().getPlayers()[1].getScore() : node.getActualBoardState().getActualPlayer().getScore() - node.getActualBoardState().getPlayers()[0].getScore();
        for (AbstractGamePlayer player : node.getActualBoardState().getPlayers()) {
            if (player == node.getActualBoardState().getActualPlayer()) { continue; }
            if (node.getActualBoardState().getActualPlayer().getScore() - player.getScore() < diff) { diff = node.getActualBoardState().getActualPlayer().getScore() - player.getScore(); }
        }
        return diff;
    }

    private final int AdvancedAI_Advantage(MoveNode node) throws Exception {
        AbstractGamePlayer advance = null;
        for (AbstractGamePlayer player : node.getActualBoardState().getPlayers()) {
            if (player.getPlayerID() == this.getTree().getRootNode().getActualBoardState().getActualPlayer().getPlayerID()) {
                advance = player;
                break;
            }
        }
        int diff = advance == node.getActualBoardState().getPlayers()[0] ? advance.getScore() - node.getActualBoardState().getPlayers()[1].getScore() : advance.getScore() - node.getActualBoardState().getPlayers()[0].getScore();
        for (AbstractGamePlayer player : node.getActualBoardState().getPlayers()) {
            if (player == advance) { continue; }
            if (advance.getScore() - player.getScore() < diff) {
                diff = advance.getScore() - player.getScore();
            }
        }
        return diff;

    }
    

    @Override
    public MoveNode getBestOfActual() throws Exception {
        MoveNode best = this.getThreadNode().getNextNodes().get(0);
        int diff = biggest_difference(best);
        for (MoveNode node : this.getThreadNode().getNextNodes()) {
            if (biggest_difference(node) > diff) {
                best = node;
                diff = biggest_difference(best);
            }
        }
        return best;
    }

    @Override
    synchronized public void sequenceThreadAction() {
        this.setThreadNode(SequenceThread.next_calculations.remove());
        while(goDeeper()) {
            try {
                this.setThreadNode(getBestOfActual());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            differences.add(AdvancedAI_Advantage(getThreadNode()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean goDeeper() {
        if (this.getThreadNode().getDepth() < this.getMaxDepth()) { return true; }
        return false;
    }

    @Override
    public void run() {
        while(!next_calculations.isEmpty()) {
            sequenceThreadAction();
        }
        interr
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
