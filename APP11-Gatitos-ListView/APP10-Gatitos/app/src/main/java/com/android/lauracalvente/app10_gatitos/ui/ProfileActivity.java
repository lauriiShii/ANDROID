package com.android.lauracalvente.app10_gatitos.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.bdd.ColecctionUsers;
import com.android.lauracalvente.app10_gatitos.bdd.ColectionCat;
import com.android.lauracalvente.app10_gatitos.model.pojos.Cat;
import com.android.lauracalvente.app10_gatitos.model.pojos.User;
import com.android.lauracalvente.app10_gatitos.utils.Constantes;
import com.android.lauracalvente.app10_gatitos.utils.IntentUtils;
import com.android.lauracalvente.app10_gatitos.utils.ValidationUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

/**
 *
 * Activity that serves to create a new user or update an existing one
 *
 * This activity contains a series of views that change color as they update their data
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class ProfileActivity extends AppCompatActivity {

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

    @BindViews({R.id.lbltxtEmail, R.id.lbltxtPhoneNumber, R.id.lbltxtAddress, R.id.lbltxtWeb, R.id.lbltxtName})
    List <TextView> textViewArray; ///TextViews list used to control the focus, your goal is to scroll them all to change the letters of the lbl

    static User user; ///User used to update the views with the data when it is received as an extra or to create it and add it to the list
    public static Cat cat;  //information about the current avatar in the activity

    /**
     * OnCreate: It is responsible for inflating the views of the "activity_profile" layout
     * load the image of the avatar
     * with "loadCatAvatar", initialize the user and load their data if esq there is an extra
     * user, call all the methods of validation of the images
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        //load user
        loadCatAvatar();
        user = new User();
        getIntentData();

        //Valid textViews
        validateName();
        validateAdress();
        validateEmail();
        validatePhone();
        validateWeb();
    }

    /**
     * OnCreateOptionsMenu: inflates the menu for this activity, in this case the menu
     * that we inflate is "profile_activity_menu"
     * @param menu
     * @return True to report that inflation has been completed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_activity_menu, menu);
        return true;
    }

    /**
     * OnOptionsItemSelected: It establishes that it is going to do the button of the menu
     * when pressing it, in this case you return to the main activity by adding a new user if possible
     * @param item
     * @return if you press a button in particular the action of starting the
     *         new activity is executed but it is informed that no
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mmu_save) {
            startMainActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * inicia la "MainActivity" cuando los datos requeridos estan rellenos, sino se informa
     * al usuario de que debe rellenar ciertos campos que se encuentran indicados con una estrella.
     * Antes de inicar la actividad principal se añade el usuario a la bdd de "ColectionUser"
     */
    private void startMainActivity() {
        if (!TextUtils.isEmpty(txtName.getText().toString()) && !TextUtils.isEmpty(txtEmail.getText().toString()) && !TextUtils.isEmpty(txtPhoneNumber.getText().toString())) {
            startActivity(new Intent(this, MainActivity.class));
            addUser();
        }
        else
            Toast.makeText(this, R.string.profile_activity_error_empty, Toast.LENGTH_SHORT).show();

    }

    /**
     * update the avatar with the current cat information, if the cato has not yet been instantiated
     * or initialized it is set to the default
     */
    private void loadCatAvatar(){
        if(cat == null)
            cat = ColectionCat.bdd.get(Constantes.DEFAULT_CAT);
        imgFormCat.setImageResource(cat.getSrc());
        imgFormCat.setTag(cat.getSrc());
        nameCat.setText(cat.getName());
    }

    /**
     * AddUser: first update the user of the activity, then check if it is an update
     * of an existing user or not and proceed to update
     */
    private void addUser() {
        Intent intent = getIntent();
        actualizarUser();
        if (intent != null) {
            if (intent.hasExtra(Constantes.POSITION_USER)) {
                int position = intent.getIntExtra(Constantes.POSITION_USER, 0);
                ColecctionUsers.bddUsers.set(position, user);
            } else
                ColecctionUsers.bddUsers.add(user);
        } else
            ColecctionUsers.bddUsers.add(user);
    }

    /**
     * ActualizarUser: method used by "addUser" to update the user of the activity, this way when
     * a user of the bdd is added or updated it will always be done with the current data of the screen
     */
    private void actualizarUser() {
        user.setCat(cat);
        user.setName(txtName.getText().toString());
        user.setPhone(txtPhoneNumber.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        user.setDireccion(txtAddress.getText().toString());
        user.setWeb(txtWeb.getText().toString());
    }

    /**
     * OnFocusChangedName: when this view receives the focus, the lbl associated with it changes its
     * letter to bold while the rest become all normal, for use the method "changeNegritas"
     * @param view over which the user clicked
     */
    @OnFocusChange(R.id.txtName)
    public void onFocusChangedName(View view) {
        changeNegritas(lbltxtName);
    }

    /**
     * OnFocusChangedEmail: when this view receives the focus, the lbl associated with it changes its
     * letter to bold while the rest become all normal, for use the method "changeNegritas"
     * @param view over which the user clicked
     */
    @OnFocusChange(R.id.txtEmail)
    public void onFocusChangedEmail(View view) {
        changeNegritas(lbltxtEmail);
    }

    /**
     * OnFocusChangedNumber: when this view receives the focus, the lbl associated with it changes its
     * letter to bold while the rest become all normal, for use the method "changeNegritas"
     * @param view over which the user clicked
     */
    @OnFocusChange(R.id.txtPhoneNumber)
    public void onFocusChangedPhone(View view) {
        changeNegritas(lbltxtPhoneNumber);
    }

    /**
     * OnFocusChangedAdress: when this view receives the focus, the lbl associated with it changes its
     * letter to bold while the rest become all normal, for use the method "changeNegritas"
     * @param view over which the user clicked
     */
    @OnFocusChange(R.id.txtAddress)
    public void onFocusChangedAdress(View view) {
        changeNegritas(lbltxtAddress);
    }

    /**
     * OnFocusChangedWeb: when this view receives the focus, the lbl associated with it changes its
     * letter to bold while the rest become all normal, for use the method "changeNegritas"
     * @param view over which the user clicked
     */
    @OnFocusChange(R.id.txtWeb)
    public void onFocusChangedWeb(View view) {
        changeNegritas(lbltxtWeb);
    }

    /**
     * change the font type to bold in the view you receive and the rest leaves them normal
     * @param view that you have to leave in bold
     */
    public void changeNegritas(View view) {
        for (int i = 0; textViewArray.size() > i; i++) {
            TextView tv = textViewArray.get(i);
            tv.setTypeface(null, Typeface.NORMAL);
        }
        ((TextView) view).setTypeface(null, Typeface.BOLD);
    }

    /**
     * OnTxtEmailChanged: Listener that detects any change in the view, when a change is detected
     * it is called first check if the field is filled or not and if it is not showing a required
     * field star, if the written complies with the validateEmail method of the validationUtils class
     * then the functionality of the image is enabled and its color changes to indicate it
     */
    @OnTextChanged(R.id.txtEmail)
    public void onTxtEmailChanged() {validateEmail();}

    /**
     * ValidateEmail: first check if the field is filled or not and if it is not showing a required
     * field star, if the written complies with the validateEmail method of the validationUtils class
     * then the functionality of the image is enabled and its color changes to indicate it
     */
    private void validateEmail() {
        errorTxt(txtEmail);
        if (ValidationUtils.isValidEmail(txtEmail.getText().toString()))
            imgEmail.setColorFilter(Color.BLACK);
        else
            imgEmail.setColorFilter(Color.LTGRAY);
    }

    /**
     * OnTxttWebChanged: Listener that detects any change in the view, when a change is detected
     * it is called "validateWeb"
     */
    @OnTextChanged(R.id.txtWeb)
    public void onTxtWebChanged() {
        validateWeb();
    }

    /**
     * ValidateWeb: if the written complies with the validateWeb method of the validationUtils class
     * then the functionality of the image is enabled and its color changes to indicate it
     */
    private void validateWeb() {
        if (ValidationUtils.isValidUrl(txtWeb.getText().toString()))
            imgWeb.setColorFilter(Color.BLACK);
        else
            imgWeb.setColorFilter(Color.LTGRAY);
    }

    /**
     * OnTxtPhoneChanged: Listener that detects any change in the view, when a change is detected
     * it is called "validatePhone"
     */
    @OnTextChanged(R.id.txtPhoneNumber)
    public void onTxtPhoneChanged() {
        validatePhone();
    }

    /**
     * ValidatePhone: first check if the field is filled or not and if it is not showing a required
     * field star, if the written complies with the validatePhone method of the validationUtils class
     * then the functionality of the image is enabled and its color changes to indicate it
     */
    private void validatePhone() {
        errorTxt(txtPhoneNumber);
        if (ValidationUtils.isValidPhone(txtPhoneNumber.getText().toString()))
            imgPhone.setColorFilter(Color.BLACK);
        else
            imgPhone.setColorFilter(Color.LTGRAY);
    }

    /**
     * OnTxtAddressChanged: Listener that detects any change in the view, when a change is detected
     * it is called "validateAddress"
     */
    @OnTextChanged(R.id.txtAddress)
    public void onTxtAdressChanged() {
        validateAdress();
    }

    /**
     * ValidateAdress: if the written complies with the validateAdress method of the validationUtils class
     * then the functionality of the image is enabled and its color changes to indicate it
     */
    private void validateAdress() {
        if (!TextUtils.isEmpty(txtAddress.getText().toString()))
            imgMap.setColorFilter(Color.BLACK);
        else
            imgMap.setColorFilter(Color.LTGRAY);
    }

    /**
     * OnTxtNameChanged: Listener that detects any change in the view, when a change is detected
     * it is called "validateName"
     */
    @OnTextChanged(R.id.txtName)
    public void onTxtNameChanged() {validateName();}

    /**
     * ValidateName: first check if the field is filled or not and if it is not showing a required
     * field star
     */
    private void validateName() {errorTxt(txtName);}

    /**
     * ErrorTxt: manages the warning that appears in the fields required in the textChange
     * @param txt that analyzes
     */
    private void errorTxt(EditText txt) {
        if (TextUtils.isEmpty(txt.getText().toString())) {
            Drawable warning = getDrawable(R.drawable.ic_star_yellow_24dp);
            warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());
            txt.setError(getString(R.string.profile_activity_required), warning);
        } else
            txt.setError(null);
    }

    /**
     * OnImgFormCatClicked: Listener that open the "selectAvatarActiviy" activity with the "putSelectAvatar" method
     */
    @OnClick(R.id.imgFormCat)
    public void onImgFormCatClicked() {
        putSelectAvatar();
    }

    /**
     * PutSelectAvatar: opens the activity "SelectAvatarActivity" to select a cat to show it in the
     * current activity, this cat is returned as an extra
     */
    private void putSelectAvatar() {
        SelectAvatarActivity.startForResult(this, Constantes.CAT);
    }

    /**
     * OnActivityResult:when resultCode and request code match the values ​​they must have
     * if extra is done from the cat then the data is loaded with "getReturnData"
     * @param requestCode value of the cat
     * @param resultCode code of the intent is correct
     * @param data intent of the cat, this is passed by parameter to "getReturnData"
     *             in order to analyze if it has reached some extra
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constantes.CAT) {
            getReturnData(data);
        }
    }

    /**
     * GetReturnData: check if there is extra in the intent received and load it
     * @param intent to cat
     */
    private void getReturnData(Intent intent) {
        if (intent != null) {
            //if is cat parcelable load cat
            if (intent.hasExtra(Constantes.OBJET_CAT)) {
                cat = intent.getParcelableExtra(Constantes.OBJET_CAT);
                loadCatAvatar();
            }
        }
    }

    /**
     * check if there is an intent and if there is an extra of that intent, if there is
     * a user, your data will be loaded, this happens when you have clicked on an item
     * in the listView (User)
     */
    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constantes.OBJET_USER)) {
                user = intent.getParcelableExtra(Constantes.OBJET_USER);
                cat = user.getCat();
                loadCatAvatar();
                txtName.setText(user.getName());
                txtPhoneNumber.setText(user.getPhone());
                txtEmail.setText(user.getEmail());
                if (!TextUtils.isEmpty(user.getDireccion()))
                    txtAddress.setText(user.getDireccion());
                if (!TextUtils.isEmpty(user.getWeb()))
                    txtWeb.setText(user.getWeb());
            }
        }
    }

    /**
     * method called in the "MainActivity" when a user is pressed, this is responsible for
     * opening the current activity and creating the necessary extras
     * @param activity in which we are
     * @param requestCode if the intent is correct
     * @param u user
     * @param position of the user in the listView to know what to update in case of clicking the menu check
     */
    public static void startForResult(Activity activity, int requestCode, User u, int position) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(Constantes.OBJET_USER, u);
        intent.putExtra(Constantes.POSITION_USER, position);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * OnImgEmailClicked: listener that detects when it is clicked, is responsible for
     * opening an intent with the help of putIntent and the intentUtils class that
     * contains the code that launches the itent of each type
     */
    @OnClick(R.id.imgEmail)
    public void onImgEmailClicked() {putIntent(ValidationUtils.isValidEmail(txtEmail.getText().toString()), IntentUtils.sendEmail(new String[]{txtEmail.getText().toString()}));}

    /**
     * OnImgPhoneClicked: listener that detects when it is clicked, is responsible for
     * opening an intent with the help of putIntent and the intentUtils class that
     * contains the code that launches the itent of each type
     */
    @OnClick(R.id.imgPhone)
    public void onImgPhoneClicked() {putIntent(ValidationUtils.isValidPhone(txtPhoneNumber.getText().toString()), IntentUtils.newDialIntent(txtPhoneNumber.getText().toString()));}

    /**
     * OnImgMapClicked: listener that detects when it is clicked, is responsible for
     * opening an intent with the help of putIntent and the intentUtils class that
     * contains the code that launches the itent of each type
     */
    @OnClick(R.id.imgMap)
    public void onImgMapClicked() {putIntent(!TextUtils.isEmpty(txtAddress.getText().toString()), IntentUtils.newSearchInMapIntent(txtAddress.getText().toString()));}

    /**
     * OnImgWebClicked: listener that detects when it is clicked, is responsible for
     * opening an intent with the help of putIntent and the intentUtils class that
     * contains the code that launches the itent of each type
     */
    @OnClick(R.id.imgWeb)
    public void onImgWebClicked() {putIntent(ValidationUtils.isValidUrl(txtWeb.getText().toString()), IntentUtils.newWebSearchIntent(txtWeb.getText().toString()));}

    /**
     * putIntent: is responsible for launching the intent and running
     * @param valid to launch the intent first you need to know if you can or not
     *              and for that you will pass the result of the field validation
     * @param intent try calling
     */
    private void putIntent(Boolean valid, Intent intent) {
        if (valid) {
            Intent i = intent;
            if (IntentUtils.isActivityAvailable(getApplicationContext(), i))
                startActivity(i);
        }
    }
}