package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;


/**
 * class yahtzeeLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl, Augustine P, Reyn H, James L, Santiago F
 *
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
                //masterGameState.setP0Score(masterGameState.getP0Score() + masterGameState.getRunningTotal());
            } else if (masterGameState.getTurn() == 1) {
                //masterGameState.setP1Score(masterGameState.getP1Score() + masterGameState.getRunningTotal());
            }


            return true;

            //roll action
        } else if (action instanceof YahtzeeRoll) {
            //todo figure out how to get current dice, how to check if dice is in keep
            for (int i = 0; i < 5; i++){
                if (masterGameState.getDices(i) == /*find out if it is selected or not*/  )
                int rand = (int)(Math.random() * 6);
                masterGameState.setDices(ind /*(Index not found just yet, building scaffolding for when we can use it)*/, rand);
            }
        }


        if (action instanceof YahtzeeScore){
            //???
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

        if (masterGameState.getRound() > 14) {
            return "game over";
        }

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
