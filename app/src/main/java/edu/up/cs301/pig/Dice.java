package edu.up.cs301.pig;

public class Dice {
    private int val;
    private boolean keep;

    /**
     * Dice ctor
     */
    public Dice() {
        this.val = 0;
        this.keep = false;
    }

    //TODO getters and setters

    //copy ctor
    public Dice(Dice dice) {
        this.val = dice.val;
        this.keep = dice.keep;
    }

    //getter methods
    public int getVal(){
        return val;
    }

    //If true, its a keeper
    public boolean isKeep() {
        return keep;
    }

    //setter methods
    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
