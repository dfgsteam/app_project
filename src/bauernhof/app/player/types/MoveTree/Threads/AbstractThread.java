package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveNode;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.Move;

/**
 * This abstract class was written by
 * @author Viktor Tevosyan
 * It contains all variables which are used by both threads
 */
public abstract class AbstractThread extends Thread {

    
    private int number_of_toput_card;
    private int number_of_totake_card;
    private MoveTree move_tree;

    public abstract MoveNode calculateNextNode();
    

}