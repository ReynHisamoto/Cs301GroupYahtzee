package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;


/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class YahtzeeLocalGame extends LocalGame {

    private YahtzeeGameState masterGameState;

    /**
     * This ctor creates a new game state
     */
    public YahtzeeLocalGame() {
        this.masterGameState = new YahtzeeGameState(players.length);
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMakeAction(int playerIdx) {

        if (playerIdx == masterGameState.getTurn()) {
            return true;
        } else {

            return false;
        }

    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        /**
        This action is proving to be difficult. It is an action that is being commited against a
        "dice" object. However, we don't know how to code that yet, so we will get on that asap.
         Essentially, this "if" statement detects if the action is a YahtzeeKeep action. If it is, it detects
         who made the action, then it will tell the YahtzeeGameState to "keep" the die that has had
         the action performed on it.
         **/
        if (action instanceof YahtzeeKeep) {
            if (masterGameState.getTurn() == 0) {
            } else if (masterGameState.getTurn() == 1) {
            }
            return true;

            /**
             * This is the same deal as the yahtzeeKeep method. It applies to a die, and we don't know
             * how to apply a method to a die, so this is a skeleton method for the time being.
             */
        } else if (action instanceof YahtzeeRoll) {
            int rand = (int)(Math.random() * 6);
            if (rand == 1) {
                if (masterGameState.getTurn() == 0) {
                    masterGameState.setTurn(1);
                } else {
                    masterGameState.setTurn(0);
                }
            } else {
            }

            return true;
        }

        /**
         * We are also stuck on this.
         * The goal with this method is to "select" the score you want to keep in yahtzee.
         * We are debating over whether the scorecard should be an array of
         * int values in the yahtzeeGameState class, or if the scorecard should be an object itself
         * with possible scoreBox objects built into it. Regardless, we're stuck on this one.
         */
        if (action instanceof YahtzeeScore){
        }
            return false;


    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        YahtzeeGameState updatedGameState = new YahtzeeGameState(masterGameState);
        p.sendInfo(updatedGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method

        if (masterGameState.getRound() > 14) {
            return "game over";
        }


        /**
        if (masterGameState.getP0Score() > 50) {
            return  "player 0 wins, Score: " + masterGameState.getP0Score();
        } else if (masterGameState.getP1Score() > 50) {
            return "player 1 wins, Score: " + masterGameState.getP1Score();
        } else {
         **/
            return null;

    }

    //todo create yahtzee methods
    protected boolean selectKeepers() {
        return false;
    }

    protected boolean selectScoreBox() {
        return false;
    }

}// class PigLocalGame
