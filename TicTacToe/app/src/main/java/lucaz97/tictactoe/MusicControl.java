package lucaz97.tictactoe;

import android.media.MediaPlayer;

/**
 * Created by lucaz on 07/08/2016.
 */
public class MusicControl {
    private static boolean play = true;


    public static boolean toPlay(){
        return play;
    }

    public static  void setMusic(boolean on){
        play = on;
    }

}
