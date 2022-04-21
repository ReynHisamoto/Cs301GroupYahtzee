package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

public class YahtzeeSmartAI extends GameComputerPlayer {

    public YahtzeeSmartAI(String name) {
        super(name);
    }


    //Reference to Local game for use of Helper Methods
    YahtzeeLocalGame yahtzeeLocalGame;
    //The master game state
    YahtzeeGameState masterGameState;
    //An array of the dice values in play.
    Dice[] diceArr;
    //An array that counts how many of each value of dice there are.
    int[] numDiceAI;
    //The amount of most common dice from the numDiceAI array.
    int amountMostComm;
    //The current turn's roll number
    int currentRollNum;
    //The value of the most common dice
    int mostCommonValue = 0;
    //Player ID
    int ID = this.playerNum;
    //Turn number
    int turn;
    // Roll number
    int rollNum;

    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof YahtzeeGameState) {
            masterGameState = (YahtzeeGameState) info;
        }
        sleep(500);

        //instantiates the instance variables.
        diceArr = masterGameState.getDiceArray();
        numDiceAI = ((YahtzeeLocalGame) game).totalDice(masterGameState.getDiceArray());
        amountMostComm = ((YahtzeeLocalGame) game).maxNumDice(numDiceAI);
        currentRollNum = masterGameState.getRollNum();
        turn = masterGameState.getTurn();
        rollNum = masterGameState.getRollNum();

        //Instantiates mostCommonValue
        for (int i = 0; i < numDiceAI.length; i++){
            if(numDiceAI[i] > mostCommonValue){
                mostCommonValue = i;
            }
        }

        if (!(info instanceof NotYourTurnInfo)) {
            int rand = (int) (Math.random() * 14);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Step 1: If lower scorecard has yahtzee, full house, or large straight, select that.
            //Checks yahtzee
            if(((YahtzeeLocalGame) game).Yahtzee(numDiceAI) && !aIChosen(this.playerNum,14)){
                YahtzeeScore action = new YahtzeeScore(this, 15, this.playerNum);
                game.sendAction(action);
            }
            //checks large straight
            else if((((YahtzeeLocalGame) game).LargeStraight(numDiceAI) && !aIChosen(this.playerNum,12))){
                YahtzeeScore action = new YahtzeeScore(this, 12, this.playerNum);
                game.sendAction(action);
            }
            //TODO Create if statement to detect full house

            //skipping a few steps to write this hard "if" statement.
//            5.	If at least three copies of a number, and it’s top score hasn’t been selected :
//            a)	If value larger than one but less than 4:
//                  1.	Roll rest and aim for Yahtzee.
//            b)	If value equal to or larger than 4:
//                  1.	Roll other dice then score upper score
//                  2.	Score chance if upper scores are filled.


            //TODO don't erase any of this. How do I go about selecting dice that I want, in detail with arrays. Ask Augustine for help.
            else if(amountMostComm >= 3){
                //a)
                if(mostCommonValue >= 3){
                    if(rollNum < 3 && !aIChosen(this.playerNum,mostCommonValue-1)){
                        YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                        game.sendAction(action);
                    }
                    else if (rollNum == 3 && !aIChosen(this.playerNum,mostCommonValue-1)){
                        YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                        game.sendAction(action);
                    }

                }
            }


            //Santiago's code:
            if (ones()){
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
            }
            else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }

            if (twos()){
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
            }
            else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }

            if (threes()){
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
            }
            else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }

            if (fours()){
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
            }
            else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }

            if (fives()){
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
            }
            else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }

            if (sixes()){
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
            }
            else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }



            //Leftover code from dumb AI - basically just allows it to run.
            if (rand != 6 && rand != 7) {
                YahtzeeScore action = new YahtzeeScore(this, rand, playerNum);
                game.sendAction(action);
            } else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }
        }//receiveInfo
    }

    /**
     * These are a series of helper methods that scan to see what values the dice array holds. Checks
     * to see how many sixes there are, if there's a 3 of a kind, if there's a full house, etc.
     */
    //Detects if there is any kind of 3 of kind
    private boolean threeOfKind() {
        if (amountMostComm >= 3) {
            return true;
        } else {
            return false;
        }
    }

    private boolean fourOfKind() {
        if (amountMostComm >= 4){
            return true;
        }
        else{
            return false;
        }
    }



    //detects if a given array spot has been chosen
    private boolean aIChosen(int player, int row){
        return masterGameState.getChosen(player,row);
    }


    //These methods check if there is at least one of a given dice, and if so return true. Santiago's methods
    private boolean ones(){
        int val = 0;
        for (int i = 0; i <= 4; i++){
            if (diceArr[i].getVal() == 1){val++;}
        }
        if (val >= 1){ return true;}
        else {return false;}}

    private boolean twos(){
        int val = 0;
        for (int i = 0; i <= 4; i++){
            if (diceArr[i].getVal() == 2){
                val++;
            }
        }
        if (val >= 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean threes(){
        int val = 0;
        for (int i = 0; i <= 4; i++){
            if (diceArr[i].getVal() == 3){
                val++;
            }
        }
        if (val >= 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean fours(){
        int val = 0;
        for (int i = 0; i <= 4; i++){
            if (diceArr[i].getVal() == 4){
                val++;
            }
        }
        if (val >= 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean fives(){
        int val = 0;
        for (int i = 0; i <= 4; i++){
            if (diceArr[i].getVal() == 5){
                val++;
            }
        }
        if (val >= 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean sixes(){
        int val = 0;
        for (int i = 0; i <= 4; i++){
            if (diceArr[i].getVal() == 6){
                val++;
            }
        }
        if (val >= 1){
            return true;
        } else {
            return false;
        }
    }


    //Methods imported from yahtzeelocalgame.
    // ((YahtzeeLocalgame) game).LargeStraight() - checks if there is large straight.
    // ((YahtzeeLocalgame) game).SmallStraight() - checks if there is small straight.
    // ((YahtzeeLocalgame) game).Yahtzee() - checks if there is Yahtzee.

}




