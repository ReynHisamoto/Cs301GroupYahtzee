package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class YahtzeeComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public YahtzeeComputerPlayer(String name) {
        super(name);
    }


    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
    if(!(info instanceof NotYourTurnInfo)){
    int rand = (int) (Math.random() * 14 );
    if(rand != 6 && rand != 7){
    YahtzeeScore action = new YahtzeeScore(this, rand,playerNum);
    game.sendAction(action);
    }

    }//receiveInfo

}
}
