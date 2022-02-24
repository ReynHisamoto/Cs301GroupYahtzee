package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    private int playerId;
    private int p0Score;
    private int p1Score;
    private Dice dice1,dice2,dice3,dice4,dice5;
    private int rollNum;
    private ScoreCard scoreCard;  // add more comments
    private int round;


    /**
     * ctor
     */
    public PigGameState() {
        this.playerId = 0;
        this.p0Score = 0;
        this.p1Score = 0;
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.dice3 = new Dice();
        this.dice4 = new Dice();
        this.dice5 = new Dice();
        this.rollNum = 1;
        this.scoreCard = new ScoreCard();
        this.round = 1;



    }

    /**
     * copy ctor
     * @param g
     */
    public PigGameState (PigGameState g) {
        this.playerId = g.playerId;
        this.p0Score = g.p0Score;
        this.p1Score = g.p1Score;
        this.dice1 = new Dice(g.dice1);
        this.dice2 = new Dice(g.dice2);
        this.dice3 = new Dice(g.dice3);
        this.dice4 = new Dice(g.dice4);
        this.dice5 = new Dice(g.dice5);
        this.rollNum = g.rollNum;
        //this.scoreCard = new ScoreCard();
        this.round = g.round;


    }



    //getter methods for PigGameState
    //todo getter methods for yahtzee

    public int getPlayerId() {
        return playerId;
    }

    public int getP0Score() {
        return p0Score;
    }

    public int getP1Score() {
        return p1Score;
    }

    public int getDiceNum() {
        return diceNum;
    }

    public int getRunningTotal() {
        return runningTotal;
    }


    //setter methods for PigGameState
    //todo setter methods for yahtzee
    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void setP0Score(int p0Score) {
        this.p0Score = p0Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setDiceNum(int diceNum) {
        this.diceNum = diceNum;
    }


    @Override
    public String toString() {
        return "PigGameState{" +
                "playerId=" + playerId +
                ", p0Score=" + p0Score +
                ", p1Score=" + p1Score +
                ", dice1=" + dice1.getVal() +
                ", dice2=" + dice2.getVal() +
                ", dice3=" + dice3.getVal() +
                ", dice4=" + dice4.getVal() +
                ", dice5=" + dice5.getVal() +
                ", rollNum=" + rollNum +
                ", scoreCard=" + scoreCard +
                ", round=" + round +
                '}';
    }
}






