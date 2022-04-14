package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

public class YahtzeeSmartAI extends GameComputerPlayer {

    public YahtzeeSmartAI(String name) {
        super(name);
    }


    public int tempBSfunction(){
        int arrLength = masterGameState.getDiceArray().length; //it's always 5
        int numSizes;
        for(int i = 0; i < arrLength; i++){
        if(diceArr[i].getVal() == 6 && masterGameState.chosen[6][2]){

        }
        }
    }


    YahtzeeGameState masterGameState;
    Dice[] diceArr= masterGameState.getDiceArray();


    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof YahtzeeGameState) {
            masterGameState = (YahtzeeGameState) info;
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
