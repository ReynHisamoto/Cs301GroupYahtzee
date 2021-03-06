package edu.up.cs301.yahtzee;

import static org.junit.Assert.*;

import org.junit.Test;

public class YahtzeeLocalGameTest {

    @Test
    public void canMakeAction() throws Exception{
        YahtzeeLocalGame game = new YahtzeeLocalGame();
        assertFalse(game.canMakeAction(1));
        assertFalse(game.canMakeAction(2));
        assertFalse(game.canMakeAction(3));
        assertTrue(game.canMakeAction(0));
    }

    @Test
    public void makeMove() {
    }

    @Test
    public void sendUpdatedStateTo() {
    }

    @Test
    public void checkIfGameOver() {
        /*YahtzeeLocalGame game = new YahtzeeLocalGame();
       /* YahtzeeLocalGame game = new YahtzeeLocalGame();
        YahtzeeGameState gameState = game.getMasterGameState();
        String gameEnd = "";
        gameState.setRound(14);
        if (gameState.getRound() > 13) {
            gameEnd = "game over";
        }
        assertEquals("game over", gameEnd);*/
    }

    //@Test
   /* public void checkMaxNumDice() {
    int[] testDice = {1,1,1,2,3};
    YahtzeeLocalGame game = new YahtzeeLocalGame();
    int res = game.checkMaxNumDice(testDice);
    assertEquals(res, 3);
    }*/

    @Test
    public void checkSecondNumDice() {
        int[] testDice = {1,1,1,2,3};
        YahtzeeLocalGame game = new YahtzeeLocalGame();

        //int second = game.checkSecondNumDice(testDice);
        //assertEquals(1,second );
    }

    @Test
    public void totalDice() {
        YahtzeeLocalGame game = new YahtzeeLocalGame();
        Dice[] dice = {new Dice(),new Dice(),new Dice(),new Dice(),new Dice()};
        dice[2].setVal(2);
        dice[1].setVal(2);
        dice[0].setVal(2);
        int[] maxNum = game.totalDice(dice);
        assertEquals(maxNum[2], 3);
        assertEquals(maxNum[0], 2);
        assertEquals(maxNum[4],0);
    }

    @Test
    public void smallStraight() {
        YahtzeeLocalGame game =  new YahtzeeLocalGame();
        int[] dice = new int[5];
        game.SmallStraight(dice);
        dice[0] = 1;
        dice[1] = 2;
        dice[2] = 3;
        dice[3] = 4;
        assertEquals(true, game.SmallStraight(dice));

    }

    @Test
    public void largeStraight() {
        YahtzeeLocalGame game =  new YahtzeeLocalGame();
        int[] dice = new int[5];
        game.LargeStraight(dice);
        dice[0] = 1;
        dice[1] = 2;
        dice[2] = 3;
        dice[3] = 4;
        dice[4] = 5;
        assertEquals(true, game.SmallStraight(dice));
    }

    /**
     * Test yahtzee method
     * if all dice are the same number, return true
     */
    @Test
    public void yahtzee() {

        Dice[] dice = {new Dice(),new Dice(),new Dice(),new Dice(),new Dice()};
        boolean yahtzee;
        dice[0].setVal(2);
        dice[1].setVal(2);
        dice[2].setVal(2);
        dice[3].setVal(2);
        dice[4].setVal(2);

        int num = dice[0].getVal();

        if (dice[1].getVal() == num && dice[2].getVal() == num &&
                dice[3].getVal() == num && dice[4].getVal() == num) {
            yahtzee = true;
        } else {
            yahtzee = false;
        }
        assertEquals(true, yahtzee);
    }
}