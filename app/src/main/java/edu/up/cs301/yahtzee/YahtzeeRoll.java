package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class YahtzeeRoll extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    private int idx;
    public YahtzeeRoll(GamePlayer player, int id) {
        super(player);
        idx = id;
    }
    public int getIdx(){return idx;}
    public void setIdx(int idx){this.idx = idx;}
}
