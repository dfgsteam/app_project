package bauernhof.app.player.types.MoveTree.Threads;

import bauernhof.app.launcher.GameBoardState;
import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.types.MoveTree.MoveTree;
import bauernhof.preset.Player;

public class DeepCalcThread extends Thread implements ThreadHandler  {
    
    private MoveTree tree;

    public DeepCalcThread(GameBoardState state, AbstractGamePlayer actual_player) {
        this.tree = new MoveTree(state, actual_player);
    }

    @Override
    public void calcNode(int cardNumTake, int cardNumPut) {
        
    }

    @Override
    public void setTree(MoveTree tree) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTree'");
    }

    @Override
    public MoveTree getTree() {
        return this.tree;
    }

    //-------------------------------

    @Override
    public void run() {

    }

    
    
}
