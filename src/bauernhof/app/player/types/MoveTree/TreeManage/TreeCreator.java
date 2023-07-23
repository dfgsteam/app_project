package bauernhof.app.player.types.MoveTree.TreeManage;

import java.util.LinkedList;
import java.util.Queue;

import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.card.Card;

/**
 * This class was written by
 * @author Viktor Tevosyan
 * @date 07.07.2023
 * This class reprersents a TreeCreator which receives the Information about the Board and creates a Tree of Moves with the Depth of (MAX_DEPTH). This tree is used by the TreeSearcher Class to determine which MoveSequence is the best, so the AdvancedAI can make a move
 */
public class TreeCreator extends AbstractCalculations {
    /**
     * A Queue of Next_Calculations, which helps to make a Tree (kind of BFS)
     */
    private Queue<MoveNode> next_calculations;

    /**
     * The MaxScore for the Advanced_AI for the ChildrenNodes of the ActualNode
     */
    private int max;

    

    /**
     * Parametrized constructor, which creates a complete Tree Of necessary moves
     * @throws Exception
     */
    public TreeCreator(GameBoard actual_state) throws Exception {
        super(actual_state);        //create a tree with the empty root node, which contains the informatiuon about the GameBoard
        this.next_calculations = new LinkedList<MoveNode>();            //create the queue for the next calculations
        max = -100000;         //set max to minimum
        this.setCurrentNode(getTree().getRootNode());       //Set current Node to the Root Node of created tree
        treeCreatorAction();               //Calculate necessary Children to the RootNode (useful Advanced_AI moves)
        while (treeCreatorAction());        //Calculate the Tree while next_calculations are not empty
    }


    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception {
        Move new_move = checkMove(cardNumPut, cardNumTake);         //check if it would make sense to calculate a MoveNode, which will contain the Move
        if (new_move == null) { return false; }             //if not, then return false
        GameBoard new_state = this.getCurrentNode().getActualBoard().clone();       //Create a Clone of the GameBoard
        new_state.executeMove(new_move);            //Execute the Move on the clone

        MoveNode next_MoveNode = new MoveNode(new_move, this.getCurrentNode(), new_state); //Create a new Child Node with the information of the move and the board where the move was already executed
        next_MoveNode.setDepth(this.getCurrentNode().getDepth()+1);     //Set The Depth of the ChildNode+1 from Parent
        this.setCurrentNode(next_MoveNode); 
        return true;        //return true, because Calculation of ChildNode was successful
    }

    @Override
    public boolean treeCreatorAction() throws Exception {
            if (this.getCurrentNode() == null) { 
                if (this.next_calculations.isEmpty()) { return false; }     //There are no next calculations, so the Tree Creation is over
                this.setCurrentNode(this.next_calculations.remove());           //Else do next calculations
            }

            for (int i = -1; i < this.getCurrentNode().getActualBoard().getDepositedCards().size(); i++) {
                for (int j = -1; j < this.getCurrentNode().getActualBoard().getCurrentPlayerCards().getCards().size(); j++) {
                    if (!calcNextNode(i, j)) { continue; }          //go through all possible moves actually 
                    next_calculations.add(this.getCurrentNode());       //Add node if and only if they bring high rating
                    this.setCurrentNode(this.getCurrentNode().getPrevNode());           //come back to parent to decide for next move
                }
            }
        this.max = -100000;
        this.setCurrentNode(null);
        return true;
    }

    /**
     * Helps for efficient Tree Building.
     * This method checks firstly if the Move is legal to do by checking whether the game is over or max depth is reached.
     * Then it checks, if the move brings more points than already calculated childre.
     * @param cardNumPut
     * @param cardNumTake
     * @return null, if illegal or Move brings less points than already calculated children of the actual_node
     * @return Move, if it is necessary
     */
    private final Move checkMove(int cardNumPut, int cardNumTake) {
        if (this.getCurrentNode().getDepth()+1 > this.getMaxDepth() || this.getCurrentNode().getActualBoard().getRound() > 30 || this.getCurrentNode().getActualBoard().getDepositedCards().size() > this.getCurrentNode().getActualBoard().getConfiguration().getNumDepositionAreaSlots()) { return null; } //Illegal Move
        Card to_take, to_put;

        //First Case -> Card from DrawPile Stack
        if (cardNumTake < 0) {
            if (this.getCurrentNode().getActualBoard().getDrawPileCards().isEmpty()) { return null; }       //Illegal Move
            to_take = this.getCurrentNode().getActualBoard().getDrawPileCards().get(this.getCurrentNode().getActualBoard().getDrawPileCards().size() - 1); 
        }

        //Second Case -> Card from Deposited Area
        else { to_take = this.getCurrentNode().getActualBoard().getDepositedCards().get(cardNumTake); }
        

        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //First Case -> The taken card has to be put
        if (cardNumPut < 0) {
            to_put = to_take;
        }
        

        //Second Case -> Own card has to be put
        else {
            to_put = this.getCurrentNode().getActualBoard().getCurrentPlayerCards().getCards().get(cardNumPut);
        }

        
        int points = calculateWinPoints(to_take, to_put, this.getCurrentNode().getActualBoard());
        if (points <= this.max) { return null; }        //If the moves does not givbe more points than already calculated moves, return null
        this.max = points;          //else make new maximum
        return new Move(to_take, to_put);       //return the next move
    }


    /**
     * Helping method, just to calculate the points of the Player in the Tree nif he hypotetically makes the move
     * @param to_take
     * @param to_put
     * @param gameboard
     * @return Score of the CurrentPlayer of the GameBoard after hypotetically MoveExecution
     */
    private final int calculateWinPoints(Card to_take, Card to_put, GameBoard gameboard) {
        return gameboard.getCurrentPlayerCards().getAddRemoveScore(to_take, to_put);
    }


    //This methods are not used by this Thread
    @Override
    public MoveNode getBestOfActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBest'");
    }

    @Override
    public boolean treeSearcherAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sequenceThreadAction'");
    }

    @Override
    public boolean goDeeper() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'goDeeper'");
    }
}
