package bauernhof.app.player.types;

import bauernhof.app.player.*;
import bauernhof.app.system.GameBoard;
import bauernhof.preset.Move;
import bauernhof.preset.Settings;
import bauernhof.preset.card.Card;

import java.util.Random;

/**
 * This class was created by
 * @author Viktor Tevosyan
 * @date 26.06.2023
 * This class represents a Random_AI, which extends from AbstractGamePlayer, because it is a GamePlayer.
 * It implements the AIHeader interface, in order to follow the structure of an Artifical Intelligence, which is written in the interface.
 * The Random_AI has the role of a very bad player, who has no idea of the game it plays and makes random moves:
 * - Does not understand, how the board looks like
 * - Does not understand the moves
 * - Does not consider the players
 * - Can't determine, which move is good or bad
 * - Plays fortune game
 */
public class Random_AI extends AbstractGamePlayer implements AIHeader{

    /**
     * Parametrized constructor, which creates a Random_AI with given settings playercards at the begining of the game and the given GameBoard
     * @param settings
     * @param playercards
     * @param gamesystem
     */
    public Random_AI(final Settings settings, final PlayerCards playercards, final GameBoard gamesystem) {
        super(settings, playercards, gamesystem);
    }

    @Override
    public Move request() {
        Card to_take = null;            //References for the cards, firstly initialised
        Card to_deposit = null;

        if (gameBoard.getDepositedCards().isEmpty() || Math.random() < 0.5) {   //if the deposit area is empty or 50% probability -> take from drawpile_stack
            if(cardFromStack() != null) to_take = cardFromStack();     //if the drawpile_stack not empty, take fromn draw_pile stack
            else to_take = cardFromDeposit();       
        }

        else { to_take = cardFromDeposit(); }        //else we know, that the deposited area is not empty --> take from there
            
        

        //----------------------------------
        if (Math.random() < 1.0/ (configuration.getNumCardsPerPlayerHand()+1)) { to_deposit = to_take;  }             //by the probability of 1/owncards+1 -> deposited card is the taken card
            
        
        else { to_deposit = removeFromOwn(to_take); }     //else a card from own

        return new Move(to_take, to_deposit);       //return calculated move
    }


    /**
     * Random Integer Generator for the own card-index to put into deposit
     * @return Random Integer as number of Card in the own hand
     */
    private final int ownCardNumber() {
        Random random = new Random();
        return random.nextInt(configuration.getNumCardsPerPlayerHand());
    }

    /**
     * Random Integer Generator for index of random card from deposit to take
     * @return Random Integer as a number of Card from deposit
     */
    private final int depositRandomNumber() {
        Random random = new Random();
        return (random.nextInt(gameBoard.getDepositedCards().size()));
    }

    @Override
    /**
     * @return Random card from Deposit
     */
    public Card cardFromDeposit() {
       int deposit_random_index = depositRandomNumber();        //generate the number of the card in the deposit area
       return gameBoard.getDepositedCards().get(deposit_random_index);      //return the card by its number of the deposited area
    }

    @Override
    /**
     * @return Card from the DrawPileStack
     */
    public Card cardFromStack() {
        if (gameBoard.getDrawPileCards().isEmpty())     //just return no reference, if the drawpile is empty
            return null;
        return gameBoard.getDrawPileCards().get(gameBoard.getDrawPileCards().size() - 1);   //else if we want the "last pushed" element from tzhe arraylist
    }

    @Override
    /**
     * @return Random Card from own Hand
     */
    public Card removeFromOwn(Card to_take) {
        int own_random_index = ownCardNumber();     //generate the number of own card
        return playercards.getCards().get(own_random_index);        //return the card by its number
    }
    
}