package bauernhof.app.player.types.MoveTree.TreeManage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;


public class TreeSearcher extends AbstractCalculations {

    private Queue<MoveNode> next_calculations;
    public ArrayList<Integer> differences;

    public TreeSearcher(MoveTree tree) throws Exception {
        this.setTree(tree);
        next_calculations = new LinkedList<MoveNode>(this.getTree().getRootNode().getNextNodes());
            

        differences = new ArrayList<Integer>();
        while (!this.next_calculations.isEmpty()) { treeSearcherAction(); }
    }

    @Override
    public MoveNode getBestOfActual() throws Exception {
        int max_points = this.getCurrentNode().getNextNodes().get(0).getActualBoard().getPlayerCards(this.getCurrentNode().getActualBoard().getCurrentPlayerID()).getScore();
        int index = 0;
        int i = 0;
        for (MoveNode node : this.getCurrentNode().getNextNodes()) {
            if (max_points < node.getActualBoard().getPlayerCards(this.getCurrentNode().getActualBoard().getCurrentPlayerID()).getScore()) {
                max_points = node.getActualBoard().getPlayerCards(this.getCurrentNode().getActualBoard().getCurrentPlayerID()).getScore();
                index = i;
            }
            i++;
        }

        return this.getCurrentNode().getNextNodes().get(index);
    }

    private final int maxEnemyPoints() {
        int points = 0;
        try {
            /*
            TODO: Das ergibt keinen Sinn bei mehr als 2 Spielern
             */
            points = 0 == getTree().getRootNode().getActualBoard().getCurrentPlayerID() ? this.getCurrentNode().getActualBoard().getPlayerCards(1).getScore() : this.getCurrentNode().getActualBoard().getPlayerCards(0).getScore();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        for (int i = 0; i < this.getCurrentNode().getActualBoard().getNumPlayers(); i++) {
            if (i == getTree().getRootNode().getActualBoard().getCurrentPlayerID()) {
                continue;
            }
        
            try {
                if (this.getCurrentNode().getActualBoard().getPlayerCards(i).getScore() > points) {
                    points = this.getCurrentNode().getActualBoard().getPlayerCards(i).getScore();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return points;
    }

    @Override
    public void treeSearcherAction() {
        // synchronized (next_calculations) {
            if (next_calculations.isEmpty()) {
                return;
            }
            else {
                this.setCurrentNode(next_calculations.remove());
                while (goDeeper());
                try {

                    differences.add(this.getCurrentNode().getActualBoard().getPlayerCards(getTree().getRootNode().getActualBoard().getCurrentPlayerID()).getScore() - maxEnemyPoints());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        // }
    }

    @Override
    public boolean goDeeper() {
        if (this.getCurrentNode().getNextNodes().isEmpty() || this.getCurrentNode().getDepth() >= this.getMaxDepth()) {
            return false;
        }
        try {
            this.setCurrentNode(getBestOfActual());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }


   
    //Not usable methods
    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcNextNode'");
    }

    @Override
    public boolean treeCreatorAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'treeCreatorAction'");
    }

    
}
