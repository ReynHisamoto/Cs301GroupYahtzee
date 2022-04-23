package edu.up.cs301.yahtzee;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;


/**
 * this is the primary activity for Yahtzee
 *
 * @author Augustine P, Reyn H, James L, Santiago F
 * @version Spring 2022
 */


/**
 * I'm just going to make a note here: This game has a bug where when you switch from portrait to
 * landscape it requires you to hit the "roll" button in order to update the gui. However,
 * it can play in both.
 */
public class YahtzeeMainActivity extends GameMainActivity {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2278;

    /**
     * Create the default configuration for this game:
     * - one human player vs. one computer player
     * - minimum of 1 player, maximum of 2
     *
     * @return
     * 		the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new YahtzeeHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new YahtzeeComputerPlayer(name);
            }});


        playerTypes.add(new GamePlayerType("Smart Comp Player") {
            public GamePlayer createPlayer(String name) {
                return new YahtzeeSmartAI(name);
            }});


        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Yahtzee", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player

        defaultConfig.addPlayer("Smart AI",2); //

        defaultConfig.setRemoteData("Remote Human Player", "", 0);


        return defaultConfig;
    }//createDefaultConfig

    /**
     * create a local game
     *
     * @return
     * 		the local game, a pig game
     */
    @Override
    public LocalGame createLocalGame() {
        return new YahtzeeLocalGame();
    }

}
