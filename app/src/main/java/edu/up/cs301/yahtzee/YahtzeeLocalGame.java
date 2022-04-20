package edu.up.cs301.yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;


/**
 * class YahtzeeLocalGame controls the play of the game
 * *
 * * @author Augustine Pham James Lulay Reyn Hisamoto Santiago Franco
 * * @version February 2022
 * <p>

/**
 External Citation
 Date: 23 September 2022
 Problem: forgot how for each loops worked
 Resource:
 https://www.geeksforgeeks.org/for-each-loop-in-java/
 Solution: I used the example code from this post.
 */
public class YahtzeeLocalGame extends LocalGame {

    private YahtzeeGameState masterGameState;

    /**
     * This ctor creates a new game state
     */
    public YahtzeeLocalGame() {
        masterGameState = new YahtzeeGameState(2);
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMakeAction(int playerIdx) {
        return (playerIdx != masterGameState.getTurn());
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        /**
         Essentially, this "if" statement detects if the action is a YahtzeeSelect action. If it is, it detects
         who made the action, then it will tell the YahtzeeGameState to "keep" the die that has had
         the action performed on it.
         TODO CHANGE NAME OF KEEP
         Although it is named Yahtzee Keep, when toggling a dice anf highlighting it green, it actually
         means you are wanting to Roll said dice. In a sense, all the dice that are not highlighted are being "kept"
         **/

        if (action instanceof YahtzeeSelect && !canMakeAction(((YahtzeeSelect) action).getIdx())) {
            //TODO WRITE MORE COMMENTS
            if (!masterGameState.getDice( ( (YahtzeeSelect)action).getSelected() ).isKeep() ) {
                masterGameState.getSelectedDice().add(masterGameState.getDice(((YahtzeeSelect) action).getSelected()));
                masterGameState.getDice(((YahtzeeSelect) action).getSelected()).setKeep(true);
            } else {
                masterGameState.getSelectedDice().remove(masterGameState.getDice(((YahtzeeSelect) action).getSelected()));
                masterGameState.getDice(((YahtzeeSelect) action).getSelected()).setKeep(false);
                if (masterGameState.getSelectedDice().size() > 3) {
                    masterGameState.getSelectedDice().get(3).setKeep(false);
                    masterGameState.getSelectedDice().remove(3);
                }
            }
            return true;

            /**
             * Sets selected dice value to random int 1-6
             */
        } else if (action instanceof YahtzeeRoll && !canMakeAction(((YahtzeeRoll) action).getIdx()) && masterGameState.getRollNum() < 3) {
            masterGameState.setRollNum(masterGameState.getRollNum() + 1);
            int rand;
            // just double checking that there is the right amount of selected dice
            if (masterGameState.getSelectedDice().size() > 3) {
                masterGameState.getSelectedDice().get(3).setKeep(false);
                masterGameState.getSelectedDice().remove(3);
            }
            if (!canMakeAction(((YahtzeeRoll) action).getIdx())) {
                for (Dice dice : masterGameState.getSelectedDice()) {
                    rand = (int) (Math.random() * 6 + 1);
                    dice.setVal(rand);
                }
                return true;
            }
        }

        /**
         * checks where the player has clicked and adds to the scoreboard accordingly
         */
        if (action instanceof YahtzeeScore && !canMakeAction(((YahtzeeScore) action).getIdx()) && ((YahtzeeScore) action).getRow() < 16 &&
                ((YahtzeeScore) action).getRow() >= 0
                && masterGameState.getScores(((YahtzeeScore) action).getIdx())[((YahtzeeScore) action).getRow()] == 0
                && !masterGameState.getChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow())) {
            int score = 0;
            int[] numDice = totalDice(masterGameState.getDiceArray());
            int mostCommon = maxNumDice(numDice);
            int secondCommon = secondNumDice(numDice, mostCommon);
            boolean fullTop = false;
            boolean alreadyChosen = masterGameState.getChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow());
            if (alreadyChosen) {
                return false;
            }
            if (((YahtzeeScore) action).getRow() == 6 || ((YahtzeeScore) action).getRow() == 7 ||
                    ((YahtzeeScore) action).getRow() == 15) {
                return false;
            }

            // if player selects aces twos etc. get score then add to score sheet
            if (((YahtzeeScore) action).getRow() <= 5 && numDice[((YahtzeeScore) action).getRow()] > 0) {
                for (Dice dice : masterGameState.getDiceArray()) {
                    if (dice.getVal() == ((YahtzeeScore) action).getRow() + 1) {
                        score += dice.getVal();
                    }
                }
                masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
            }
            // if player selects 3 of a kind then get the most common dice value and multiply by three
            else if (((YahtzeeScore) action).getRow() == 8) {
                if (mostCommon >= 3) {
                    int valOfThree = 0;
                    for (int i = 0; i < numDice.length; i++) {
                        if (mostCommon == numDice[i]) {
                            valOfThree = i;
                        }
                    }
                    score += valOfThree * 3;
                    for (int i = 0; i < masterGameState.getDiceArray().length; i++) {
                        if (masterGameState.getDiceArray()[i].getVal() != valOfThree) {
                            score += masterGameState.getDiceArray()[i].getVal();
                        }
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else if (masterGameState.getRollNum() == 3) {
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), 0);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else {
                    return false;
                }
            }
            // if player selects 4 of a kind then get the most common dice value and multiply by four
            else if (((YahtzeeScore) action).getRow() == 9) {
                if (mostCommon >= 4) {
                    int valOfFour = 0;
                    for (int i = 0; i < numDice.length; i++) {
                        if (mostCommon == numDice[i]) {
                            valOfFour = i;
                        }
                    }
                    score += valOfFour * 4;
                    for (int i = 0; i < masterGameState.getDiceArray().length; i++) {
                        if (masterGameState.getDiceArray()[i].getVal() != valOfFour) {
                            score += masterGameState.getDiceArray()[i].getVal();
                        }
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else if (masterGameState.getRollNum() == 3) {
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), 0);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else {
                    return false;
                }
            }
            // if player selects full house then checks for full house by looking if there are three of one type and two of another if true then set score to 25
            else if (((YahtzeeScore) action).getRow() == 10) {
                if (mostCommon == 3 && secondCommon == 2) {
                    score += 25;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else if (masterGameState.getRollNum() == 3) {
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), 0);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else {
                    return false;
                }
                // if player selects small straight check if true if so then give player 30 pts on score card
            } else if (((YahtzeeScore) action).getRow() == 11) {
                if (SmallStraight(numDice)) {
                    score += 30;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else if (masterGameState.getRollNum() == 3) {
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), 0);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else {
                    return false;
                }
                // if player selects large straight and true then give player 40 points
            } else if (((YahtzeeScore) action).getRow() == 12) {
                if (LargeStraight(numDice)) {
                    score += 40;
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                    // if player chooses yahtzee and if true give player 50 points
                } else if (masterGameState.getRollNum() == 3) {
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), 0);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else {
                    return false;
                }
            } else if (((YahtzeeScore) action).getRow() == 14) {
                if (Yahtzee(numDice)) {
                    if (masterGameState.getScores(((YahtzeeScore) action).getIdx())[13] == 0) {
                        score += 50;
                    } else if (masterGameState.getScores(((YahtzeeScore) action).getIdx())[13] > 0) {
                        score += 100;
                    }
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else if (masterGameState.getRollNum() == 3) {
                    masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), 0);
                    masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
                } else {
                    return false;
                }

                // if player chooses chance sum dice and add to score
            } else if (((YahtzeeScore) action).getRow() == 13) {
                for (Dice dice : masterGameState.getDiceArray()) {
                    score += dice.getVal();
                }
                masterGameState.setScores(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), score);
                masterGameState.setChosen(((YahtzeeScore) action).getIdx(), ((YahtzeeScore) action).getRow(), true);
            }

