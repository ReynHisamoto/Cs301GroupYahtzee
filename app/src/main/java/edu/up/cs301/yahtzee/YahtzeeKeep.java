package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class YahtzeeKeep extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    private int idx;
    private int selected;
    public YahtzeeKeep(GamePlayer player,int id,int die) {
        super(player);
        this.idx = id;
        this.selected = die;
    }
    public int getIdx(){return idx;}
    public int getSelected(){return selected;}
}
