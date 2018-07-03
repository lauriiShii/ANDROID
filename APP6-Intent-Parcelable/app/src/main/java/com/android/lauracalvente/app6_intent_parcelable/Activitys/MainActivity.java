package com.android.lauracalvente.app6_intent_parcelable.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.android.lauracalvente.app6_intent_parcelable.BDD.model.Alumno;
import com.android.lauracalvente.app6_intent_parcelable.R;
import com.android.lauracalvente.app6_intent_parcelable.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.lblDatos)
    TextView lblDatos;
    @BindView(R.id.txtDatosNombre)
    TextView txtDatosNombre;
    @BindView(R.id.txtDatosEdad)
    TextView txtDatosEdad;
    @BindView(R.id.btnSolicitar)
    Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSolicitar)
    public void onViewClicked() {
        requestData();
    }

    private void requestData() {
        if(TextUtils.isEmpty(txtDatosEdad.getText().toString()))
            txtDatosEdad.setText("0");
        if(TextUtils.isEmpty(txtDatosNombre.getText().toString()))
            txtDatosNombre.setText("Sin nombre");
        DataActivity.startForResult(this, Constantes.ALUMNO, new Alumno (txtDatosNombre.getText().toString(), Integer.parseInt(txtDatosEdad.getText().toString())));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constantes.ALUMNO) {
            getReturnData(data);
        }
    }

    private void getReturnData(Intent intent) {
        if (intent != null) {
            if (intent.hasExtra(String.format("%s",Constantes.ALUMNO))) {
                Alumno alumno = intent.getParcelableExtra(String.format("%s",Constantes.ALUMNO));
                txtDatosNombre.setText(alumno.getNombre());
                txtDatosEdad.setText(String.format("%s",alumno.getEdad()));
            }
        }
    }

}