            score = 0;

            for (int i = 0; i < 6; i++) {
                score += masterGameState.getScores(((YahtzeeScore) action).getIdx())[i];
                if (score > 63) {
                    fullTop = true;
                }
            }

            masterGameState.setScores(((YahtzeeScore) action).getIdx(), 6, score);
            score = 0;

            for (int i = 0; i < masterGameState.getScores(((YahtzeeScore) action).getIdx()).length; i++) {
                if (i != 6 && i != 7 && i != 15)
                    score += masterGameState.getScores(((YahtzeeScore) action).getIdx())[i];
            }

            if (fullTop) {
                score += 35;
                masterGameState.setScores(((YahtzeeScore) action).getIdx(), 7, 35);
            }

            masterGameState.setScores(((YahtzeeScore) action).getIdx(), 15, score);
            masterGameState.setRound(masterGameState.getRound() + 1);
            masterGameState.rollAllDice();
            masterGameState.setRollNum(1);
            masterGameState.getSelectedDice().clear();

            if (masterGameState.getTurn() < players.length - 1) {
                masterGameState.setTurn(masterGameState.getTurn() + 1);
            } else {
                masterGameState.setTurn(0);
            }

            return true;
        }

        // not valid move
        return false;
    }


    //makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        YahtzeeGameState updatedGameState = new YahtzeeGameState(masterGameState);
        p.sendInfo(updatedGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     *        a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        int max = 0;
        for (int i = 0; i < players.length; i++) {
            if (masterGameState.getScores(max)[15] < masterGameState.getScores(i)[15]) {
                max = i;
            }
        }

        if (masterGameState.getRound() >= 13 * players.length) {
            return "The winner is " + playerNames[max] + " !";
        }

        return null;

    }

    /*
    checks the most common dice given an array of common dice.Getter method.
     */
    public int maxNumDice(int[] potValue) {
        int maxNum = 0;
        for (int numDice : potValue) {
            if (maxNum < numDice) {
                maxNum = numDice;
            }
        }
        return maxNum;
    }

    /*
    checks for the second most common dice given an array of common dice. Getter Method.
     */
    public int secondNumDice(int[] potValue, int currentMax) {
        int maxNum = 0;
        for (int numDice : potValue) {
            if (maxNum < numDice && numDice < currentMax) {
                maxNum = numDice;
            }
        }
        return maxNum;
    }


    /*
    creates an array of dice in common.Getter method.
     */
    public int[] totalDice(Dice[] dice) {
        int[] potValue = new int[7];
        for (int i = 0; i < potValue.length; i++)
            for (Dice dices : dice) {
                if (dices.getVal() == i) {
                    potValue[i]++;
                }
            }
        return potValue;
    }

    /*
    checks whether or not the given hand of dice is a small straight. Getter method
     */
    public boolean SmallStraight(int[] potValue) {
        int numInstance = 0;
        for (int val : potValue) {
            if (val >= 1) {
                numInstance++;
                if (numInstance >= 4) {
                    break;
                }
            } else {
                numInstance = 0;
            }
        }
        return (numInstance >= 4);
    }


    /*
    checks whether or not the given hand of dice is a large straight. Getter Method.
     */
    public boolean LargeStraight(int[] potValue) {
        int numInstance = 0;
        for (int val : potValue) {
            if (val == 1) {
                numInstance++;
                if (numInstance >= 5) {
                    break;
                }
            } else {
                numInstance = 0;
            }
        }
        return (numInstance >= 5);
    }

    /*
    checks whether or not the given hand of dice is a yahtzee. Getter method.
     */
    public boolean Yahtzee(int[] potValue) {
        for (int val : potValue) {
            if (val == 5) {
                return true;
            }
        }
        return false;
    }


}// class YahtzeeLocalGame
