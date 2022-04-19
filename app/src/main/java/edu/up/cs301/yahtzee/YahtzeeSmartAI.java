package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

public class YahtzeeSmartAI extends GameComputerPlayer {

    public YahtzeeSmartAI(String name) {
        super(name);
    }
    private final int arrLength = 5;
    private Boolean[][] chosen;


    /**
     * List of geter methods I obtain from YahtzeeLocalGame
     *
     * game.maxNumDice - checks the most common dice given an array of common dice.
     *
     *
     */








    //masterGameState.setChosen(2,6,true);
    YahtzeeGameState masterGameState;
    Dice[] diceArr;

    int test;

    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof YahtzeeGameState) {
            masterGameState = (YahtzeeGameState) info;
        }
        //test = game.maxNumDice;
        sleep(500);
        diceArr = masterGameState.getDiceArray();
        //chose = masterGameState.get


        if (!(info instanceof NotYourTurnInfo)) {
            int rand = (int) (Math.random() * 14);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (this.hasSixes()){
                YahtzeeScore action = new YahtzeeScore(this, 5, playerNum);
                game.sendAction(action);
            }
            else if (rand != 6 && rand != 7) {
                YahtzeeScore action = new YahtzeeScore(this, rand, playerNum);
                game.sendAction(action);
            } else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }
        }//receiveInfo
    }

    /**
     *These are a series of helper methods that scan to see what values the dice array holds. Checks
     * to see how many sixes there are, if there's a 3 of a kind, if there's a full house, etc.
     *
     */

    //counts how many dice of a given value there are.
    public int hasValue (){
        int numValue = 0;
        for(int i = 0; i < arrLength; i++){
            if(diceArr[i].getVal() == 6){ numValue++;}
        }
        return numValue;
    }

    public boolean hasSixes (){
        int numValue = 0;
        for(int i = 0; i < arrLength; i++){
            if(diceArr[i].getVal() == 6){
                numValue++;
            }
        }
        if(numValue >= 1){
            return true;
        }
        else {return false;}
    }



    // 3 of a kind detector. Input value you're looking for three of kind of.
    public boolean threeOfKind (int value){
        int arrLength = masterGameState.getDiceArray().length; //it's always 5

        int numValue = 0;
        for(int i = 0; i < arrLength; i++){
            if(diceArr[i].getVal() == value){
                numValue++;
            }
        }
        if(numValue >= 3){
            return true;
        }
        else {return false;}
    }






}









//package edu.up.cs301.yahtzee;
//
//        import edu.up.cs301.game.GameComputerPlayer;
//        import edu.up.cs301.game.infoMsg.GameInfo;
//        import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
//
///**
// * An AI for Pig
// *
// * @author Andrew M. Nuxoll
// * @version August 2015
// */
//public class YahtzeeComputerPlayer extends GameComputerPlayer {
//
//    /**
//     * ctor does nothing extra
//     */
//    public YahtzeeComputerPlayer(String name) {
//        super(name);
//    }
//
//
//    /**
//     * callback method--game's state has changed
//     *
//     * @param info the information (presumably containing the game's state)
//     */
//    @Override
//    protected void receiveInfo(GameInfo info) {
//        if (!(info instanceof NotYourTurnInfo)) {
//            int rand = (int) (Math.random() * 14);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (rand != 6 && rand != 7) {
//                YahtzeeScore action = new YahtzeeScore(this, rand, playerNum);
//                game.sendAction(action);
//            } else {
//                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
//                game.sendAction(action);
//            }
//
//        }//receiveInfo
//
//
//    }
//}
