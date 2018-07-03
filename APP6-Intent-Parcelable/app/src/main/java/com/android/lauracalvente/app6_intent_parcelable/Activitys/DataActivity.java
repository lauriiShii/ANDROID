package com.android.lauracalvente.app6_intent_parcelable.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.lauracalvente.app6_intent_parcelable.BDD.model.Alumno;
import com.android.lauracalvente.app6_intent_parcelable.R;
import com.android.lauracalvente.app6_intent_parcelable.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataActivity extends AppCompatActivity {


    @BindView(R.id.lblDatos)
    TextView lblDatos;
    @BindView(R.id.txtNombre)
    EditText txtNombre;
    @BindView(R.id.lblNombre)
    TextInputLayout lblNombre;
    @BindView(R.id.txtEdad)
    EditText txtEdad;
    @BindView(R.id.lblEdad)
    TextInputLayout lblEdad;
    @BindView(R.id.btnAceptar)
    Button btnAceptar;
    static Alumno alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        getIntentData();
    }

    @OnClick(R.id.btnAceptar)
    public void onViewClicked() {
        createResult();
        finish();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(String.format("%s",Constantes.ALUMNO))) {
                txtNombre.setText(alumno.getNombre());
                txtEdad.setText(String.format("%s",alumno.getEdad()));
            }
        }
    }

    private void createResult() {
        Intent result = new Intent();
        result.putExtra(String.format("%s", Constantes.ALUMNO), new Alumno (txtNombre.getText().toString(), Integer.parseInt(txtEdad.getText().toString())));
        this.setResult(RESULT_OK, result);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Up == Back in order not to create a new instance of MainActivity when going up.
        onBackPressed();
        return true;
    }

    @SuppressWarnings("SameParameterValue")
    public static void startForResult(Activity activity, int requestCode, Alumno a) {
        Intent intent = new Intent(activity, DataActivity.class);
        intent.putExtra(String.format("%s", Constantes.ALUMNO), "1");
        activity.startActivityForResult(intent, requestCode);
        alumno = a;
    }
}
