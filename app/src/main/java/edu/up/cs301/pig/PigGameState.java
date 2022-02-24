package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    // is the player of the current turn's id
    private int turn;
    // 2d array to score scores [the player id of the person in question][the value when looking at the score sheet from top to bottom]
    private int[][] scores;
    // an array of the dice values in play
    private int dices[];
    // current turn's roll number
    private int rollNum;
    //current round of play
    private int round;
    //array of selected dice up to 3 per rules
    private int[] selected;


    /**
     * ctor
     */
    // default constructor that sets up the arrays and vals at game launch
    public PigGameState(int numPlayers) {
        this.turn = 0;
        this.scores = new int[numPlayers][13];
        this.dices= new int[5];
        this.rollNum = 1;
        this.round = 1;
        this.selected = new int[3];


    }

    /**
     * copy ctor
     * @param g
     */
    //copies all new values into new gamestate
    public PigGameState (PigGameState g) {
        this.turn = g.turn;
        this.rollNum = g.rollNum;
        for( int i = 0; i < scores.length; i++ ){
            for(int j = 0; j < scores[i].length; j++){
                this.scores[i][j] = g.scores[i][j];
            }
        }
        for(int i = 0; i < dices.length; i++){
            this.dices[i] = g.dices[i];
        }
        for(int i =0; i < selected.length; i++){
            this.selected[i] = g.selected[i];
        }
        //this.scoreCard = new ScoreCard();
        this.round = g.round;


    }



    //getter methods for PigGameState
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
    public int[] getDices() {
        return dices;
    }
//returns number of rolls during the turn
    public int getRollNum() {
        return rollNum;
    }
    //returns current round number
    public int getRound() {
        return round;
    }
    //returns array of selected dice
    public int[] getSelected(){return selected;}


    //setter methods for PigGameState
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
    public void setDices(int ind, int val){
        this.dices[ind] = val;
    }
    //sets the RollNumber to given num
    public void setRollNum(int num){
        this.rollNum = num;
    }
    //sets Selected die at given index to a new value
    public void setSelected(int ind, int val){
        this.selected[ind] = val;
    }
    // sets round to given val
    public void setRound(int num){
        this.round = num;
    }




    @Override
    public String toString() {
        String playerValues = "";
        String diceValues = "";
        String selectedDie = "";
        for(int i = 0; i < scores.length; i++){
            playerValues.concat("player" + i + " scores: ");
            for(int j = 0; j < scores[i].length; j++){
                playerValues.concat(scores[j] + ", ");
            }
        }
        for(int i = 0; i < dices.length; i++){
            diceValues.concat("dice " + i + "'s value is:" + dices[i]);
        }
        for(int i = 0; i < selected.length; i++){
            selectedDie.concat("selected dice " + i + "'s value is:" + selected[i]);
        }
        return "YahtzeeGameState{" +
                "Turn=" + turn + "\n" +
                playerValues + "\n" +
               diceValues + "\n" +
                selectedDie + "\n" +
                ", rollNum=" + rollNum + "\n" +
                ", round=" + round +
                '}';
    }
}






