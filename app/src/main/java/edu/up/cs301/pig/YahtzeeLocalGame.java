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
        //TODO  You will implement this method

        //hold actionjhghjuh
        if (action instanceof YahtzeeKeep) {

            if (masterGameState.getTurn() == 0) {
                //masterGameState.setP0Score(masterGameState.getP0Score() + masterGameState.getRunningTotal());
            } else if (masterGameState.getTurn() == 1) {
                //masterGameState.setP1Score(masterGameState.getP1Score() + masterGameState.getRunningTotal());
            }


            return true;

            //roll action
        } else if (action instanceof YahtzeeRoll) {
            int rand = (int)(Math.random() * 6);
            if (rand == 1) {
                //masterGameState.setRunningTotal(0);
                if (masterGameState.getTurn() == 0) {
                    masterGameState.setTurn(1);
                } else {
                    masterGameState.setTurn(0);
                }
            } else {
                //masterGameState.setRunningTotal(masterGameState.getRunningTotal() + rand);
            }

            return true;
        }

        if (action instanceof YahtzeeScore){

        }
            return false;


    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
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
