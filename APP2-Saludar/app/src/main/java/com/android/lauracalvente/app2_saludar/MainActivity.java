package com.android.lauracalvente.app2_saludar;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lauracalvente.app2_saludar.utils.KeyBoardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnLongClick;
import butterknife.OnTextChanged;

import static com.android.lauracalvente.app2_saludar.R.*;

public class MainActivity extends AppCompatActivity {

    @BindView(id.txtName)
    EditText txtName;
    @BindView(id.lblName)
    TextInputLayout lblName;
    @BindView(id.chkRespect)
    CheckBox chkRespect;
    @BindView(id.btnGreet)
    Button btnGreet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        ButterKnife.bind(this);

        String fontFolder = "fonts/custom.ttf";
        Typeface font = Typeface.createFromAsset(getAssets(), fontFolder);
        txtName.setTypeface(font);
        lblName.setTypeface(font);
        chkRespect.setTypeface(font);
        btnGreet.setTypeface(font);

        //Ejemplo Lambdas
        btnGreet.setOnClickListener(v -> greet());
    }

    /**@OnClick(id.btnGreet)
    public void onViewClicked() {
        greet();
    }**/

    @OnTextChanged(id.txtName)
    public void onTextChanged() {
        if (!TextUtils.isEmpty(txtName.getText().toString())) {
            btnGreet.setEnabled(true);
            btnGreet.setBackgroundColor(getResources().getColor(color.colorAccent));
        } else {
            txtName.setError(getString(string.main_activity_rellenarText));
            btnGreet.setEnabled(false);
            btnGreet.setBackgroundColor(getResources().getColor(color.colorNotEnable));
        }
    }

    @OnFocusChange(id.txtName)
    public void onFocusChanged(boolean focused) {
        if (!txtName.hasFocus())
            KeyBoardUtils.hideOffKeyBoard(btnGreet, this);
    }

    @OnEditorAction(id.txtName)
    public boolean onEditorAction(){
        //Cuando le da al boton del teclado debe saludar
        greet();
        return true;
    }

    @OnLongClick(id.btnGreet)
    public boolean onLongClick(){
        int veces = 3;
        //Hay que ponerlo asi en String.xml
        //<string name="main_activity_longClick">long click %1$d veces</string>
        //String message = getString(string.main_activity_longClick, veces);
        String message = getResources().getQuantityString(R.plurals.main_activity_longClick, veces);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        return true;
    }

    public void greet (){
        String message = getString(string.main_activity_buenosDias) + (chkRespect.isChecked() ? " Sr/a " : " ") + txtName.getText().toString();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        KeyBoardUtils.hideOffKeyBoard(btnGreet, this);
    }



}
