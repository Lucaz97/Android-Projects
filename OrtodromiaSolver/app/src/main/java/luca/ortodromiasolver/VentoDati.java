package luca.ortodromiasolver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

/**
 * Created by lucaz on 21/03/2016.
 */
public class VentoDati extends Activity implements View.OnClickListener{

    private EditText thInput, tcInput, gsInput, tasInput, windVInput, windDInput;        //edit texts for data input
    private Button calcola, hideMessage, clearButton;                                                //buttons
    private Wind myWind;
    private ScrollView ventoDati, ventoError;           //layouts to be able to hide and show them when it's needed



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vento_dati_layout);

        //initiating error layout and making it invisible --> is showed only when data error occurs
        ventoError = (ScrollView)findViewById(R.id.ventoError);
        ventoError.setVisibility(View.INVISIBLE);

        //initiating data input layout and setting it visible
        ventoDati = (ScrollView)findViewById(R.id.ventoDati);
        ventoDati.setVisibility(View.VISIBLE);

        calcola = (Button)findViewById(R.id.calcolaVento);
        calcola.setOnClickListener(this);

        hideMessage = (Button)findViewById(R.id.hideError);
        hideMessage.setOnClickListener(this);

        clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        thInput = (EditText)findViewById(R.id.thInput);
        tcInput = (EditText)findViewById(R.id.tcInput);
        gsInput = (EditText)findViewById(R.id.gsInput);
        tasInput = (EditText)findViewById(R.id.tasInput);
        windDInput = (EditText)findViewById(R.id.wInput);
        windVInput = (EditText)findViewById(R.id.vInput);

        myWind = new Wind();
    }


    private void controllaDati(){
        try{
            //try to get data for first problem

            myWind.setTc(Double.parseDouble(String.valueOf(tcInput.getText())));
            myWind.setWindV(Double.parseDouble(String.valueOf(windVInput.getText())));
            myWind.setWindD(Double.parseDouble(String.valueOf(windDInput.getText())));
            myWind.setTas(Double.parseDouble(String.valueOf(tasInput.getText())));

            //if you can calculate results
            myWind.calculateFirstWindProblem();

            //then display results
            displayResults();

        }catch (Exception e){
            try{
                //if data for first problem isnt available look for data for second problem
                myWind.setTh(Double.parseDouble(String.valueOf(thInput.getText())));
                myWind.setTas(Double.parseDouble(String.valueOf(tasInput.getText())));
                myWind.setWindV(Double.parseDouble(String.valueOf(windVInput.getText())));
                myWind.setWindD(Double.parseDouble(String.valueOf(windDInput.getText())));

                //if you can calculate results
                myWind.calculateSecondWindProblem();


                //then display results
                displayResults();

            }catch (Exception f){
                try{
                    //if data for first and second problem isnt available look for third problem
                    myWind.setTh(Double.parseDouble(String.valueOf(thInput.getText())));
                    myWind.setTas(Double.parseDouble(String.valueOf(tasInput.getText())));
                    myWind.setTc(Double.parseDouble(String.valueOf(tcInput.getText())));
                    myWind.setGs(Double.parseDouble(String.valueOf(gsInput.getText())));

                    //if you can calculate results
                    myWind.calculateThirdWindProblem();

                    //then display results
                    displayResults();

                }catch (Exception g){
                    try{
                        //try to get data for last problem
                        myWind.setTc(Double.parseDouble(String.valueOf(tcInput.getText())));
                        myWind.setGs(Double.parseDouble(String.valueOf(gsInput.getText())));
                        myWind.setWindV(Double.parseDouble(String.valueOf(windVInput.getText())));
                        myWind.setWindD(Double.parseDouble(String.valueOf(windDInput.getText())));

                        //if you can calculate results
                        myWind.calculateFourthWindProblem();

                        //then display results
                        displayResults();

                    }catch(Exception h){
                        //if there is no data for any problem show error
                        ventoDati.setVisibility(View.INVISIBLE);
                        ventoError.setVisibility(View.VISIBLE);

                    }
                }
            }

        }
    }

    private void displayResults(){
        //imposta i risultati
        thInput.setText(Double.toString(myWind.getTh()));
        tcInput.setText(Double.toString(myWind.getTc()));
        tasInput.setText(Double.toString(myWind.getTas()));
        gsInput.setText(Double.toString(myWind.getGs()));
        windVInput.setText(Double.toString(myWind.getWindV()));
        windDInput.setText(Double.toString(myWind.getWindD()));

    }

    public void clearData(){
        thInput.setText(" ");
        tcInput.setText(" ");
        tasInput.setText(" ");
        gsInput.setText(" ");
        windVInput.setText(" ");
        windDInput.setText(" ");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.calcolaVento:
                    controllaDati();
                break;
            case R.id.hideError:
                ventoError.setVisibility(View.INVISIBLE);
                ventoDati.setVisibility(View.VISIBLE);
                break;
            case R.id.clearButton:
                clearData();
                break;
        }
    }
}
