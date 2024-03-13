package com.ml.domasno2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RecnikActivity extends AppCompatActivity {

    private EditText searchInput;
    private TextView displayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recnik);

        searchInput = findViewById(R.id.vnesiZbor);
        displayResult = findViewById(R.id.prevedenTextView);
    }

    public void prebaraj(View view){
        String searchItem = searchInput.getText().toString();
        String rezultat = prebarajVoRecnik(searchItem);
        displayResult.setText(rezultat);
    }

    private String prebarajVoRecnik(String searchItem) {

        try {
            InputStream fis = getAssets().open("en_mk_recnik.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                //Se pretpostavuva deka sekoja linija vo datotekata na recnikot sodrzi Angliski zbor
                //i negov Makedonski prevod oddeleni so "|"
                String[] delovi = line.split("\\|");
                if (delovi.length >=2 && delovi[0].trim().equalsIgnoreCase(searchItem)) {
                    return  delovi[1].trim(); // Vraka Makedonski prevod
                }
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Zborot ne e najden vo recnikot.";
    }
}