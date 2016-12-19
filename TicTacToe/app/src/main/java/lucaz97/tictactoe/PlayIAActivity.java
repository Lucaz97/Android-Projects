package lucaz97.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import static lucaz97.tictactoe.MusicControl.toPlay;
import static lucaz97.tictactoe.SettingsActivity.blue;
import static lucaz97.tictactoe.SettingsActivity.green;
import static lucaz97.tictactoe.SettingsActivity.red;

/**
 * Created by lucaz on 05/07/2016.
 */
public class PlayIAActivity extends Activity implements View.OnClickListener {
    Button newGameButton, exitButton;
    ImageButton button1, button2, button3, button4, button5, button6, button7,button8, button9;
    TextView compScoreTxt, userScoreTxt, drawsTxt;

    RelativeLayout layout;
    private MediaPlayer music;
    protected int button1State = 0;
    protected int button2State = 0;
    protected int button3State = 0;
    protected int button4State = 0;
    protected int button5State = 0;
    protected int button6State = 0;
    protected int button7State = 0;
    protected int button8State = 0;
    protected int button9State = 0;

    protected int rowState = 0;
    protected int colState = 0;
    protected int diagState = 0;
    public boolean playerTurn = true;

    private int userScore = 0;
    private int compScore = 0;
    private int gameNum = 1;
    private int draws = 0;

    protected IAcomp myIA = new IAcomp();


    protected int[] buttonState = {button1State, button2State, button3State, button4State, button5State, button6State, button7State, button8State, button9State};

