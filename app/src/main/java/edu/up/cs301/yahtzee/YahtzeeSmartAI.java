package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;
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
    //The value of the second most common dice
    int secondMostCommon = 0;
    //Player ID
    int ID = this.playerNum;
    //Turn number
    int turn;
    // Roll number
    int rollNum;

    @Override
    protected void receiveInfo(GameInfo info) {

        if ((info instanceof YahtzeeGameState) ) {
            masterGameState = (YahtzeeGameState) info;
            if(masterGameState.getTurn() != playerNum){
                return;
            }
        }

        //instantiates the instance variables.
        yahtzeeLocalGame = (YahtzeeLocalGame) game;
        diceArr = masterGameState.getDiceArray();
        numDiceAI = ((YahtzeeLocalGame) game).totalDice(masterGameState.getDiceArray());
        amountMostComm = ((YahtzeeLocalGame) game).maxNumDice(numDiceAI);
        currentRollNum = masterGameState.getRollNum();
        turn = masterGameState.getTurn();
        rollNum = masterGameState.getRollNum();
        secondMostCommon = ((YahtzeeLocalGame) game).secondNumDice(numDiceAI,amountMostComm);

        //Instantiates mostCommonValue
        for (int i = 0; i < numDiceAI.length; i++) {
            if (numDiceAI[i] == amountMostComm) {
                mostCommonValue = i;
            }
        }


        if (!(info instanceof NotYourTurnInfo)) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Step 1: If lower scorecard has yahtzee, full house, or large straight, select that.
            //Checks yahtzee
            if (((YahtzeeLocalGame) game).Yahtzee(numDiceAI) && !aIChosen(this.playerNum, 14)) {
                YahtzeeScore action = new YahtzeeScore(this, 14, this.playerNum);
                game.sendAction(action);
                return;
            }
            //checks large straight
            else if ((((YahtzeeLocalGame) game).LargeStraight(numDiceAI) && !aIChosen(this.playerNum, 12))) {
                YahtzeeScore action = new YahtzeeScore(this, 12, this.playerNum);
                game.sendAction(action);
                return;
            }
            //checks small straight
            else if ((((YahtzeeLocalGame) game).SmallStraight(numDiceAI)) && !aIChosen(this.playerNum, 11)) {
                YahtzeeScore action = new YahtzeeScore(this, 11, this.playerNum);
                game.sendAction(action);
                return;
            }

            //checks full house
            else if (fullHouse(numDiceAI) && !aIChosen(this.playerNum, 10)) {
                YahtzeeScore action = new YahtzeeScore(this, 10, this.playerNum);
                game.sendAction(action);
                return;
            //checks four of a kind
            } else if (fourOfKind() && mostCommonValue > 3 && !aIChosen(this.playerNum, 9)) {
                YahtzeeScore action = new YahtzeeScore(this, 9, this.playerNum);
                game.sendAction(action);
                return;
            //checks four of a kind
            } else if (threeOfKind() && mostCommonValue > 3 && !aIChosen(this.playerNum, 8)) {
                YahtzeeScore action = new YahtzeeScore(this, 8, this.playerNum);
                game.sendAction(action);
                return;
            }
            //Step 2
//            If at least three copies of a number, and it???s top score hasn???t been selected :
//            a)	If value larger than one but less than 4:
//                  1.	Roll rest and aim for Yahtzee.
//            b)	If value equal to or larger than 4:
//                  1.	Roll other dice then score upper score
//                  2.	Score chance if upper scores are filled.
             if (amountMostComm >= 3 && currentRollNum < 3) {
                for (int i = 0; i < diceArr.length; i++) {
                    Dice dice = diceArr[i];
                    if (dice.getVal() != mostCommonValue && !dice.isKeep()) {
                        YahtzeeSelect action = new YahtzeeSelect(this, this.playerNum, i);
                        game.sendAction(action);
                        return;
                    }
                }
                YahtzeeRoll action = new YahtzeeRoll(this, this.playerNum);
                game.sendAction(action);
                return;

             //Step 3: if 2 copies of a number, and it???s top hasn???t been selected, roll other three.

             } else if (amountMostComm <= 2 && currentRollNum < 3) {
                for (int i = 0; i < diceArr.length; i++) {
                    Dice dice = diceArr[i];
                    if (dice.getVal() != mostCommonValue && !dice.isKeep()) {
                        YahtzeeSelect action = new YahtzeeSelect(this, this.playerNum, i);
                        game.sendAction(action);
                        return;
                    }
                }
                YahtzeeRoll action = new YahtzeeRoll(this, this.playerNum);
                game.sendAction(action);
                return;
            }

            /**
             *This is a series of if statements that, if there exists nothing else decent for the
             * AI to do, will fill in the highest most box on the scorecard. This is actually a yahtzee
             * strategy: you don't want to throw away your 4's,5's,or 6's because you want high scores on those.
             * The "chance" is also best saved for later in the game when there is less left on the board.
              */
            if (ones() && !masterGameState.getChosen(playerNum,0)) {
                YahtzeeScore action = new YahtzeeScore(this, 0, playerNum);
                game.sendAction(action);
                return;
            } else if (twos() && !masterGameState.getChosen(playerNum,1)) {
                YahtzeeScore action = new YahtzeeScore(this, 1, playerNum);
                game.sendAction(action);
            } else if (threes() && !masterGameState.getChosen(playerNum,2)) {
                YahtzeeScore action = new YahtzeeScore(this, 2, playerNum);
                game.sendAction(action);
                return;
            } else if (fours() && !masterGameState.getChosen(playerNum,3)) {
                YahtzeeScore action = new YahtzeeScore(this, 3, playerNum);
                game.sendAction(action);
                return;
            } else if (fives() && !masterGameState.getChosen(playerNum,4)) {
                YahtzeeScore action = new YahtzeeScore(this, 4, playerNum);
                game.sendAction(action);
                return;
            } else if (sixes() && !masterGameState.getChosen(playerNum,5)) {
                YahtzeeScore action = new YahtzeeScore(this, 5, playerNum);
                game.sendAction(action);
                return;
            }else if( !masterGameState.getChosen(playerNum,13)){
                YahtzeeScore action = new YahtzeeScore(this,13,playerNum);
                game.sendAction(action);
                return;
            }
            for(int i =0; i < 16; i++){
                if(!masterGameState.getChosen(playerNum,i)){
                    YahtzeeScore action = new YahtzeeScore(this, i, playerNum);
                    game.sendAction(action);
                }
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
        if (amountMostComm >= 4) {
            return true;
        } else {
            return false;
        }
    }


    //detects if a given array spot has been chosen
    private boolean aIChosen(int player, int row) {
        return masterGameState.getChosen(player, row);
    }


    //These methods check if there is at least one of a given dice, and if so return true.
    private boolean ones() {
        int val = 0;
        for (int i = 0; i <= 4; i++) {
            if (diceArr[i].getVal() == 1) {
                val++;
            }
        }
        return (val >= 1);
    }

    private boolean twos() {
        int val = 0;
        for (int i = 0; i <= 4; i++) {
            if (diceArr[i].getVal() == 2) {
                val++;
            }
        }
        return (val >= 1);
    }

    private boolean threes() {
        int val = 0;
        for (int i = 0; i <= 4; i++) {
            if (diceArr[i].getVal() == 3) {
                val++;
            }
        }
        return (val >= 1);
    }

    private boolean fours() {
        int val = 0;
        for (int i = 0; i <= 4; i++) {
            if (diceArr[i].getVal() == 4) {
                val++;
            }
        }
        return (val >= 1);
    }

    private boolean fives() {
        int val = 0;
        for (int i = 0; i <= 4; i++) {
            if (diceArr[i].getVal() == 5) {
                val++;
            }
        }
        return (val >= 1);
    }

    private boolean sixes() {
        int val = 0;
        for (int i = 0; i <= 4; i++) {
            if (diceArr[i].getVal() == 6) {
                val++;
            }
        }
        return (val >= 1);
    }
    //Detects if full house.
    private boolean fullHouse(int[] numDiceAI) {
        if (amountMostComm == 3 && secondMostCommon == 2) {
            return true;
        } else {
            return false;
        }
    }
}




