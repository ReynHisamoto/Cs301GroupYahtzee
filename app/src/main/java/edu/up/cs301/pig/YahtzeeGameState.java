package edu.up.cs301.pig;

/**
 * class Yahtzee Game State is the game state with all of the variables in yahtzee
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl, Augustine P, Reyn H, James L, Santiago F
 *
 * @version February 2016
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;

public class YahtzeeGameState extends GameState {
    // is the player of the current turn's id
    private int turn;
    // 2d array to score scores [the player id of the person in question][the value when looking at the score sheet from top to bottom]
    private int[][] scores;
    // an array of the dice values in play
    private Dice[] diceArray;
    // current turn's roll number
    private int rollNum;
    //current round of play
    private int round;
    private ArrayList<Dice> selected = new ArrayList<Dice>();
    private int[] numYahtzee;



    /**
     * ctor
     */
    // default constructor that sets up the arrays and vals at game launch
    public YahtzeeGameState(int numPlayers) {
        this.turn = 0;
        this.scores = new int[numPlayers][13];
        this.diceArray= new Dice[5];
        this.numYahtzee = new int[numPlayers];
        //Do I need this for loop to instantiate dice?
        for(int i = 0; i < diceArray.length; i++){
            this.diceArray[i] = new Dice();
        }
        this.rollNum = 1;
        this.round = 1;


    }

    /**
     * copy ctor
     * @param g
     */
    //copies all new values into new gamestate
    public YahtzeeGameState (YahtzeeGameState g) {
        this.turn = g.turn;
        this.rollNum = g.rollNum;
        this.scores = new int[g.scores.length][g.scores[0].length];
        this.diceArray = new Dice[g.diceArray.length];
        for( int i = 0; i < g.scores.length; i++ ){
            for(int j = 0; j < g.scores[i].length; j++){
                this.scores[i][j] = g.scores[i][j];
            }
        }
        for(int i = 0; i < g.diceArray.length; i++){
            this.diceArray[i] = new Dice(g.diceArray[i]);
        }
        for(int i =0; i < selected.size();i++){
            this.selected.add(new Dice(g.diceArray[i]));
        }
        //this.scoreCard = new ScoreCard();
        this.round = g.round;
        for(int i = 0; i < numYahtzee.length; i++){
            this.numYahtzee[i] = g.numYahtzee[i];
        }

    }



    //getter methods for YahtzeeGameState
    //todo getter methods for yahtzee
//returns current player id
    public int getTurn() {
        return turn;
    }
//returns array current score of player
   public int[] getScores(int p){
        return scores[p];
   }
//returns array of dices
    public Dice getDice(int idx) {
        return diceArray[idx];
    }
    public Dice[] getDiceArray(){return diceArray;}
//returns number of rolls during the turn
    public int getRollNum() {
        return rollNum;
    }
    //returns current round number
    public int getRound() {
        return round;
    }
    public ArrayList<Dice> getSelectedDice(){return selected;}

    public int getNumYahtzee(int idx) {
        return numYahtzee[idx];
    }

    //setter methods for YahtzeeGameState
    //todo setter methods for yahtzee
    // sets current turn to given player id
   public void setTurn(int id){
        this.turn = id;
   }
   // sets for a given player, and row that is in question, to a given score
    public void setScores(int id, int row, int score){
        this.scores[id][row] = score;
    }
    //sets the dice at a given ind to a new value
    public void setSelected(Dice dice,int idx){this.selected.set(idx,dice);}
    public void setDiceVal(Dice d, int i){
        d.setVal(i);
    }
    public void incrNumYahtzee(int idx){
        this.numYahtzee[idx]++;
    }
    //swaps the "keep" value of the dice (setter)
    public void swapKeepValue(Dice d,boolean Keep){
        d.setKeep(false);
    }



    //sets the RollNumber to given num
    public void setRollNum(int num){
        this.rollNum = num;
    }

    // sets round to given val
    public void setRound(int num){
        this.round = num;
    }




    @Override
    public String toString() {
        //initializes empty strings for later return
        String playerValues = "";
        String diceValues = "";
        String selectedValues = "";
        //writes out all values in arrays to a given string
        for(int i = 0; i < scores.length; i++){
            playerValues = playerValues.concat("player" + i + " scores: ");
            for(int j = 0; j < scores[i].length; j++){
                playerValues = playerValues.concat(scores[i][j] + ", ");
            }
        }
        for(int i = 0; i < diceArray.length; i++){
            diceValues = diceValues.concat("dice " + i + "'s value is: " + diceArray[i].getVal());
        }
        for(int i =0; i < selected.size(); i++){
            selectedValues = selectedValues.concat("Selected dice: " + i + "'s value is: " + selected.get(i).getVal());
        }
        //returns values with given variable values and new strings above
        return "YahtzeeGameState{" +
                "Turn=" + turn + "\n" +
                playerValues + "\n" +
               diceValues + "\n" +
                selectedValues + "\n" +
                ", rollNum=" + rollNum + "\n" +
                ", round=" + round +
                '}';
    }
}






