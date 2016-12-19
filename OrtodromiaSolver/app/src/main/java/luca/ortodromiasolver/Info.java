package luca.ortodromiasolver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;



/**
 * Created by lucaz on 21/03/2016.
 */
public class Info extends Activity{

    private String versione = "alpha 0.1.3"; //version number
    private String lastUpdate = "03/25/16";     //last update date

    //text dysplayed
    private String infoString = "Questa App nasce senza scopo di lucro, come esercizio. \n" +
            "\n" +
            "Per segnalare ogni bug, risultato non corretto o per qualsiasi domanda contattare luca.collini@maxwell.mi.it \n" +
            "\n" +
            "Versione app: " + versione +
            "\n" +
            "\nLa risoluzione del problema fondamentale dell'ortodromia e della lossodromia sono ora disponibili, si è riscontrato un lieve errore con quest'ultimo. Entrambi i risolutori non sono ancora da considerare attendibili al 100%. " +
            "\n" +
            "\n Il prossimo obbiettivo è rendere disponibile la risoluzione di tutti e 4 i problemi del vento, si provvederà poi alle varianti dei problemi fondamentali della lossodromia e dell'ortodromia." + "" +
            "\n" +
            "\nApp made by Luca Collini for Squirrel Development." +
            "\n" +
            "luca.collini@maxwell.mi.it \n"  +
            "\n" +
            "Last update: " + lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        TextView info1 = (TextView) findViewById(R.id.Info1Text);
        //displaying text
        info1.setText(infoString);

    }
}
