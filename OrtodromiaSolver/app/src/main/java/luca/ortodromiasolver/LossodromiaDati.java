package luca.ortodromiasolver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by lucaz on 21/03/2016.
 */


public class LossodromiaDati extends Activity implements View.OnClickListener{
    private Point puntoA = new Point();         //starting point
    private Point puntoB = new Point();         //destination point
    private Button calculateLossodromia, hideError;     //buttons
    private Switch swLatA, swLatB, swLongA, swLongB;        //sign switches
    private EditText latAin, latBin, longAin, longBin;      //input text fields
    private TextView errorMessage, distRslt, deltaLatRslt, deltaLongRslt, rottaRslt;        //texts for error and results
    private ScrollView datiLyt;                                                             //layout for data
    private LinearLayout risultatiLyt;                                                      //layout for results
    private Lossodromia myLossodromia = new Lossodromia();                                  //lossodromia

    private double latA, latB, longA, longB, distanza, deltaLat, deltaLong, rottaVera;      //lossodromia's data
    private String deltaLatSign, deltaLongSign;                                             //signs for latitude and longitude difference


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lossodromia_dati_layout);

        //initiating layouts to be able to show and hide what is needed
        risultatiLyt = (LinearLayout)findViewById(R.id.risultatiLossodromia);
        risultatiLyt.setVisibility(View.INVISIBLE);
        datiLyt = (ScrollView)findViewById(R.id.scrollViewLoss);
        datiLyt.setVisibility(View.VISIBLE);


        //initiating buttons and set them on click listener
        hideError = (Button)findViewById(R.id.hideError);
        hideError.setOnClickListener(this);

        calculateLossodromia = (Button)findViewById(R.id.calcolaLossodromia);
        calculateLossodromia.setOnClickListener(this);

        //initiating the error text
        errorMessage = (TextView)findViewById(R.id.ErrorMessage);

        //setting the error text and button invisible ---> they are only showed when input errors occurs
        errorMessage.setVisibility(View.INVISIBLE);
        hideError.setVisibility(View.INVISIBLE);

        //initiating switch for pointA latitude's sign and setting it on checked change listener
        swLatA = (Switch)findViewById(R.id.switchLatALoss);
        swLatA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    swLatA.setText("S");        //South if is checked
                }else {
                    swLatA.setText("N");        //North if it's not
                }
            }
        });

        //initiating switch for pointA latitude's sign and setting it on checked change listener
        swLatB = (Switch) findViewById(R.id.switchLatBLoss);
        swLatB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swLatB.setText("S");        //South if is checked
                } else {
                    swLatB.setText("N");        //North if it's not
                }
            }
        });


        //initiating switch for pointA longitude's sign and setting it on checked change listener
        swLongA = (Switch) findViewById(R.id.switchLongALoss);
        swLongA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swLongA.setText("W");       //West if it's checked
                } else {
                    swLongA.setText("E");       //East if it's not
                }
            }
        });

        //initiating switch for pointB longitude's sign and setting it on checked change listener
        swLongB = (Switch) findViewById(R.id.switchLongBLoss);
        swLongB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swLongB.setText("W");       //West if it's checked
                } else {
                    swLongB.setText("E");       //East if it's not
                }
            }
        });


        //initiating text fields for data input
        latAin = (EditText)findViewById(R.id.latAinputLoss);
        latBin = (EditText)findViewById(R.id.latBinputLoss);
        longBin = (EditText)findViewById(R.id.longBinputLoss);
        longAin = (EditText)findViewById(R.id.longAinputLoss);

        //initiating text field to display outputs (results)
        distRslt = (TextView)findViewById(R.id.distLossRslt);
        deltaLatRslt = (TextView)findViewById(R.id.deltaLatRsltLoss);
        deltaLongRslt = (TextView)findViewById(R.id.deltaLongRsltLoss);
        rottaRslt = (TextView)findViewById(R.id.rottaRslt);


    }

    //when calculate button is pressed:
    private void verificaDati(){

        //first thing to do is to check that the data inserted is in the right format to prevent crashes

        try {


            latA = Double.parseDouble(String.valueOf(latAin.getText()));
            latB = Double.parseDouble(String.valueOf(latBin.getText()));
            longA = Double.parseDouble(String.valueOf(longAin.getText()));
            longB = Double.parseDouble(String.valueOf(longBin.getText()));

            //if data is ok start assigning it to local variables to be able to use it
            //first set the points
            puntoA.setLat(latA);
            puntoA.setIsNorth(!swLatA.isChecked());
            puntoA.setLong(longA);
            puntoA.setIsEast(!swLongA.isChecked());
            puntoB.setLat(latB);
            puntoB.setIsNorth(!swLatB.isChecked());
            puntoB.setLong(longB);
            puntoB.setIsEast(!swLongB.isChecked());

            //then pass the points to the Lossodromia
            myLossodromia.setPointA(puntoA);
            myLossodromia.setPointB(puntoB);

            //once data is set calculate the results
            calcolaRisultati();
        } catch (Exception e) {

            //if data is not in the right format display an error message
            errorMessage.setVisibility(View.VISIBLE);
            hideError.setVisibility(View.VISIBLE);
        }




    }

    private void calcolaRisultati() {

        //call the method to calculate the results
        myLossodromia.calcolaRisultati();

        //get the results and assign them to local variables to display it
        distanza = myLossodromia.getLossodromicDistance();
        deltaLat = myLossodromia.getDeltaLatitude();
        deltaLong = myLossodromia.getDeltaLongitude();
        rottaVera = myLossodromia.getRottaVera();


        //formatting results for a nice display
        if(deltaLat < 0){
            deltaLat = -1* deltaLat;
            deltaLatSign = "  S  ";

        } else {
            deltaLatSign = "  N  ";
        }

        if(deltaLong < 0){
            deltaLong = -1* deltaLong;
            deltaLongSign = "  W  ";
        } else {
            deltaLongSign = "  E  ";
        }


        //display the results
        distRslt.setText(Double.toString(distanza) + "  NM");
        deltaLatRslt.setText(Double.toString(deltaLat) + deltaLatSign);
        deltaLongRslt.setText(Double.toString(deltaLong) + deltaLongSign );
        rottaRslt.setText(deltaLatSign + Double.toString(rottaVera) + "°" + deltaLongSign);

        //hide the user data input interface
        datiLyt.setVisibility(View.INVISIBLE);
        //show the results
        risultatiLyt.setVisibility((View.VISIBLE));

    }

    @Override       //looking for pressed buttons
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.calcolaLossodromia:           //if calculate button is pressed go ahead with calculating process
                verificaDati();
                break;

            case R.id.hideError:
                //when error message is displaying, if the "OK" button is pressed hide the error message and display again the input interface
                errorMessage.setVisibility(View.INVISIBLE);
                hideError.setVisibility(View.INVISIBLE);
                break;

        }
    }
}
