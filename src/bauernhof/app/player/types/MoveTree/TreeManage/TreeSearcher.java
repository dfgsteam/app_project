package bauernhof.app.player.types.MoveTree.TreeManage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;

/**
 * This Class was written by
 * @author Viktor Tevosyan
 * @date 10.07.2023
 * This class reprents a search class, which will go through a given tree and be useful for finding the best Move for the Advanced_AI
 */
public class TreeSearcher extends AbstractCalculations {

    /**
     * Which MoveNodes of the Advanced_AI at the begining it has to go yet
     */
    private Queue<MoveNode> next_calculations;
    /**
     * Differences of points of Advanced_AI at the RootNode, after taking a Move at the begining and going Deeper through the whole tree
     */
    public ArrayList<Integer> differences;

    /**
     * Paramterized constructor, which initialises the Tree to work with
     * @param tree
     * @throws Exception
     */
    public TreeSearcher(MoveTree tree) throws Exception {
        this.setTree(tree);     //Set the Tree
        next_calculations = new LinkedList<MoveNode>(this.getTree().getRootNode().getNextNodes());          //Next Calculations are all useful moves of the Advanced_AI of the begining of the tree
            

        differences = new ArrayList<Integer>();         //Points differences for the Advanced_AI
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

    /**
     * Helps to determine which Enemy has the most points in the actual GameBoard
     * @return
     */
    private final int maxEnemyPoints() {
        int points = 0;
        points = 0 == getTree().getRootNode().getActualBoard().getCurrentPlayerID() ? this.getCurrentNode().getActualBoard().getPlayerCards(1).getScore() : this.getCurrentNode().getActualBoard().getPlayerCards(0).getScore();
        //


        for (int i = 0; i < this.getCurrentNode().getActualBoard().getNumPlayers(); i++) {
            if (i == this.getCurrentNode().getActualBoard().getCurrentPlayerID()) { continue;    }   //just skip the current Player
            
        
    
            if (this.getCurrentNode().getActualBoard().getPlayerCards(i).getScore() > points) { points = this.getCurrentNode().getActualBoard().getPlayerCards(i).getScore();}
                
        }
        return points;
    }

    @Override
    public boolean treeSearcherAction() {
            if (next_calculations.isEmpty()) {
                return false;
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
            return true;
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
