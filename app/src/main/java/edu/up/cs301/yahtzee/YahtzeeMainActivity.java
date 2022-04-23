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
 * Hello! This is the part where we discuss how this game fulfills the grading rubric.
 * Our game, almost in its entirety, implements the rules of the game and derives from the
 * design that we started out with.
 *
 * The GUI is mostly effective and easy to use. The only thing
 * that can be confusing is that when you select dice you are actually selecting dice to roll rather
 * than to hold. However, we included that detail in our "how to play" button/screen.
 *
 * The smart AI is pretty smart. It's definitely possible to lose to it.
 *
 * We appropriately addressed all the bugs reported.
 *
 * The game doesn't support network play, but it's close. I discuss the bug down below.
 *
 * The game mostly follows the 301 coding standard, although there's room for improvement.
 *
 * The unit tests are present and passing.
 *
 * We feel the game looks pleasant, and the sound effects make it more satisfying.
 *
 * Extra features:
 * -It can work in both portrait and landscape mode, with a small bug discussed below.
 * -There are sound effects
 * -There is an "how to play" page.
 *
 * We feel we followed the instructions well.
 *
 * Bugs:
 * This game has a bug where when you switch from portrait to
 * landscape it requires you to hit the "roll" button in order to update the gui. However,
 * it can play in both. It would likely take another listener to update the GUI each time
 * the tablet is rotated.
 *
 * Network play doesn't work, but we're close. We can link two tablets up to each other, and both
 * can send actions. However, only the local player can actually see the updated game state. The
 * remote player has a blank GUI. We don't have any idea how to fix this.
 *
 *
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
