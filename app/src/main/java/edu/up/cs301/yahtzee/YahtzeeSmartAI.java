package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

public class YahtzeeSmartAI extends GameComputerPlayer {

    public YahtzeeSmartAI(String name) {
        super(name);
    }

    int numSixes = 0;
    // iterates through the dice array, and
    public int tempBSfunction(){
        int arrLength = masterGameState.getDiceArray().length; //it's always 5


        for(int i = 0; i < arrLength; i++){

        if(diceArr[i].getVal() == 6 && !masterGameState.getChosen(2,6)){
            numSixes++;
        }


        }

        return  numSixes;
    }


    //masterGameState.setChosen(2,6,true);
    YahtzeeGameState masterGameState;
    Dice[] diceArr;



    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof YahtzeeGameState) {
            masterGameState = (YahtzeeGameState) info;
        }

        //sleep(1000);
        diceArr= masterGameState.getDiceArray();

        //checks if there are more than 2 sixes. If there are, selects the "sixes" box.
        if((numSixes > 2) && !masterGameState.getChosen(2,6)){
            masterGameState.setChosen(2,6,true);
        }



        if (!(info instanceof NotYourTurnInfo)) {
            int rand = (int) (Math.random() * 14);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (rand != 6 && rand != 7) {
                YahtzeeScore action = new YahtzeeScore(this, rand, playerNum);
                game.sendAction(action);
            } else {
                YahtzeeRoll action = new YahtzeeRoll(this, playerNum);
                game.sendAction(action);
            }
        }//receiveInfo
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
