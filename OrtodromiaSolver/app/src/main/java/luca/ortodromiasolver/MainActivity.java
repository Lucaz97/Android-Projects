package luca.ortodromiasolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button ortodromiaButton, lossodromiaButton, ventoButton, infoButton; //declaring menu's buttons


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate buttons and set them on click listener
        ortodromiaButton = (Button)findViewById(R.id.ortodromiaButton);
        ortodromiaButton.setOnClickListener(this);
        lossodromiaButton = (Button)findViewById(R.id.lossodromiaButton);
        lossodromiaButton.setOnClickListener(this);
        ventoButton = (Button)findViewById(R.id.ventoButton);
        ventoButton.setOnClickListener(this);
        infoButton = (Button)findViewById(R.id.infoButton);
        infoButton.setOnClickListener(this);


    }

    //handles ortodrima button click
    private void ortodromiaClick(){
        startActivity(new Intent("android.ortodromiaActivity"));
    }

    //handles lossodromia button click
    private void lossodromiaClick(){
        startActivity(new Intent("android.lossodromiaActivity"));
    }

    //handles vento button click
    private void ventoClick(){
        startActivity(new Intent("android.ventoActivity"));
    }

    //handles info button click
    private void infoClick(){
        startActivity(new Intent("android.infoActivity"));
    }





    @Override //looking for button pressed !
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.ortodromiaButton:     //if ortodromia button is pressed change activity and go on ortodromia activity
                ortodromiaClick();
                break;

            case R.id.lossodromiaButton:    //if losodromia button is pressed change activity andgo on ortodromia activity
                lossodromiaClick();
                break;

            case R.id.ventoButton:          //if vento button is pressed change activity and go on vento activity
                ventoClick();
                break;

            case R.id.infoButton:           //if info button is pressed change activity and go on info activity
                infoClick();
                break;

        }
    }
}
