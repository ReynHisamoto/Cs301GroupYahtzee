package edu.up.cs301.pig;

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
    }

    @Test
    public void checkMaxNumDice() {
    int[] testDice = {1,1,1,2,3};
    YahtzeeLocalGame game = new YahtzeeLocalGame();
    int res = game.checkMaxNumDice(testDice);
    assertEquals(res, 3);
    }

    @Test
    public void checkSecondNumDice() {
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
    }

    @Test
    public void largeStraight() {
    }

    @Test
    public void yahtzee() {
    }
}