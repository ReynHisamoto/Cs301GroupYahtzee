package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class YahtzeeScore extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    private int row;
    private int idx;
    public YahtzeeScore(GamePlayer player, int row, int idx) {
        super(player);
        this.row = row;
        this.idx = idx;
    }
    public int getRow(){return row;}
    public int getIdx(){return idx;}

    public void setRow(int row) {
        this.row = row;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
