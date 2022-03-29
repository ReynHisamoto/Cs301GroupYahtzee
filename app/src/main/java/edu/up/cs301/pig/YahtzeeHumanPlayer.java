package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;

import android.view.MotionEvent;
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
public class YahtzeeHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnTouchListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView[][] scores = null;
    private TextView roundNum = null;
    private Button roll = null;
    private ImageButton[] dice = null;
    private TextView[] names = null;

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
        //flash(0xFFFF0000,3);
        return;
    }
    else{
        for(int i = 0; i < names.length; i++){
            names[i].setText(allPlayerNames[i]);
        }
        for(int i =0; i < dice.length; i++){
            if(((YahtzeeGameState) info).getDice(i).isKeep()){
                dice[i].setBackgroundColor(0xFFFF0000);
            }else{
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
         for(int i = 0; i < allPlayerNames.length; i++){
             for(int j = 0; j < scores[i].length; j++){
                 scores[i][j].setText(((YahtzeeGameState)info).getScores(i)[j]+ "");
             }
         }
         roundNum.setText("ROUND: " + ((YahtzeeGameState) info).getRound() + "\n" + allPlayerNames[((YahtzeeGameState) info).getTurn()] + "'s Turn");
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
        if(button instanceof ImageButton){
            int idx =-99;
            for(int i =0; i < dice.length;i++){
                if(button.equals(dice[i])){
                    idx = i;
                }
            }
            YahtzeeKeep action = new YahtzeeKeep(this, playerNum,idx);
            game.sendAction(action);
        } else if(button instanceof Button){
            YahtzeeRoll action = new YahtzeeRoll(this,playerNum);
            game.sendAction(action);
        }
            }// onClick
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getX() > (this.getTopView().getWidth()*3.65)/5 && view.equals(getTopView())){
             int row = (int) motionEvent.getY()/(scores[1][1].getHeight());
             YahtzeeScore action = new YahtzeeScore(this,row-1,playerNum);
             game.sendAction(action);
            return true;
        }
        return false;
    }
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
        this.roundNum = activity.findViewById(R.id.roundNum);
        this.scores = new TextView[][] {{activity.findViewById(R.id.p0Ones),
                activity.findViewById(R.id.p0Twos),
                activity.findViewById(R.id.p0Threes),
                activity.findViewById(R.id.p0Fours),
                activity.findViewById(R.id.p0Fives),
                activity.findViewById(R.id.p0Sixes),
                activity.findViewById(R.id.p0Sum),
                activity.findViewById(R.id.p0Bonus),
                activity.findViewById(R.id.p0ThreeOfAKind),
                activity.findViewById(R.id.p0FourOfAKind),
                activity.findViewById(R.id.p0FullHouse),
                activity.findViewById(R.id.p0SmallStraight),
                activity.findViewById(R.id.p0LargeStraight),
                activity.findViewById(R.id.p0Chance),
                activity.findViewById(R.id.p0Yahtzee),
                activity.findViewById(R.id.p0TotalScore),
        },
        {activity.findViewById(R.id.p1Ones),
                activity.findViewById(R.id.p1Twos),
                activity.findViewById(R.id.p1Threes),
                activity.findViewById(R.id.p1Fours),
                activity.findViewById(R.id.p1Fives),
                activity.findViewById(R.id.p1Sixes),
                activity.findViewById(R.id.p1Sum),
                activity.findViewById(R.id.p1Bonus),
                activity.findViewById(R.id.p1ThreeOfAKind),
                activity.findViewById(R.id.p1FourOfAKind),
                activity.findViewById(R.id.p1FullHouse),
                activity.findViewById(R.id.p1SmallStraight),
                activity.findViewById(R.id.p1LargeStraight),
                activity.findViewById(R.id.p1Chance),
                activity.findViewById(R.id.p1Yahtzee),
                activity.findViewById(R.id.p1TotalScore),
        }};
        names = new TextView[] {activity.findViewById(R.id.player0),activity.findViewById(R.id.player1)};
        dice = new ImageButton[] {activity.findViewById(R.id.Dice1),
                activity.findViewById(R.id.Dice2),
                activity.findViewById(R.id.Dice3),
                activity.findViewById(R.id.Dice4),
                activity.findViewById(R.id.Dice5),};
        roll = activity.findViewById(R.id.roll);
        //Listen for button presses
    for(ImageButton die : dice){
        die.setOnClickListener(this);
    }
    roll.setOnClickListener(this);
    getTopView().setOnTouchListener(this);
    }//setAsGui


}// class PigHumanPlayer
