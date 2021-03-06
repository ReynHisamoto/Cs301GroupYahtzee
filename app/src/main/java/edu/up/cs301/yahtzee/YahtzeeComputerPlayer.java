package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

/**
 * An AI for Yahtzee
 *
 * @author Augustine P, Reyn H, James L, Santiago F
 * @version Spring 2022
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
     * @param info the information (presumably containing the game's state)
     */

    YahtzeeGameState masterGameState;

    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof NotYourTurnInfo)) {
            int rand = (int) (Math.random() * 15);
            try {
                Thread.sleep(250);
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
