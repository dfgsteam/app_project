package bauernhof.app.player.types.MoveTree.TreeManage;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
/**
 * This interface was written by
 * @author Viktor Tevosyan
 * @date 30.06.2023
 * This interface is used to create a Structure for Future Classes, which will use the Tree Class and the Node Class to create a MoveTree, to manage it and to determine which Move the AdvancedAI should make to get a long term advantage
 */
public interface CalculationsHandler {
    
   
    /**
     * Calculates a nextNode in dependence of which card should be taken and which card should be put
     * Connects actualNode with the child and sets the calculated Child to the Actual
     * @param NumberOfTakenCard
     * @param NumberOfCardToPut
     * @return true, if Calculation successful
     * @return false, if calculation not possible (for example maxDepth or game Over)
     * @throws Exception
     */
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception;

    /**
     * ActionMethod (main-Method) for the TreeCreator class
     * Creates Children for the Actual Node if possible
     * @return true, if possible
     * @return false, if not possible
     */
    public boolean treeCreatorAction() throws Exception;

    /**
     * Setter for the Current Node of a Calculational Class
     * @param MoveNode
     */
    public void setCurrentNode(MoveNode move_node);

    /**
     * Getter for the current Node of a calculational Class
     * @return MoveNode
     */
    public MoveNode getCurrentNode();

    /**
     * Tree Searcher Enters a Move of the Advanced_AI and supposes, that all Players are going to make the Moves, which give them the Most points.
     * After Reaching last Node, Points difference between Advanced_AI and highest Enemy are calculated, so the most advantageable MOve can be found
     * @return true, if successful
     * @return false, if no calculations left
     */
    public boolean treeSearcherAction();

    /**
     * Getter for the MAX_Depth of the tree
     * @return int
     */
    public int getMaxDepth();

    /**
     * Go deeper in the tree
     */
    public boolean goDeeper();

    /**
     * Getter for the Tree of Calculational Object
     * @return MoveTree
     */
    public MoveTree getTree();

    /**
     * Setter for the Tree of Calculational Object
     * @param MoveTree
     */
    public void setTree(MoveTree tree);
}
