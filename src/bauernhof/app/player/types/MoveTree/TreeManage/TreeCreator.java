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
        this.setCurrentNode(getTree().getRootNode());       //Set current Node to the Root NOde of created tree
        treeCreatorAction();
         while (!this.next_calculations.isEmpty()) { 
            treeCreatorAction();
        }

    }


    @Override
    public boolean calcNextNode(int cardNumTake, int cardNumPut) throws Exception {
        Move new_move = checkMove(cardNumPut, cardNumTake);         //check if it would make sense to calculate a MoveNode, which will contain the Move
        if (new_move == null) { return false; }             //if not, then return false
        GameBoard new_state = this.getCurrentNode().getActualBoard().clone();
        new_state.executeMove(new_move);

        MoveNode next_MoveNode = new MoveNode(new_move, this.getCurrentNode(), new_state);
        next_MoveNode.setDepth(this.getCurrentNode().getDepth()+1);
        this.setCurrentNode(next_MoveNode);
        return true;
    }

    @Override
    public boolean treeCreatorAction() throws Exception {
            if (this.getCurrentNode() == null) {
                if (this.next_calculations.isEmpty()) { return false; }
                this.setCurrentNode(this.next_calculations.remove());
            }

            for (int i = -1; i < this.getCurrentNode().getActualBoard().getDepositedCards().size(); i++) {
                for (int j = -1; j < this.getCurrentNode().getActualBoard().getCurrentPlayerCards().getCards().size(); j++) {
                    if (!calcNextNode(i, j)) { continue; }
                    next_calculations.add(this.getCurrentNode());
                    this.setCurrentNode(this.getCurrentNode().getPrevNode());
                }
            }
        this.max = -100000;
        this.setCurrentNode(null);
        return true;
    }

    /**
     * Helps for efficient Tree Building -> 
     * @param cardNumPut
     * @param cardNumTake
     * @return
     */
    private final Move checkMove(int cardNumPut, int cardNumTake) {
        if (this.getCurrentNode().getDepth()+1 > this.getMaxDepth() || this.getCurrentNode().getActualBoard().getRound() > 30) { return null; }
        Card to_take, to_put;
        if (cardNumTake < 0) {
            if (this.getCurrentNode().getActualBoard().getDrawPileCards().isEmpty()) { return null; }
            to_take = this.getCurrentNode().getActualBoard().getDrawPileCards().get(this.getCurrentNode().getActualBoard().getDrawPileCards().size() - 1);
        }

        else {
            if (this.getCurrentNode().getActualBoard().getDepositedCards().size() > this.getCurrentNode().getActualBoard().getConfiguration().getNumDepositionAreaSlots()) { return null; }
            to_take = this.getCurrentNode().getActualBoard().getDepositedCards().get(cardNumTake);
        }

        if (cardNumPut < 0) {
            to_put = to_take;
        }

        else {
            to_put = this.getCurrentNode().getActualBoard().getCurrentPlayerCards().getCards().get(cardNumPut);
        }

        
        Move new_move = new Move(to_take, to_put);
        try {
            if (!this.getCurrentNode().getActualBoard().clone().executeMove(new_move)) { return null;}
        } catch (Exception e) {
            System.err.println("Can't do a Move");
        }
        if (calculateWinPoints(new_move, this.getCurrentNode().getActualBoard()) <= this.max) { return null; }
        this.max = calculateWinPoints(new_move, this.getCurrentNode().getActualBoard());
        return new_move;
    }

    public int calculateWinPoints(Move move, GameBoard gameboard) {
        return gameboard.getCurrentPlayerCards().getAddRemoveScore(move.getTaken(), move.getDeposited());
    }


    //This methods are not used by this Thread
    @Override
    public MoveNode getBestOfActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBest'");
    }

    @Override
    public void TreeSearcherAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sequenceThreadAction'");
    }

    @Override
    public boolean goDeeper() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'goDeeper'");
    }
}