    public Griglia field = new Griglia(buttonState);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_ia_layout);
        music = MediaPlayer.create(this, R.raw.music);
        music.setLooping(true);


        button1 = (ImageButton)findViewById(R.id.butt0);
        button1.setOnClickListener(this);
        button2 = (ImageButton)findViewById(R.id.butt1);
        button2.setOnClickListener(this);
        button3 = (ImageButton)findViewById(R.id.butt2);
        button3.setOnClickListener(this);
        button4 = (ImageButton)findViewById(R.id.butt3);
        button4.setOnClickListener(this);
        button5 = (ImageButton)findViewById(R.id.butt4);
        button5.setOnClickListener(this);
        button6 = (ImageButton)findViewById(R.id.butt5);
        button6.setOnClickListener(this);
        button7 = (ImageButton)findViewById(R.id.butt6);
        button7.setOnClickListener(this);
        button8 = (ImageButton)findViewById(R.id.butt7);
        button8.setOnClickListener(this);
        button9 = (ImageButton)findViewById(R.id.butt8);
        button9.setOnClickListener(this);

        newGameButton = (Button)findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(this);
        exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);

        compScoreTxt = (TextView)findViewById(R.id.aiScore);
        userScoreTxt = (TextView)findViewById(R.id.userScore);
        drawsTxt = (TextView)findViewById(R.id.drawsText);

        newGameButton.setVisibility(View.GONE);
        exitButton.setVisibility(View.GONE);



        layout = (RelativeLayout)findViewById(R.id.IAlayout);
        System.out.println("green:   " + green);
        System.out.println("red:   " + red);
        System.out.println("blue:   " + blue);
        if(red == 0 && blue == 0 && green == 0){
            layout.setBackgroundColor(Color.rgb(19, 19, 30));
        } else {
            layout.setBackgroundColor(Color.rgb(red, green, blue));
        }
    }



    private void checkIAmove(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){
                if(field.getCell(row, col) == 2){
                    if(col + row == 0){
                        button1.setImageResource(R.drawable.cellcircle);
                    } else if (col+row == 4){
                        button9.setImageResource(R.drawable.cellcircle);
                    } else if (row == 1 && col == 1){
                        button5.setImageResource(R.drawable.cellcircle);
                    } else if (row == 1 && col == 0){
                        button4.setImageResource(R.drawable.cellcircle);
                    } else if (row == 1 && col == 2){
                        button6.setImageResource(R.drawable.cellcircle);
                    } else if (row == 0 && col == 1){
                        button2.setImageResource(R.drawable.cellcircle);
                    } else if (row == 0 && col == 2){
                        button3.setImageResource(R.drawable.cellcircle);
                    } else if (row == 2 && col == 0){
                        button7.setImageResource(R.drawable.cellcircle);
                    } else if (row ==2 && col == 1){
                        button8.setImageResource(R.drawable.cellcircle);
                    }
                }
            }
        }
    }

    public void buttonPressed(int x, int y, ImageButton button){
        if(field.getCell(x,y) == 0 && playerTurn == true){
            field.setCell(x, y, 1);
            button.setImageResource(R.drawable.cellcross);
            playerTurn = false;

            if(myIA.hasXWon()){
               // PlayAgain.setText("         YOU WON, \n Press to play again");
                //PlayAgain.setVisible(true);
                userScore = userScore + 1;
                exitButton.setVisibility(View.VISIBLE);
                newGameButton.setVisibility(View.VISIBLE);
            } else if (myIA.hasOWon()){
                //PlayAgain.setText("         YOU LOST, \n Press to play again");
                //PlayAgain.setVisible(true);
                compScore = compScore + 1;
                exitButton.setVisibility(View.VISIBLE);
                newGameButton.setVisibility(View.VISIBLE);
            } else if (field.getRowState(0) == 4 && field.getRowState(1) == 4 && field.getRowState(2) == 4){
                //PlayAgain.setText("       It's a draw!, \n Press to play again");
                //PlayAgain.setVisible(true);
                draws = draws +1;
                exitButton.setVisibility(View.VISIBLE);
                newGameButton.setVisibility(View.VISIBLE);
            } else {
                //turnText.setText("AI Turn");

                myIA.setGrill(field);
                myIA.callMinimax(0, 1);
                for (PointsAndScores pas : myIA.rootsChildrenScores) {
                    System.out.println("Point: " + pas.point + " Score: " + pas.score);
                }
                myIA.placeAMove(myIA.returnBestMove(), 2);
                myIA.toGrill(field);
                checkIAmove();

                for(int i = 0; i < 3; i++){
                    rowState = field.getRowState(i);
                    colState = field.getColState(i);
                    if(rowState != 0 || colState != 0){
                        break;
                    }
                }
                diagState = field.getDiagonalsState();

                if(myIA.hasXWon()){
                    //PlayAgain.setText("        YOU WON, \n Press to play again");
                    //PlayAgain.setVisible(true);
                    userScore = userScore + 1;
                    exitButton.setVisibility(View.VISIBLE);
                    newGameButton.setVisibility(View.VISIBLE);
                } else if (myIA.hasOWon()){
                    //PlayAgain.setText("       YOU LOST, \n Press to play again");
                   // PlayAgain.setVisible(true);
                    compScore = compScore + 1;
                    exitButton.setVisibility(View.VISIBLE);
                    newGameButton.setVisibility(View.VISIBLE);
                } else if (field.getRowState(0) == 4 && field.getRowState(1) == 4 && field.getRowState(2) == 4){
                   // PlayAgain.setText("      It's a draw!, \n Press to play again");
                    //PlayAgain.setVisible(true);
                    draws = draws +1;
                    exitButton.setVisibility(View.VISIBLE);
                    newGameButton.setVisibility(View.VISIBLE);
                } else {
                    playerTurn = true;
                    //turnText.setText("Your turn");
                }
            }

            compScoreTxt.setText(Integer.toString(compScore));
            userScoreTxt.setText(Integer.toString(userScore));
            drawsTxt.setText(Integer.toString(draws));
        } else {
            System.out.println("this cell is not avaiable");
        }
    }

    private void newGame(){
        button1.setImageResource(R.drawable.cellbackground);
        button2.setImageResource(R.drawable.cellbackground);
        button3.setImageResource(R.drawable.cellbackground);
        button4.setImageResource(R.drawable.cellbackground);
        button5.setImageResource(R.drawable.cellbackground);
        button6.setImageResource(R.drawable.cellbackground);
        button7.setImageResource(R.drawable.cellbackground);
        button8.setImageResource(R.drawable.cellbackground);
        button9.setImageResource(R.drawable.cellbackground);

        exitButton.setVisibility(View.GONE);
        newGameButton.setVisibility(View.GONE);

        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){
                field.setCell(row, col, 0);
            }
        }
        myIA.setGrill(field);

        gameNum = gameNum +1;
        String num =Integer.toString(gameNum);
        //gameNumber.setText("Game " + num);

        playerTurn = true;
        //turnText.setText("Your turn");
        //PlayAgain.setVisible(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butt0:
                buttonPressed(0,0,button1);
                break;
            case R.id.butt1:
                buttonPressed(0,1,button2);
                break;
            case R.id.butt2:
                buttonPressed(0,2,button3);
                break;
            case R.id.butt3:
                buttonPressed(1,0,button4);
                break;
            case R.id.butt4:
                buttonPressed(1,1,button5);
                break;
            case R.id.butt5:
                buttonPressed(1,2,button6);
                break;
            case R.id.butt6:
                buttonPressed(2,0,button7);
                break;
            case R.id.butt7:
                buttonPressed(2,1,button8);
                break;
            case R.id.butt8:
                buttonPressed(2,2,button9);
                break;
            case R.id.newGameButton:
                newGame();
                break;
            case R.id.exitButton:
                if(music.isPlaying()) {
                    music.pause();
                }
                Intent intent = new Intent(PlayIAActivity.this, MainActivityTic.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

        }
    }
    @Override
    public void onStart(){
        super.onStart();

        if( toPlay()){

            music.start();



            System.out.println("start music");

        }
    }
    @Override
    public void onPause(){
        super.onPause();
        music.stop();
        music.release();
    }
}