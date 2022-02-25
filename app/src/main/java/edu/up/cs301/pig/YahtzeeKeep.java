package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class YahtzeeKeep extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    int idx;
    Dice selected;
    public YahtzeeKeep(GamePlayer player,int id,Dice selected) {
        super(player);
        this.idx = id;
        this.selected = selected;
    }
    public int getIdx(){return idx;}
    public Dice getSelected(){return selected;}
}
