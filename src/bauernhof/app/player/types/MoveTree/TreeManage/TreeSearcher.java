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
 * This class reprents a search class, which will go through a given tree and be useful for finding the best Move for the Advanced_AI.
 * The Tree Searches goes through all neccessarly possible Moves of the Advacned_AI which were calculated by the TreeCreator class.
 * For each ChildrenNodes it supposes further Players making the most Scorable Moves -> that's the method how the TreeSearcher goes deeper.
 * After reaching the end of the Tree for each Advanced_AI move of the RootNode, the tree stores differences of points between Advanced_AI and the most high Enemy Score
 * The Move, which the Advanced_AI should make will be decided by picking the most best difference of points for the Advanced_AI and the enemy players at the endö.
 */
public class TreeSearcher extends AbstractCalculations {

    /**
     * Which MoveNodes of the Advanced_AI at the begining it has to go yet
     */
    private Queue<MoveNode> next_calculations;
    /**
     * Differences of points between Advanced_AI and the most scorable enemy player at the end of the Sequence
     */
    public ArrayList<Integer> differences;

    //---------------------------------------------------------------------------------------------------------------------------------

    /**
     * Paramterized constructor, which initialises the Tree to work with
     * @param tree
     * @throws Exception
     */
    public TreeSearcher(MoveTree tree) throws Exception {
        this.setTree(tree);     //Set the Tree
        next_calculations = new LinkedList<MoveNode>(this.getTree().getRootNode().getNextNodes());          //Next Calculations are all useful moves of the Advanced_AI of the begining of the tree
        differences = new ArrayList<Integer>();         //Points differences for the Advanced_AI
        while (treeSearcherAction());           //Build MoveSequence while there are remaining Moves of the Advanced_AI at the begining
    }

    /**
     * Returns the ChildNode, which gives the Current Player the Max-points after making nthe Move
     * @return Best ChildNode for the Actual Player
     */
    private final MoveNode getBestOfActual() {
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
     * Helps to determine which Enemy (not RootNode Advnaced_AI) has the most points
     * @return Max Points of all enemies
     */
    private final int maxEnemyPoints() {
        int max_points = 0 == this.getTree().getRootNode().getActualBoard().getCurrentPlayerID() ? this.getCurrentNode().getActualBoard().getPlayerCards(1).getScore() : this.getCurrentNode().getActualBoard().getPlayerCards(0).getScore();
        for (int i = 0; i < this.getCurrentNode().getActualBoard().getNumPlayers(); i++) {
            if (i == this.getTree().getRootNode().getActualBoard().getCurrentPlayerID()) { continue; }   //just skip the AI, who calculates the Tree
            else if (this.getCurrentNode().getActualBoard().getPlayerCards(i).getScore() > max_points) { max_points = this.getCurrentNode().getActualBoard().getPlayerCards(i).getScore(); }        //Check if the viewed enemy has more points than max, if yes set new max  
        }
        return max_points;
    }

    @Override
    public boolean treeSearcherAction() {
        if (next_calculations.isEmpty()) { return false; }      //There are no more moveSequences to calculate
        this.setCurrentNode(next_calculations.remove());        //Set Current Node to the remaining Advanced_AI MoveNodes
        while (goDeeper());     //Go Deeper while you can
        differences.add(this.getCurrentNode().getActualBoard().getPlayerCards(getTree().getRootNode().getActualBoard().getCurrentPlayerID()).getScore() - maxEnemyPoints());    //difference between Advancefd_AI Points and max ENemyPoints
        return true;
    }

    @Override
    public boolean goDeeper() {
        if (this.getCurrentNode().getNextNodes().isEmpty()) { return false; }       //if there are no children anymore, you can't go deeper
        this.setCurrentNode(getBestOfActual());                     //CurrentNode becomes the Node, where Current Player of the Old Node gets most points
        return true;
    }


   
    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcNextNode'");
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean treeCreatorAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'treeCreatorAction'");
    }

    
}
