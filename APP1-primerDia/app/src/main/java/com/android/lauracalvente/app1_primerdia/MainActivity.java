package com.android.lauracalvente.app1_primerdia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity /* OPCION 1 --> implements View.OnClickListener*/{

    //VARIABLES
    private Button btnSaludar;
    private TextView lblHola, lblBien;
    private CheckBox chkInsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        //Inflamos vistas
        btnSaludar = (Button) findViewById(R.id.btnSaludar);
        lblHola = (TextView) findViewById(R.id.lblHola);
        lblBien = (TextView) findViewById(R.id.lblBien);
        chkInsult = (CheckBox) findViewById(R.id.chkInsult);

        chkInsult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showInsultMode(isChecked);
            }
        });

        //OPCION 1
        // btnSaludar.setOnClickListener(this);

        //OPCION 2
        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkInsult.isChecked())
                    insult();
                else
                    greet();
            }
        });

    }

    private void greet (){
        //lblHola.setText("Eres muy muy feo .... Perooo ..... "+ getResources().getString(R.string.main_activity_saludo));
        lblHola.setText("Eres muy muy feo .... Perooo ..... "+ getString(R.string.main_activity_saludo));
    }

    private void insult (){
        lblHola.setText(R.string.main_activity_insult);
    }

    private void showInsultMode(boolean isChecked){
        String message = isChecked ? "activated" : "desactivated";
        Toast.makeText(this, "Modo insulto: " + message, Toast.LENGTH_SHORT).show();
    }

    //OPCION 1
    /*@Override
    public void onClick(View v) {
        saludar();
    }*/
}
