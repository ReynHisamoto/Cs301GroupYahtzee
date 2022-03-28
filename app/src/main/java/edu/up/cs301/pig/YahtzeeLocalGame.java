package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;


/**
 * class YahtzeeLocalGame controls the play of the game
 *  *
 *  * @author Augustine Pham James Lulay Reyn Hisamoto Santiago Franco
 *  * @version February 2022
 *  */
/**
 External Citation
 Date: 23 September 2022
 Problem: forgot how arraylists worked
 Resource:
 https://www.geeksforgeeks.org/arraylist-in-java/
 Solution: I used the example code from this post.
 */
/**
 External Citation
 Date: 23 September 2022
 Problem: forgot how for each loops worked
 Resource:
 https://www.geeksforgeeks.org/for-each-loop-in-java/
 Solution: I used the example code from this post.
 */
public class YahtzeeLocalGame extends LocalGame {

    private YahtzeeGameState masterGameState;

    /**
     * This ctor creates a new game state
     */
    public YahtzeeLocalGame() {
        masterGameState = new YahtzeeGameState(2);
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMakeAction(int playerIdx) {
        return (playerIdx == masterGameState.getTurn());
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        /**
         Essentially, this "if" statement detects if the action is a YahtzeeKeep action. If it is, it detects
         who made the action, then it will tell the YahtzeeGameState to "keep" the die that has had
         the action performed on it.
         **/
        if (action instanceof YahtzeeKeep && canMakeAction(((YahtzeeKeep)action).getIdx())) {
           if(!canMakeAction(((YahtzeeKeep) action).getIdx()) || masterGameState.getSelectedDice().size() > 3){
               return false;
           }else if (!masterGameState.getDice(((YahtzeeKeep) action).getSelected()).isKeep()) {
               masterGameState.getSelectedDice().add(masterGameState.getDice(((YahtzeeKeep) action).getSelected()));
               masterGameState.getDice(((YahtzeeKeep) action).getSelected()).setKeep(true);
               return true;
           }else{
                masterGameState.getSelectedDice().remove(masterGameState.getDice(((YahtzeeKeep) action).getSelected()));
                masterGameState.getDice(((YahtzeeKeep) action).getSelected()).setKeep(false);
                return true;
               }



            /**
             * Sets selected dice value to random int 1-6
             */
        } else if (action instanceof YahtzeeRoll && canMakeAction(((YahtzeeRoll) action).getIdx()) && masterGameState.getRollNum() <= 3) {
            masterGameState.setRollNum(masterGameState.getRollNum() + 1);
            int rand;
            if(canMakeAction(((YahtzeeRoll) action).getIdx())){
            for (Dice dice : masterGameState.getSelectedDice()){
                rand = (int)(Math.random() * 6 + 1);
                dice.setVal(rand);
            }
            return true;
        }
        }

        /**
         * checks where the player has clicked and adds to the scoreboard accordingly
         */
        if (action instanceof YahtzeeScore && canMakeAction(((YahtzeeScore) action).getIdx()) && masterGameState.getScores(((YahtzeeScore) action).getIdx())[((YahtzeeScore) action).getRow()] == 0){
                int score = 0;
                int[] numDice = totalDice(masterGameState.getDiceArray());
                int mostCommon = maxNumDice(numDice);
                int secondCommon = secondNumDice(numDice,mostCommon);
                boolean fullTop = false;
                // if player selects aces twos etc. get score then add to score sheet
                if(((YahtzeeScore) action).getRow() <= 5){
                    for (Dice dice : masterGameState.getDiceArray()){
                        if(dice.getVal() == ((YahtzeeScore) action).getRow() + 1 ){
                            score += dice.getVal();
                        }
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                }
                // if player selects 3 of a kind then get the most common dice value and multiply by three
                else if(((YahtzeeScore) action).getRow() == 8 && numDice[mostCommon] >= 3){

                    score += mostCommon * 3;
                    for(int i =0; i < masterGameState.getDiceArray().length; i++){
                        if(masterGameState.getDiceArray()[i].getVal() != mostCommon){
                            score += masterGameState.getDiceArray()[i].getVal();
                        }
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                }
                // if player selects 4 of a kind then get the most common dice value and multiply by four
                else if(((YahtzeeScore) action).getRow() == 9 && numDice[mostCommon] >= 4){
                    score += mostCommon * 4;
                    for(int i =0; i < masterGameState.getDiceArray().length; i++){
                        if(masterGameState.getDiceArray()[i].getVal() != mostCommon){
                            score += masterGameState.getDiceArray()[i].getVal();
                        }
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                }
                // if player selects full house then checks for full house by looking if there are three of one type and two of another if true then set score to 25
                else if(((YahtzeeScore) action).getRow() == 10 && numDice[mostCommon] == 3 && numDice[secondCommon] == 2){
                    score += 25;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                    // if player selects small straight check if true if so then give player 30 pts on score card
                }else if(((YahtzeeScore) action).getRow() == 11 && SmallStraight(numDice)){
                    score += 30;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                    // if player selects large straight and true then give player 40 points
                }else if(((YahtzeeScore) action).getRow() == 12 && LargeStraight(numDice)){
                    score += 40;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                    // if player chooses yahtzee and if true give player 50 points
                }else if(((YahtzeeScore) action).getRow() == 13 && Yahtzee(numDice)) {
                    if(masterGameState.getScores(((YahtzeeScore) action).getIdx())[13] == 0){
                    score += 50;
                    } else if(masterGameState.getScores(((YahtzeeScore) action).getIdx())[13] > 0){
                        score += 100;
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);


                    // if player chooses chance sum dice and add to score
                }else if(((YahtzeeScore) action).getRow() == 14 ){
                    for(Dice dice : masterGameState.getDiceArray()){
                        score += dice.getVal();
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),((YahtzeeScore) action).getRow(),score);
                }

                score = 0;

                for(int i = 0; i < 6; i++){
                    score += masterGameState.getScores(((YahtzeeScore) action).getIdx())[i];
                    if(score > 63){
                        fullTop = true;
                    }
                }

                masterGameState.setScores(((YahtzeeScore) action).getIdx(),6,score);
                score = 0;

                for(int i =0; i < masterGameState.getScores(((YahtzeeScore) action).getIdx()).length;i++){
                    score += masterGameState.getScores(((YahtzeeScore) action).getIdx())[i];
                }

                if(fullTop){
                    score += 35;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(),7,35);
                }

                masterGameState.setScores(((YahtzeeScore) action).getIdx(),15,score);
                masterGameState.setRound(masterGameState.getRound() + 1);
                YahtzeeRoll endRoll = new YahtzeeRoll(action.getPlayer(),((YahtzeeScore) action).getIdx());
                makeMove(endRoll);
                masterGameState.setRollNum(1);

                if(masterGameState.getTurn() >= players.length){
                    masterGameState.setTurn(0);
                }else{
                    masterGameState.setTurn(masterGameState.getTurn() + 1);
                }

                return true;
            }

        // not valid move
            return false;
        }



    //makeMove

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
        int max =0;
        for(int i =0; i < players.length; i++){
            if(max < masterGameState.getScores(i)[15]){
                max = i;
            }
        }

        if (masterGameState.getRound() > 13 * players.length) {
            return "The winner is " + playerNames[max] + " !";
        }

        return null;

    }
    /*
    checks the most common dice given an array of common dice
     */
    protected int maxNumDice(int[] potValue){
        int maxNum = 0;
        for(int numDice : potValue){
            if(maxNum < numDice){
                maxNum = numDice;
            }
        }
        return maxNum;
    }
    /*
    checks for the second most common dice given an array of common dice
     */
    protected int secondNumDice(int[] potValue, int currentMax){
        int maxNum = 0;
        for(int numDice : potValue){
            if(maxNum < numDice && numDice < currentMax){
                maxNum = numDice;
            }
        }
        return maxNum;
    }


    /*
    creates an array of dice in common
     */
    protected int[] totalDice(Dice[] dice){
        int[] potValue = new int[6];
        for(int i =0; i < potValue.length; i++)
            for(Dice dices : dice){
                if (dices.getVal() == i){
                    potValue[i]++;
                }
            }
        return potValue;
    }

    /*
    checks whether or not the given hand of dice is a small straight
     */
    protected boolean SmallStraight(int[] potValue){
        int numInstance = 0;
        for(int val : potValue){
            if(val >= 1) {
                numInstance++;
            }else{
                numInstance = 0;
            }
        }
        return( numInstance >= 4);
        }


    /*
    checks whether or not the given hand of dice is a large straight
     */
    protected boolean LargeStraight(int[] potValue){
        int numInstance = 0;
        for(int val : potValue){
            if(val == 1 ){
                numInstance++;
            }else{
                numInstance = 0;
            }
        }
        return(numInstance >= 5);
    }

    /*
    checks whether or not the given hand of dice is a yahtzee
     */
    protected boolean Yahtzee(int[] potValue){
        for(int val: potValue){
            if(val == 5){
                return true;
            }
        }
        return false;
    }





}// class PigLocalGame
