package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

public class YahtzeeSmartAI extends GameComputerPlayer {

    public YahtzeeSmartAI(String name) {
        super(name);
    }


    //The master game state
    YahtzeeGameState masterGameState;
    //An array of the dice values in play.
    Dice[] diceArr;
    //An array that counts how many of each value of dice there are.
    int[] numDiceAI;
    //The amount of most common dice from the numDiceAI array.
    int mostCommonAI;
    //The amount of the second most common dice
    int secondMostCommonAI;
    //The current turn's roll number
    int currentRollNum;

    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof YahtzeeGameState) {
            masterGameState = (YahtzeeGameState) info;
        }


        sleep(500);


        //instantiates the instance variables.
        diceArr = masterGameState.getDiceArray();
        numDiceAI = ((YahtzeeLocalGame) game).totalDice(masterGameState.getDiceArray());
        mostCommonAI = ((YahtzeeLocalGame) game).maxNumDice(numDiceAI);
        currentRollNum = masterGameState.getRollNum();


        //Leftover code from dumb AI - basically just allows it to run.
        if (!(info instanceof NotYourTurnInfo)) {


            int rand = (int) (Math.random() * 14);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //Dumb if statement, will change later. Just selects 3 of kind if AI gets it.
//            if (threeOfKind() && masterGameState.getChosen(this.playerNum,8)){
//                YahtzeeScore action = new YahtzeeScore(this, 8, playerNum);
//                game.sendAction(action);
//            }

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
        if (mostCommonAI >= 3) {
            return true;
        } else {
            return false;
        }
    }

    // ((YahtzeeLocalgame) game).LargeStraight - checks if there is large straight.
    // ((YahtzeeLocalgame) game).SmallStraight - checks if there is small straight.
    // ((YahtzeeLocalgame) game).Yahtzee - checks if there is Yahtzee.




}




