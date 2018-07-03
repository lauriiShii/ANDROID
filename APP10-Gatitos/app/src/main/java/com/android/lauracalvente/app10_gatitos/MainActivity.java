package com.android.lauracalvente.app10_gatitos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lauracalvente.app10_gatitos.BDD.Colection;
import com.android.lauracalvente.app10_gatitos.Model.Pojos.Cat;
import com.android.lauracalvente.app10_gatitos.Utils.Constantes;
import com.android.lauracalvente.app10_gatitos.Utils.IntentUtils;
import com.android.lauracalvente.app10_gatitos.Utils.ValidationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imgFormCat)
    ImageView imgFormCat;
    @BindView(R.id.nameCat)
    TextView nameCat;
    @BindView(R.id.lbltxtName)
    TextView lbltxtName;
    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.lbltxtEmail)
    TextView lbltxtEmail;
    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.imgEmail)
    ImageView imgEmail;
    @BindView(R.id.lbltxtPhoneNumber)
    TextView lbltxtPhoneNumber;
    @BindView(R.id.txtPhoneNumber)
    EditText txtPhoneNumber;
    @BindView(R.id.imgPhone)
    ImageView imgPhone;
    @BindView(R.id.lbltxtAddress)
    TextView lbltxtAddress;
    @BindView(R.id.txtAddress)
    EditText txtAddress;
    @BindView(R.id.imgMap)
    ImageView imgMap;
    @BindView(R.id.lbltxtWeb)
    TextView lbltxtWeb;
    @BindView(R.id.txtWeb)
    EditText txtWeb;
    @BindView(R.id.imgWeb)
    ImageView imgWeb;

    TextView textViewArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //fill textviews in array
        fillArrayTextView();

        //Valid textViews
        validateAdress();
        validateEmail();
        validatePhone();
        validateWeb();
        loadImgCat();
    }

    //fill data textVies in array
    private void fillArrayTextView() {
        textViewArray = new TextView[5];
        textViewArray[0] = lbltxtEmail;
        textViewArray[1] = lbltxtName;
        textViewArray[2] = lbltxtWeb;
        textViewArray[3] = lbltxtPhoneNumber;
        textViewArray[4] = lbltxtAddress;
    }

    //replace img cat and name in avatar
    private void loadImgCat() {
        for (int i = 0; Colection.bdd.size() > i; i++) {
            if (Colection.bdd.get(i).getSelect()) {
                imgFormCat.setImageResource(Colection.bdd.get(i).getSrc());
                nameCat.setText(Colection.bdd.get(i).getName());
            }
        }
    }

    /**CONTROL TEXTVIEW HAS FOCUS - if txt has focus, lbl in bold**/
    @OnFocusChange(R.id.txtName)
    public void onFocusChangedName(View view) {
        changeNegritas(lbltxtName);
    }

    @OnFocusChange(R.id.txtEmail)
    public void onFocusChangedEmail(View view) {
        changeNegritas(lbltxtEmail);
    }

    @OnFocusChange(R.id.txtPhoneNumber)
    public void onFocusChangedPhone(View view) {
        changeNegritas(lbltxtPhoneNumber);
    }

    @OnFocusChange(R.id.txtAddress)
    public void onFocusChangedAdress(View view) {
        changeNegritas(lbltxtAddress);
    }

    @OnFocusChange(R.id.txtWeb)
    public void onFocusChangedWeb(View view) {
        changeNegritas(lbltxtWeb);
    }

    //Replace text Normal for Bold in textView has focus
    public void changeNegritas(View view) {
        for (int i = 0; textViewArray.length > i; i++) {
            TextView tv = textViewArray[i];
            tv.setTypeface(null, Typeface.NORMAL);
        }
        ((TextView) view).setTypeface(null, Typeface.BOLD);
    }

    /**CONTROL CHANGE TEXTVIEW - if text is valid the imagen color is black, else gray**/
    @OnTextChanged(R.id.txtEmail)
    public void onTxtEmailChanged() {
        validateEmail();
    }

    private void validateEmail() {
        if (ValidationUtils.isValidEmail(txtEmail.getText().toString()))
            imgEmail.setColorFilter(Color.BLACK);
        else
            imgEmail.setColorFilter(Color.LTGRAY);
    }

    @OnTextChanged(R.id.txtWeb)

    public void onTxtWebChanged() {
        validateWeb();
    }

    private void validateAdress() {
        if (!TextUtils.isEmpty(txtAddress.getText().toString()))
            imgMap.setColorFilter(Color.BLACK);
        else
            imgMap.setColorFilter(Color.LTGRAY);
    }

    @OnTextChanged(R.id.txtPhoneNumber)
    public void onTxtPhoneChanged() {
        validatePhone();
    }

    private void validatePhone() {
        if (ValidationUtils.isValidPhone(txtPhoneNumber.getText().toString()))
            imgPhone.setColorFilter(Color.BLACK);
        else
            imgPhone.setColorFilter(Color.LTGRAY);
    }

    @OnTextChanged(R.id.txtAddress)
    public void onTxtAdressChanged() {
        validateAdress();
    }

    private void validateWeb() {
        if (ValidationUtils.isValidUrl(txtWeb.getText().toString()))
            imgWeb.setColorFilter(Color.BLACK);
        else
            imgWeb.setColorFilter(Color.LTGRAY);
    }

    /**START ACTIVITY SelectAvatarActivity - in this activity, you select avatar-cat**/
    @OnClick(R.id.imgFormCat)
    public void onImgFormCatClicked() {
        putSelectAvatar();
    }

    private void putSelectAvatar() {
        SelectAvatarActivity.startForResult(this, Constantes.CAT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constantes.CAT) {
            getReturnData(data);
        }
    }

    private void getReturnData(Intent intent) {
        if (intent != null) {
            if (intent.hasExtra(String.format("%s", Constantes.CAT))) {
                Cat cat = intent.getParcelableExtra(String.format("%s", Constantes.CAT));
                imgFormCat.setImageResource(cat.getSrc());
                nameCat.setText(cat.getName());
            }
        }
    }

    /**CONTROL ONCLICK IN IMG - if txt is valid put intent**/
    @OnClick(R.id.imgEmail)
    public void onImgEmailClicked() {
        putIntent(ValidationUtils.isValidEmail(txtEmail.getText().toString()), IntentUtils.sendEmail(new String[]{txtEmail.getText().toString()}));
    }

    @OnClick(R.id.imgPhone)
    public void onImgPhoneClicked() {
        putIntent(ValidationUtils.isValidPhone(txtPhoneNumber.getText().toString()), IntentUtils.newDialIntent(txtPhoneNumber.getText().toString()));
    }

    @OnClick(R.id.imgMap)
    public void onImgMapClicked() {
        putIntent(!TextUtils.isEmpty(txtAddress.getText().toString()), IntentUtils.newSearchInMapIntent(txtAddress.getText().toString()));
    }

    @OnClick(R.id.imgWeb)
    public void onImgWebClicked() {
        putIntent(ValidationUtils.isValidUrl(txtWeb.getText().toString()), IntentUtils.newWebSearchIntent(txtWeb.getText().toString()));
    }

    //throw intent if is valid
    private void putIntent(Boolean valid, Intent intent) {
        if (valid) {
            Intent i = intent;
            if (IntentUtils.isActivityAvailable(getApplicationContext(), i))
                startActivity(i);
        }
    }

}
