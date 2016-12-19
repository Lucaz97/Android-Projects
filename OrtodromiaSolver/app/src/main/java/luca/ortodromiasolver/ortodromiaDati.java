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
 * Created by lucaz on 20/03/2016.
 */
public class ortodromiaDati extends Activity  implements View.OnClickListener{
    private Button calculateOrtodromia, hideError;      //buttons
    private Switch swLatA, swLatB, swLongA, swLongB;        //switches for points signs
    private EditText latAin, latBin, longAin, longBin;      //text fields for data input
    private TextView errorMessage, distRslt, deltaLatRslt, deltaLongRslt, rottaInizialeRslt;        //text fields for error text and results text

    //layouts to be able to show data or results or errors and hiding the rest when needed
    private ScrollView datiLyt;
    private LinearLayout risultatiLyt;

    //object declaration for ortodromia and its associated points
    public Ortodromia myOrtodromia = new Ortodromia();
    private Point puntoA = new Point();
    private Point puntoB = new Point();

    //variables for ortodromia's data
    private double latA, latB, longA, longB, distanza, deltaLat, deltaLong, rottaIniziale;
    private String latAsign, deltaLongSign;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ortodromia_dati_layout);


        //layout initiation for results
        risultatiLyt = (LinearLayout)findViewById(R.id.risultatiOrt);
        risultatiLyt.setVisibility(View.INVISIBLE);
        //layout initiation for data input
        datiLyt = (ScrollView)findViewById(R.id.scrollViewOrt);

        //button to hide error message initiation
        hideError = (Button)findViewById(R.id.hideError);
        hideError.setOnClickListener(this);     //setting the button on click listener

        //button to calculate results
        calculateOrtodromia = (Button)findViewById(R.id.calcolaOrto);
        calculateOrtodromia.setOnClickListener(this);    //setting the button on click listener

        //initiating error message's text
        errorMessage = (TextView)findViewById(R.id.ortoErrorMessage);

        //hiding the error message and button ---> they are only showed when data errors occur
        errorMessage.setVisibility(View.INVISIBLE);
        hideError.setVisibility(View.INVISIBLE);

        //initiating switch for pointA latitude's sign and setting it on checked change listener
        swLatA = (Switch)findViewById(R.id.switchLatA);
        swLatA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    swLatA.setText("S");
                }else {
                    swLatA.setText("N");
                }
            }
        });

        //initiating switch for pointA latitude's sign and setting it on checked change listener
        swLatB = (Switch) findViewById(R.id.switchLatB);
        swLatB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swLatB.setText("S");
                } else {
                    swLatB.setText("N");
                }
            }
        });

        //initiating switch for pointA longitude's sign and setting it on checked change listener
        swLongA = (Switch) findViewById(R.id.switchLongA);
        swLongA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swLongA.setText("W");
                } else {
                    swLongA.setText("E");
                }
            }
        });


        //initiating switch for pointB longitude's sign and setting it on checked change listener
        swLongB = (Switch) findViewById(R.id.switchLongB);
        swLongB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swLongB.setText("W");
                } else {
                    swLongB.setText("E");
                }
            }
        });


        //initiating text fields for data input
        latAin = (EditText)findViewById(R.id.latAinput);
        latBin = (EditText)findViewById(R.id.latBinput);
        longBin = (EditText)findViewById(R.id.longBinput);
        longAin = (EditText)findViewById(R.id.longAinput);


        //initiating text field to display outputs (results)
        distRslt = (TextView)findViewById(R.id.distOrtRslt);
        deltaLatRslt = (TextView)findViewById(R.id.deltaLatRslt);
        deltaLongRslt = (TextView)findViewById(R.id.deltaLongRslt);
        rottaInizialeRslt = (TextView)findViewById(R.id.riRslt);


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

            //then pass the points to the Ortodromia
            myOrtodromia.setPointA(puntoA);
            myOrtodromia.setPointB(puntoB);

            //once data is set calculate the results
            calcolaRisultati();
        } catch (Exception e) {

            //if data is not in the right format display an error message
            errorMessage.setVisibility(View.VISIBLE);
            hideError.setVisibility(View.VISIBLE);
        }




    }

    private  void calcolaRisultati(){

        //call the method to calculate the results
        myOrtodromia.calculateResults();

        //get the results and assign them to local variables to display it
        distanza = myOrtodromia.getOrtodromicDistance();
        deltaLat = myOrtodromia.getDeltaLat();
        deltaLong = myOrtodromia.getDeltaLong();
        rottaIniziale = myOrtodromia.getRottaIniziale();

        //formatting results for a nice display
        if(deltaLat < 0){
            deltaLat = -1* deltaLat;
        }

        if(puntoA.getIsNorth()){
            latAsign = "  N  ";
        }else{
            latAsign = "  S  ";
        }


        if(deltaLong < 0){
            deltaLong = -1* deltaLong;
            deltaLongSign = "  W  ";
        } else {
            deltaLongSign = "  E  ";
        }

        //display the results
        distRslt.setText(Double.toString(distanza) + "° \n " + Double.toString(distanza*60) + " NM");
        deltaLatRslt.setText(Double.toString(deltaLat) + latAsign);
        deltaLongRslt.setText(Double.toString(deltaLong) + deltaLongSign );
        rottaInizialeRslt.setText(latAsign + Double.toString(rottaIniziale) + "°" + deltaLongSign);

        //hide the user data input interface
        datiLyt.setVisibility(View.INVISIBLE);
        //show the results
        risultatiLyt.setVisibility((View.VISIBLE));
    }

    @Override       //looking for pressed buttons
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.calcolaOrto:      //if calculate button is pressed go ahead with calculating process
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