package lucaz97.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import java.io.IOException;


import static lucaz97.tictactoe.SettingsActivity.red;
import static lucaz97.tictactoe.SettingsActivity.blue;
import static lucaz97.tictactoe.SettingsActivity.green;
import static lucaz97.tictactoe.MusicControl.*;

public class MainActivityTic extends AppCompatActivity implements View.OnClickListener {



    Button IAbutton, PlyButton, SettingsButton;
    RelativeLayout layout;

    private MediaPlayer music;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_tic);

        IAbutton = (Button) findViewById(R.id.buttAI);
        IAbutton.setOnClickListener(this);
        PlyButton = (Button) findViewById(R.id.buttPly);
        PlyButton.setOnClickListener(this);
        SettingsButton = (Button) findViewById(R.id.buttSett);
        SettingsButton.setOnClickListener(this);

        layout = (RelativeLayout)findViewById(R.id.mainLayout);





    }



    @Override
    public void onStart() {
        super.onStart();

        music = MediaPlayer.create(this, R.raw.music);
        music.setLooping(true);







    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("green:   " + green);
        System.out.println("red:   " + red);
        System.out.println("blue:   " + blue);
        if(red == 0 && blue == 0 && green == 0){
            layout.setBackgroundColor(Color.rgb(19, 19, 30));
        } else {
            layout.setBackgroundColor(Color.rgb(red, green, blue));
        }
        if( toPlay()){

            music.start();



            System.out.println("start music");

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();




    }

    @Override
    public void onPause(){
        super.onPause();
        music.stop();
        music.release();
    }


    private void AIClick(){
        startActivity(new Intent("android.playIA"));
    }
    private void PlyClick(){
        startActivity(new Intent("android.play1vs1"));
    }
    private void SettClick(){
        startActivity(new Intent("android.settings"));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttAI:
                AIClick();
                break;
            case R.id.buttPly:
                PlyClick();
                break;
            case R.id.buttSett:
                SettClick();
                break;

        }
    }
}
