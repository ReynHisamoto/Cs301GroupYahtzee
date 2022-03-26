package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class YahtzeeHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView[] scores = null;
    private TextView roundNum = null;
    private Button settings = null;
    private ImageButton[] dice = null;
    private TextView names = null;
    private TextView[][] scoreboard = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public YahtzeeHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
    if(!(info instanceof YahtzeeGameState)){
        flash(0xFFFF0000,3);
    }
    else{
        if(playerNum != ((YahtzeeGameState)info).getTurn()){
            for(int i = 0; i < dice.length; i++){
                dice[i].setBackgroundColor(0xFFFF0000);
            }
        }else{
            for(int i = 0; i< dice.length; i++){
                dice[i].setBackgroundColor(0x00000000);
            }
        }
        for(int i = 0; i < dice.length; i++){
         Dice[] values = ((YahtzeeGameState) info).getDiceArray();
         switch(values[i].getVal()){
             case 1: dice[i].setImageResource(R.drawable.face1);
             break;
             case 2: dice[i].setImageResource(R.drawable.face2);
                 break;
             case 3: dice[i].setImageResource(R.drawable.face3);
                 break;
             case 4: dice[i].setImageResource(R.drawable.face4);
                 break;
             case 5: dice[i].setImageResource(R.drawable.face5);
                 break;
             case 6: dice[i].setImageResource(R.drawable.face6);
                 break;

         }
        }
    }
    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        //TODO  You will implement this method to send appropriate action objects to the game
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.yahtzee_layout);

        //Initialize the widget reference member variables

        //Listen for button presses

    }//setAsGui

}// class PigHumanPlayer
