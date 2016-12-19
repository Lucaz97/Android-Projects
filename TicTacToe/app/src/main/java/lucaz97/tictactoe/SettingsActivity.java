package lucaz97.tictactoe;

import android.app.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.ToggleButton;





import static lucaz97.tictactoe.MusicControl.setMusic;
import static lucaz97.tictactoe.MusicControl.toPlay;

/**
 * Created by lucaz on 07/07/2016.
 */
public class SettingsActivity extends Activity {
    private static ToggleButton musicButton;


    RelativeLayout layout;
    SeekBar greenBar, redBar, blueBar;

    public static ColorDrawable color;
    public static int red;
    public static int blue;
    public static int green;
    private MediaPlayer music;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        music = MediaPlayer.create(this, R.raw.music);
        music.setLooping(true);
        greenBar = (SeekBar)findViewById(R.id.greenSeekBar);
        blueBar = (SeekBar)findViewById(R.id.blueSeekBar);
        redBar = (SeekBar)findViewById(R.id.redSeekBar);

        layout = (RelativeLayout)findViewById(R.id.relLay);
        color = (ColorDrawable)layout.getBackground();
        musicButton = (ToggleButton)findViewById(R.id.musicButton);

        if(toPlay()) {
            musicButton.setChecked(true);
        }else{
            musicButton.setChecked(false);
        }

        if(red == 0 && blue == 0 && green == 0){
            layout.setBackgroundColor(Color.rgb(19, 19, 30));
        } else {
            layout.setBackgroundColor(Color.rgb(red, green, blue));
        }
            red = Color.red(color.getColor());
            blue = Color.blue(color.getColor());
            green = Color.green(color.getColor());



        greenBar.setProgress(green);
        redBar.setProgress(red);
        blueBar.setProgress(blue);







    }

    @Override
    public void onResume(){
        super.onResume();

        if( toPlay()){

            music.start();



            System.out.println("start music");

        } else {
            //music.pause();
        }
        musicButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled

                    music.start();
                    setMusic(true);
                    System.out.println("start music");

                } else {
                    // The toggle is disabled

                    music.pause();
                    setMusic(false);
                    System.out.println("stop music");

                }

            }
        });


        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                if (green == 0){
                    green = 1;
                }
                layout.setBackgroundColor(Color.rgb(red, green, blue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("green:   " + green);
                layout.setBackgroundColor(Color.rgb(red, green, blue));
                System.out.println("Color: " + color.getColor());
            }
        });

        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                if(red == 0){
                    red = 1;
                }
                layout.setBackgroundColor(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("red:   " + red);
                layout.setBackgroundColor(Color.rgb(red, green, blue));
                System.out.println("Color: " + color.getColor());
            }
        });

        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                if(blue == 0){
                    blue = 1;
                }
                layout.setBackgroundColor(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("blue:   " + blue);
                layout.setBackgroundColor(Color.rgb(red, green, blue));
                System.out.println("Color: " + color.getColor());
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        music.stop();
        System.out.println("pause music");
        music.release();

    }

}
